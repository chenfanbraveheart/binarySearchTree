package com.scary.bst

object Test {
  def main(args: Array[String]): Unit = {
    val words = Util.parseFile("/home/chenfan/IdeaProjects/mavenTest/src/main/scala/other/testFile.txt")
    println(words.length)
    words.foreach(word => print(s"${word} "))

    val a = "12"


  }
}
