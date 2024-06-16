package command.sample.controllers

import command.sample.infrastructure.DBAccessSampleDataSource
import org.scalatestplus.play.*
import org.scalatestplus.play.guice.*
import play.api.mvc.*
import play.api.test.*
import play.api.test.Helpers.*

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
