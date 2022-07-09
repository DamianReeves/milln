package mill.native
import mainargs.{main, arg, Leftover, ParserForClass, ParserForMethods, Flag}

object Main {

  def main(args: Array[String]): Unit = {
    val commandLineConfig =
      ParserForClass[CommandLineConfig].constructOrExit(args)
    println(s"CommandLineConfig: $commandLineConfig")
    val environmentConfig = EnvironmentConfig.load()
    println(s"EnvironmentConfig: $environmentConfig")

    val config = Config.load(commandLineConfig, environmentConfig)
    println(s"Config: $config")

    downloadMillTo(config.millVersion, config.millDownloadPath)
    launch(config)
  }

  def trySttp() = {
    import sttp.client3._

// the `query` parameter is automatically url-encoded
// `sort` is removed, as the value is not defined
    val request = basicRequest.get(
      uri"https://httpbin.org/get"
    )

    val backend = CurlBackend()
    val response = request.send(backend)

// response.header(...): Option[String]
    println(response.header("Content-Length"))

// response.body: by default read into an Either[String, String] to indicate failure or success
    println(response.body)
  }

  def downloadMillTo(millVersion: String, millDownloadPath: os.Path) = {
    println(s"Downloading mill version '$millVersion' to $millDownloadPath")
    // os.makeDir.all(millDownloadPath)
  }

  def launch(config: Config) = {
    val millPath = config.millDownloadPath / config.millVersion
    println(s"Launching mill at $millPath")
    val millProcess =
      Shell.startMill("exec", Seq(millPath.toString(), "--version"))
    millProcess.waitFor()
  }

  // def zioPart = {
  //   import zio._
  //   val runtime = Runtime.default

  //   Unsafe.unsafe { implicit u =>
  //     runtime.unsafe
  //       .run(ZIO.attempt(println("Hello World!")))
  //       .getOrThrowFiberFailure()
  //   }
  // }

}
