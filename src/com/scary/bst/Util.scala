package com.scary.bst

import scala.io.Source

object Util {

  // Read words from textfile
  def parseFile(file: String): List[String] = {
    if(file == null) {
      null
    } else {
      val wordMatch = """([A-Za-z])+""".r
      Source.fromFile(file).
        getLines().flatMap(line =>
        wordMatch.findAllIn(line.toLowerCase())
      ).toList
    }
  }
}
