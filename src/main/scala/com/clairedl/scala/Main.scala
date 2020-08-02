package com.clairedl.scala

import scala.io.Source
import scala.util.Random._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import com.clairedl.scala.StringOperator._
// import com.clairedl.scala.Keywords._

object Main extends App {

  val keywords: Map[String, Map[String, List[String]]]  =
  Map(
    ("Skydiving",
      Map(
        ("Tunnel", List("V E BRADLEY", "TUNNEL", "RICHARD OWEN\\s+TUNNEL\\s*$", "BODY FLIGHT")),
        ("subcat", List("RICHARD OWEN\\s+(LIFT|UBER)\\s*$", "A2B EURO CARS"))
        )
    ),
    ("Transports",
      Map(
        ("Tube/Bus", List("TFL.GOV.UK", "TFL TRAVEL", "UBER")),
        ("Train", List("TRAINLINE", "LNE RAILWAY", "GREATER ANGLIA", "VIRGINTRAINSEC", "RAILCARD"))
        )
    )
  )

  val sub = Map(
    ("Tube/Bus", List("TFL.GOV.UK", "TFL TRAVEL", "UBER")),
    ("Train", List("TRAINLINE", "LNE RAILWAY", "GREATER ANGLIA", "VIRGINTRAINSEC", "RAILCARD"))
  )

  def findInList(pairings: Map[String, List[String]], keyword: String): String = {
    val result = pairings.find(_._2.contains(keyword))
    result match {
      case Some((x, y)) => x
      case None         => ""
    }
  }

  def findSubcategory(matches: Map[String, Map[String, List[String]]], keyword: String) = {
    var finalResult = new Tuple2[String, String]("","")

    for (key <- matches) {
      val result = key._2.find(_._2.contains(keyword))
      result match {
        case Some((x, y)) => finalResult = ((key._1, x))
        case None         => (("",""))
      }
    }

    finalResult
  }

  println(findInList(sub, "UBER"))
  println(findSubcategory(keywords, "maison"))



  //
  // String operations
  //
  // val find = findString("I am looking for the king of Skating.", " ")
  // println(find)

  // val comparison = StringOperator.compareStrings("Is it the same?", "is it the same?")
  // val comparison = StringOperator.compareStringsScalaMeth("", "d")
  // val comparison2 = compareStringsScalaMeth("This is identical.", "This is identical.")
}
