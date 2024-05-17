package command.sample.controllers

import command.sample.domain.Sample
import command.sample.infrastructure.DBAccessSampleDataSource
import play.api.mvc.{BaseController, *}
import play.api.libs.json.*
import play.api.libs.functional.syntax.*
import play.api.libs.json.Reads.*

import javax.inject.*

@Singleton
class JsonController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def listPlaces() = Action {
    val json = Json.toJson(Place.list)
    Ok(json)
  }
  
  def selectFromRDBSample() = Action {
    val dataSource = new DBAccessSampleDataSource
    val maybeSample = dataSource.selectSample("1111")
    maybeSample match {
      case Some(sample) =>
        val sampleJson = Json.toJson(sample)
        Ok(sampleJson)
      case None => NotFound
    }
  }

  // Sample => Json
  implicit val sampleWrites: Writes[Sample] = (
    (JsPath \ "col1").write[String] and
      (JsPath \ "col2").write[String] and
      (JsPath \ "col3").write[String]
    )(unlift(Sample.unapply))


  def savePlace(): Action[JsValue] = Action(parse.json) { request =>
    val placeResult = request.body.validate[Place]
    placeResult.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      place => {
        Place.save(place)
        Ok(Json.obj("message" -> ("Place '" + place.name + "' saved.")))
      }
    )
  }

  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  // jsonのパースとバリデーション
  // バリデーションに失敗した場合はEitherを返す
  def validateJson[A: Reads]: BodyParser[A] = parse.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )

  // バリデーションをしたくないときは引数のvalidateJson[Place]をBodyParsers.parse.json[Place]で置き換える
  def savePlaceConcise: Action[Place] = Action(validateJson[Place]) { request =>
    // request.bodyにはバリデーション済のPlaceが入ってる
    val place = request.body

    Place.save(place)
    Ok(Json.obj("message" -> ("Place '" + place.name + "' saved.")))
  }
}

// Location => Json
implicit val locationWrites: Writes[Location] =
  (JsPath \ "lat").write[Double]
    .and((JsPath \ "long").write[Double])(unlift(Location.unapply))

// Place => Json
implicit val placeWrites: Writes[Place] =
  (JsPath \ "name")
    .write[String]
    .and((JsPath \ "location").write[Location])(unlift(Place.unapply))

// Json => Location
// readのうしろににバリデーションでつかう条件を追加してるよ
implicit val locationReads: Reads[Location] =
  (JsPath \ "lat")
    .read[Double](min(-90.0).keepAnd(max(90.0)))
    .and((JsPath \ "long").read[Double](min(-180.0).keepAnd(max(180.0))))(Location.apply _)

// Json => Place
implicit val placeReads: Reads[Place] =
  (JsPath \ "name").read[String](minLength[String](2)).and((JsPath \ "location").read[Location])(Place.apply _)

case class Location(lat: Double, long: Double)
object Location {
  def unapply(l: Location): Option[(Double, Double)] = Some(l.lat, l.long)
}

case class Place(name: String, location: Location)
object Place {
  var list: List[Place] = {
    List(
      Place(
        "Sandleford",
        Location(51.377797, -1.318965)
      ),
      Place(
        "Watership Down",
        Location(51.235685, -1.309197)
      )
    )
  }

  def save(place: Place): Unit = {
    list = list ::: List(place)
  }

  def unapply(p: Place): Option[(String, Location)] = Some(p.name, p.location)
}