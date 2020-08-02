package com.clairedl.scala

import com.clairedl.scala.tableFormatter._
import com.clairedl.scala.Plant

object ExCaseClassToTable {
  /**
  * Exercise: print a list of case classes as a table
  * A list of case classes is provided, alongside a converter.
  * TableFormatter processes that list with the converter and returns a printable List that is printed.
  */
  def run(): Unit = {
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
  }
}
