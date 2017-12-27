package com.scary.bst

class BinarySearchTree() {

  private var root = TreeNode("root", -1, null, null, null)

  // data structure
  private case class TreeNode(
                               val word: String,
                               var times: Int,
                               var leftChild: TreeNode,
                               var rightChild: TreeNode,
                               var parent: TreeNode)

  // add a word
  private def add(word: String): Unit = {
    if (word == null) return

    if (root.times == -1) {
      root = TreeNode(word, 1, null, null, null)
    } else {
      var currNode = root
      while (currNode.word != word) {
        currNode = if (word < currNode.word) {
          if (null == currNode.leftChild) {
            currNode.leftChild = TreeNode(word, 0, null, null, currNode)
          }
          currNode.leftChild
        } else {
          if (null == currNode.rightChild) {
            currNode.rightChild = TreeNode(word, 0, null, null, currNode)
          }
          currNode.rightChild
        }
      }
      currNode.times += 1
    }
  }

  // add multi words
  def addAll(words: List[String]): Unit = {
    if (null != words)
      words.foreach(add)
  }

  /**
    * find the max node in subTree
    *
    * @param currNode subTree's root
    * @return max value node
    */
  private def findMax(currNode: TreeNode): TreeNode = {
    if (currNode.rightChild == null)
      currNode
    else
      findMax(currNode.rightChild)
  }

  //remove one word
  private def remove(word: String): Unit = {
    if (word == null || root.times == -1) {
      return
    } else {
      var currNode = root
      var flag = 0
      while (currNode.word != word) {
        currNode = if (word < currNode.word) {
          if (null == currNode.leftChild) {
            Util.logWarning(s"Remove '${word}' failed,this word does not exist")
            return
          }
          flag = -1
          currNode.leftChild
        } else {
          if (null == currNode.rightChild) {
            Util.logWarning(s"Remove '${word}' failed,this word does not exist")
            return
          }
          flag = 1
          currNode.rightChild
        }
      }
      if (currNode.times > 1) {
        currNode.times -= 1
      } else if (flag == 0) {
        root = TreeNode("root", -1, null, null, null)
      } else if (flag == -1) {
        currNode.parent.leftChild = if (currNode.leftChild == null) {
          currNode.rightChild
        } else if (currNode.rightChild == null) {
          currNode.leftChild
        } else {
          findMax(currNode.leftChild).rightChild = currNode.rightChild
          currNode.leftChild
        }
      } else {
        currNode.parent.rightChild = if (currNode.leftChild == null) {
          currNode.rightChild
        } else if (currNode.rightChild == null) {
          currNode.leftChild
        } else {
          findMax(currNode.leftChild).rightChild = currNode.rightChild
          currNode.leftChild
        }
      }
    }
  }

  // remove stop word list
  def removeAll(words: List[String]): Unit = {
    if (null != words)
      words.foreach(remove)
  }

  // reset tree
  def clear():Unit = {
    root = TreeNode("root", -1, null, null, null)
  }
  // search word
  def find(word: String): Int = {

    if (word == null || root.times == -1) {
      0
    } else {
      var currNode = root

      while (currNode.word != word || currNode != null) {
        currNode = if (word < currNode.word) {
          currNode.leftChild
        } else {
          currNode.rightChild
        }
      }
      if (null == currNode) {
        0
      } else {
        currNode.times
      }
    }
  }

  def printTree(): Unit = {
    if(root.times > 0){
      inorder(root)
      println
    } else {
      Util.logInfo("Tree is empty.")
    }
  }

  // inorder print
  private def inorder(node: TreeNode): Unit = {
    if (node != null) {
      inorder(node.leftChild)
      print(s" ${node.word}(${node.times}) ")
      inorder(node.rightChild)
    }
  }
}

