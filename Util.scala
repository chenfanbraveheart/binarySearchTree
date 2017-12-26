package other

import scala.io.Source

object Util {

  // Read words from textfile
  def parseFile(file: String): List[String] = {

    val wordMatch = """([A-Za-z])+""".r
    if (null != file) {
      Source.fromFile(file).
        getLines().flatMap(line =>
        wordMatch.findAllIn(line.toLowerCase())
      ).toList
    }

  }



}
