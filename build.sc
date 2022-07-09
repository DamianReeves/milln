import mill._, scalalib._, scalanativelib._
import $file.project.deps
import deps.{Deps, ScalaVersions}
import deps.ScalaVersions.{scala213, scala3x, scalaNative}

object milln extends ScalaNativeModule {
  def scalaVersion = ScalaVersions.scala213
  def scalaNativeVersion = scalaNative.version
  def ivyDeps = Agg(
    Deps.com.lihaoyi.mainargs,
    Deps.com.lihaoyi.`os-lib`,
    Deps.com.lihaoyi.pprint,
    Deps.com.softwaremill.sttp.client3.core
    // Deps.dev.zio.zio,
    // Deps.io.github.cquiroz.`scala-java-time`,
    // Deps.io.github.cquiroz.`scala-java-time-tzdb`
  )
}
