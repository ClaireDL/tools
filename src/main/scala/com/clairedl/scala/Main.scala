package com.clairedl.scala

import scala.io.Source
import com.clairedl.scala.CsvReader._
import scala.util.Random._
import scala.collection.mutable
import com.clairedl.scala.tableFormatter._
import com.clairedl.scala.StringOperator._

object Main extends App {

  //
  // Converting a csv file to a List of string
  //
  case class Name(phyllum: String, subphyllum: String)
  case class Plant(name: Name, value: Double, alive: Boolean)
  class ConvertCsvLineToPlant extends Converter[Plant] {
    def convert(line: List[String]): Plant = {
      Plant(Name(line(0), line(1)), line(2).toDouble, line(3).toBoolean)
    }
  }

  val converter = new ConvertCsvLineToPlant
  val garden = loadConvert("Plants.csv", converter)
  // garden.foreach(println)

  //
  // Transforming List of case classes into a table
  //
  val herbarium = List(
    Plant(Name("Dianthus", "Caryophyllus"), 2.50, true),
    Plant(Name("Rosa","Damascena"), 10, false),
    Plant(Name("Iris","Germanica"), 3, true)
  )

  class PlantConverter extends CaseClassConverter[Plant]{
    def convert(plant: Plant): Map[String, String] = {
      val phyllum = plant.name.phyllum
      val subphyllum = plant.name.subphyllum
      val value = plant.value.toString()
      val alive = plant.alive.toString()

      Map(("phyllum", phyllum), ("subphyllum", subphyllum), ("value", value), ("alive", alive))
    }
  }

  val converter2 = new PlantConverter

  val table = new TableFormatter[Plant](herbarium, converter2)
  table.printAsTable(" | ")

  //
  // String operations
  //
  val find = findString("I am looking for the king of Skating.", " ")
  println(find)

  // val comparison = StringOperator.compareStrings("Is it the same?", "is it the same?")
  // println(comparison)
  // val comparison2 = compareStrings("This is identical.", "This is identical.")
  // println(comparison2)
}
