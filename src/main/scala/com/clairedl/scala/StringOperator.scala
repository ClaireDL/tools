package com.clairedl.scala

import scala.collection.mutable.ListBuffer

object StringOperator {
  /**
  * Returns the position(s) of a string if it is found in a string
  */
  def findString(text: String, toFind: String): List[Int] = {
    val input = text.zipWithIndex
    val indexMatch = new ListBuffer[Int]()

    if (text == "" || toFind == "") indexMatch.toList
    else {
      for (index <- input.indices) {
        val end = index + toFind.length()
        val selected = input
          .slice(index, end)
          .map(_._1)
          .mkString
        if (selected == toFind) indexMatch += index
      }
      indexMatch.toList
    }
  }

  def compareStrings(text1: String, text2: String): Boolean = {
    text1 == text2
  }

  def slice(string: String, start: Int, end: Int): String = {
    if (string.length < start)
      return ""

    var result = ""
    for (i <- start to end) {
      if (string.length >= i)
        result += string(i)
    }

    return result
  }
}
