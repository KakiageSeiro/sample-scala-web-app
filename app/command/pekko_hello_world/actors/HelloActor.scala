package command.pekko_hello_world.actors

import org.apache.pekko.actor.*
import org.apache.pekko.util.Timeout
import play.mvc.Action

object HelloActor {
  def props = Props[HelloActor]()

  case class SayHello(name: String)
}

class HelloActor extends Actor {
  import HelloActor._

  def receive = {
    case SayHello(name: String) =>
      sender() ! "Hello, " + name + "!! ■これはPekkoのアクターから返却したメッセージだよ！"
  }
}
