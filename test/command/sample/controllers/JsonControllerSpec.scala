package command.sample.controllers

import org.scalatestplus.play.*
import play.api.mvc.*
import play.api.test.*
import play.api.test.Helpers.*
import org.scalatestplus.play.guice.*

import command.sample.infrastructure.DBAccessSampleDataSource
import org.apache.pekko.util.ByteString
import play.api.libs.json.{JsValue, Json}
import play.api.libs.streams.Accumulator

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._
import scala.concurrent.Future

class JsonControllerSpec extends PlaySpec with Results with GuiceOneAppPerTest {
  "listPlaces" should {
    "should be valid" in {
      val controller = new JsonController(Helpers.stubControllerComponents(), new DBAccessSampleDataSource)
      val result: Future[Result] = controller.listPlaces().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText mustBe """[{"name":"Sandleford","location":{"lat":51.377797,"long":-1.318965}},{"name":"Watership Down","location":{"lat":51.235685,"long":-1.309197}}]"""
    }
  }

}
