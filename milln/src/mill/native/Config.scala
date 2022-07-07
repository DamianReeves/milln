package mill.native

final case class Config(
    millVersion: String,
    curlCommand: String,
    millDownloadPath: os.Path,
    rest: List[String]
) { self =>
  def updateIf(condition: Config => Boolean)(update: Config => Config): Config =
    if (condition(self)) self else update(self)
}
object Config {
  final val DefaultMillVersion = "0.10.0"
  def load(
      commandLineConfig: CommandLineConfig,
      environmentConfig: EnvironmentConfig
  ): Config = {
    val millVersion = commandLineConfig.millVersion.getOrElse("")
    val millVersionResolved = resolveMillVersion(millVersion)
    val rest = commandLineConfig.rest.value.toList
    Config(
      millVersion = millVersionResolved,
      curlCommand = environmentConfig.curlCommand,
      millDownloadPath = getMillDownloadPath,
      rest = rest
    )
  }

  def getMillDownloadPath: os.Path = {

    if (scala.scalanative.runtime.Platform.isWindows()) {
      os.Path(sys.props("user.home")) / "mill"
    } else {
      val xdgCacheHome = sys.props.getOrElse("XDG_CACHE_HOME", "")
      if (xdgCacheHome.nonEmpty) {
        os.Path(xdgCacheHome) / "mill" / "download"
      } else {
        os.Path(sys.props("user.home")) / ".cache" / "mill" / "download"
      }
    }
    // println(sys.props.get("os.name"))
    // os.pwd

  }

  def resolveMillVersion(millVersion: String): String = {
    if (millVersion.trim.isEmpty()) {
      val millVersionPath = os.pwd / ".mill-version"
      println(s"millVersionPath: $millVersionPath")
      if (os.exists(millVersionPath)) {
        println(s"Exists: millVersionPath: $millVersionPath")
        val readVersion =
          os.read.lines(millVersionPath).headOption.getOrElse("")
        if (readVersion.trim().isEmpty()) DefaultMillVersion else readVersion
      } else {
        DefaultMillVersion
      }
    } else { millVersion }
  }
}
