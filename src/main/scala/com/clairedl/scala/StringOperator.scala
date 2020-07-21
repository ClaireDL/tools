package com.clairedl.scala

import scala.collection.mutable.ListBuffer

object StringOperator {
  def findString(text: String, toFind: String): List[Int] = {
    val input = text
      .split("").toList
      .zipWithIndex
    val expression = toFind.split("").toList
    var indexMatch = new ListBuffer[Int]()

    for (inputIndex <- input.indices) {
      val end = inputIndex + expression.length
      val selected = input
        .slice(inputIndex, (inputIndex + expression.length))
        .map(_._1)
      if (selected == expression) indexMatch += inputIndex
    }
    indexMatch.toList
  }
}
