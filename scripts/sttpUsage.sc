#!/usr/bin/env amm

import $ivy.`com.softwaremill.sttp.client3::core:3.6.2`
import sttp.client3.quick._

@main def main(args: String*): Unit = {
  println(quickRequest.get(uri"http://httpbin.org/ip").send(backend))
}
