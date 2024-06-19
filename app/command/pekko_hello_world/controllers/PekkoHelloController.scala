package command.pekko_hello_world.controllers

import command.pekko_hello_world.actors.HelloActor
import command.pekko_hello_world.actors.HelloActor.SayHello
import org.apache.pekko.actor.*
import org.apache.pekko.pattern.ask
import org.apache.pekko.util.Timeout
import play.api.mvc
import play.api.mvc.*
import play.mvc.Action

import javax.inject.*
import scala.concurrent.Future
import scala.concurrent.duration.*

implicit val timeout: Timeout = 5.seconds
implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

// 作成したアクターの参照を保持するためにSingleton必須
@Singleton
class PekkoHelloController @Inject()(
  val controllerComponents: ControllerComponents,
  system: ActorSystem,
) extends BaseController {

  // actorOf()で新しいアクターを作成する
  val helloActor = system.actorOf(HelloActor.props, "hello-actor")

  // アクターにできることはメッセージの送信。メッセージを受け取っても応答はしない。これをTellパターンという。
  // しかし、HTTPはリクエストとレスポンスがあるので以下のaskパターンを使う方が良い。
  // askパターン(アクターに対してメッセージを送信し、その応答を待つパターン)
  //   - org.apache.pekko.pattern.askをインポート
  //   - 非同期なのでタイムアウトを設定してるよ
  //   - "?"メソッドが内部でaskという処理を呼び出している
  //   - 戻り値はFuture[String]をPlayの型にしたやつになってるよ
  //   - アクターはsender()メソッドを使って応答する
  def sayHello(name: String): mvc.Action[AnyContent] = Action.async {
    // Playの結果型であるResult
    val future: Future[Result] = (helloActor ? SayHello(name)).mapTo[String].map { message => Ok(message) }
    future
  }


}

