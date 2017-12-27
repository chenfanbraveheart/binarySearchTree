package com.scary.bst

class Main {

}

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length > 1) {
      printErrorAndExit("Too much arg")
    }

    val initWords = Util.parseFile(
      if (args.length == 1) args(0) else null
    )

  }

  private def printErrorAndExit(str: String): Unit = {
    println("Error: " + str)
    println("Usage: Main textfile")
    System.exit(1)
  }
}
