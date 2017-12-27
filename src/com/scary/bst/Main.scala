package com.scary.bst

import scala.io.StdIn
import scala.util.control.NonFatal

class Main {

}

object Main {

  def main(args: Array[String]): Unit = {
    val tree = new BinarySearchTree

    println(
      """
        |Usage: command <args>
        |
        |Commands List:
        |  read <file>              read words from a text file
        |  add <word [word]>        add one or more word to our tree
        |  remove <word [word]>     remove one or more word from our tree
        |  find <word>              search word in our tree
        |  stop <file>              read a stop use words from a file
        |  print                    print inorder of tree
        |  clear                    clear all word in tree
      """.stripMargin)

    while (true) {
      try{
        print("-> ")
        val command = StdIn.readLine().trim

        command.split("\\s+")(0).trim match {
          case "read" =>
            val file = command.substring(5).trim
            tree.addAll(Util.parseFile(file))
            Util.logInfo("Read file successfully.")
          case "add" =>
            val words = command.substring(4).trim.split(" ").toList
            tree.addAll(words)
            Util.logInfo("Add words successfully.")
          case "remove" =>
            val words = command.substring(7).trim.split(" ").toList
            tree.removeAll(words)
            Util.logInfo("Remove words successfully.")
          case "find" =>
            val word = command.substring(5).trim
            val times = tree.find(word)
            if(times > 0) {
              Util.logInfo(s"${word} ${times}")
            }else {
              Util.logInfo(s"Word ${word} does not exist.")
            }
          case "stop" =>
            val file = command.substring(5).trim
            tree.removeAll(Util.parseFile(file))
            Util.logInfo("Remove all stop words successfully.")
          case "print" =>
            tree.printTree
          case "clear" =>
            tree.clear
            Util.logInfo("clear all words.")
          case "" =>

          case _ =>
            Util.logWarning("Unknow command,please retry.")
        }
      }catch {
        case NonFatal(e) =>
          e.printStackTrace()
          println
      }
    }

  }

}
