import mill._, scalalib._, scalanativelib._

object Deps {
  case object com {
    case object lihaoyi {
      val mainargs = ivy"com.lihaoyi::mainargs::${Versions.mainargs}"
      val `os-lib` = ivy"com.lihaoyi::os-lib::${Versions.oslib}"
      val pprint = ivy"com.lihaoyi::pprint::0.7.0"
    }
    case object softwaremill {
      case object sttp {
        case object client3 {
          val core = ivy"com.softwaremill.sttp.client3::core::${Versions.sttp}"
        }
      }
    }
  }

  case object dev {
    case object zio {
      val zio = ivy"dev.zio::zio::${Versions.zio}"
    }
  }

  case object io {
    case object github {
      case object cquiroz {
        val `scala-java-time` =
          ivy"io.github.cquiroz::scala-java-time::${Versions.scalaJavaTime}"
        val `scala-java-time-tzdb` =
          ivy"io.github.cquiroz::scala-java-time-tzdb::${Versions.scalaJavaTime}"

      }
    }
  }
}

object Versions {
  val mainargs = "0.2.3"
  val oslib = "0.8.1"
  val pprint = "0.7.0"
  val sttp = "3.6.2"
  val scalaJavaTime = "2.4.0"
  val zio = "2.0.0"
}

object ScalaVersions {
  val all = List(scala213, scala3x)
  lazy val scala213 = "2.13.8"
  lazy val scala3x = "3.1.3"

  object scalaNative {
    val version = "0.4.5"
  }
}
