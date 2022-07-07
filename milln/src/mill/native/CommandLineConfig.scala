package mill.native

import mainargs.{main, arg, Leftover, ParserForClass, ParserForMethods, Flag}

@main
final case class CommandLineConfig(
    @arg(
      name = "mill-version",
      doc = "Version of mill to use to perform the build."
    ) millVersion: Option[String],
    rest: Leftover[String]
)