package com.clairedl.scala.tableFormatter

import scala.collection.mutable.ListBuffer
import com.clairedl.scala.contentFormatter._

abstract class CaseClassConverter[A] {
  def convert(input: A): Map[String, String]
}

/**
* After converting a list of case classes to strings, the program formats the input as a table and prints it
*/
class TableFormatter[A](input: List[A], converter: CaseClassConverter[A]) {
  def formatAsTable(): List[Map[String, String]] = {
    if (input == List()) { List() }
    else {
      // Step 1: converts CaseClass to a Map
      val data = input.map(x => converter.convert(x))

      // Step 2: adds header
      val header = data(0).transform((key, value) => key.toUpperCase())
      val unformattedTable = header :: data

      // Step 3: aligns content to get same size columns
      val formattedTable = new ContentFormatter(unformattedTable).format()
      formattedTable
    }
  }

  def printAsTable(spacing: String): Unit = {
    val output = formatAsTable()
    for (line <- output) {println(line.values.mkString(spacing))}
  }
}
