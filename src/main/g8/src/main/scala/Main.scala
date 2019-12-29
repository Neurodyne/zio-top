package hello

import zio.App
import zio.console.{ putStrLn }

object Main extends App {

  def run(args: List[String]) =
    myAppLogic.as(0)

  val myAppLogic =
    for {
      _ <- putStrLn("Hello World")
    } yield ()
}
