package cup

/**
 * Created by foostan on 8/29/14.
 */

import scala.io.Source

object Cup08 {
  def main(args: Array[String]) {

  }

  def processFile(filename: String, width: Int) {
    def processLine(line: String) {
      if (line.length > width) {
        println(filename + " : " + line)
      }
    }
    val source = Source.fromFile(filename)

    for (line <- source.getLines())
      processLine(line)
  }

  val increase = (x: Int) => {
    println("We")
    println("are")
    println("here!")
    x + 1
  }

  increase(10)

  def sum(a: Int, b: Int, c: Int) = a + b + c
  val a = sum _
  val b = sum(1, _: Int, 3)
}

