package com.clairedl.scala

import com.clairedl.scala.CsvReader._
import com.clairedl.scala.Plant

object ExReadCsvAsCaseClasses {
  /**
  * Exercise: read a csv file and returns it as a list of case classes
  * A converter is implemented, then the program loads and convert a csv file as a list of Plant
  * Prints the result
  */
  def run(): Unit = {

    class ConvertCsvLineToPlant extends Converter[Plant] {
      def convert(line: List[String]): Plant = {
        Plant(Name(line(0), line(1)), line(2).toDouble, line(3).toBoolean)
      }
    }

    val converter = new ConvertCsvLineToPlant
    val garden = loadConvert("Plants.csv", converter)
    garden.foreach(println)
  }
}
