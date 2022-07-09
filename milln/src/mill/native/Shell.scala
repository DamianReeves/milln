package mill.native
import java.lang.{Process, ProcessBuilder}

object Shell {
  def startMill(millExecutable: String, millArgs: Seq[String]): Process = {
    val millCommand = Seq(millExecutable) ++ millArgs
    new ProcessBuilder(millCommand: _*).inheritIO().start()
  }

  def runMill(millExecutable: String, millArgs: Seq[String]): Unit = {
    val process = startMill(millExecutable, millArgs)
    process.waitFor()
  }
}
/*
  Poll(0).startRead { _ =>
    val line = scala.io.StdIn.readLine().trim
    println(s"line was read: $line")
  }
0 is the file descriptor for Stdin
 */
