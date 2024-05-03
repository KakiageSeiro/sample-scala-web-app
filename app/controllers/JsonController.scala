package controllers

import play.api.mvc.{BaseController, *}
import play.api.libs.json.*
import play.api.libs.functional.syntax.*

import javax.inject.*

@Singleton
class JsonController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def listPlaces() = Action {
    val json = Json.toJson(Place.list)
    Ok(json)
  }
}

// Location=>Json
implicit val locationWrites: Writes[Location] =
  (JsPath \ "lat").write[Double]
    .and((JsPath \ "long").write[Double])(unlift(Location.unapply))

// Place=>Json
implicit val placeWrites: Writes[Place] =
  (JsPath \ "name")
    .write[String]
    .and((JsPath \ "location").write[Location])(unlift(Place.unapply))

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