package mill.native

import mainargs.{main, arg, Leftover, ParserForClass, ParserForMethods, Flag}

final case class EnvironmentConfig(
    millVersion: String = sys.env.getOrElse("MILL_VERSION", ""),
    curlCommand: String = scala.sys.env.getOrElse("CURL_CMD", "curl")
) { self =>
  def updateIf(condition: EnvironmentConfig => Boolean)(
      update: EnvironmentConfig => EnvironmentConfig
  ): EnvironmentConfig =
    if (condition(self)) self else update(self)
}

object EnvironmentConfig {

  def load(): EnvironmentConfig = {
    EnvironmentConfig()
      .updateIf(_.millVersion.trim().isEmpty()) {
        _.copy(millVersion = Config.DefaultMillVersion)
      }
  }
}
