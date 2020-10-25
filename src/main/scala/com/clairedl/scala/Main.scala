package com.clairedl.scala

import scala.io.Source
import scala.util.Random._
import scala.collection.mutable
import com.clairedl.scala.StringOperator._
// import com.clairedl.scala.categoryFinder._

object Main extends App {

  val transactionLabels =
    List(
      "DELLELLE           Food 31/10         BGC",
      "ANDREW CUMMING         TUNNEL D4          FT",
      "C DELLELLE    GROCERY            FT",
      "CRV*EASY BIKE BAR    ON 06 NOV          BCC",
      "HALIFAX CLARITY MA    5353130107545290   BBP",
      "CRV*YOUWORK (1219)     ON 04 NOV          BCC", "THOR A"
    )

  //  Step 1: find Expression in a simple Map structure
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

  // Step2: find Expression in a nested Map structure
  val keywords: Map[String, Map[String, List[String]]]  =
    Map(
      ("Skydiving",
        Map(
          ("Tunnel", List("V E BRADLEY", "TUNNEL", "RICHARD OWEN\\s+TUNNEL\\s*$", "BODY FLIGHT")),
          ("Transport", List("RICHARD OWEN\\s+(LIFT|UBER)\\s*$", "A2B EURO CARS"))
          )
      ),
      ("Transports",
        Map(
          ("Tube/Bus", List("TFL.GOV.UK", "TFL TRAVEL", "UBER")),
          ("Train", List("TRAINLINE", "LNE RAILWAY", "GREATER ANGLIA", "VIRGINTRAINSEC", "RAILCARD"))
          )
      )
    )

  def find(description: String, keywordMatches: Map[String, Map[String, List[String]]]): (String, String) = {
      var matches = ListBuffer[(String, String, String)]()

      for (
        category <- keywordMatches;
        subcategory <- category._2;
        keyword <- subcategory._2
        ) {
          keyword
            .r
            .findFirstIn(description.toUpperCase()) match {
              case Some(_)  => matches += ((category._1, subcategory._1, keyword))
              case None     => ("", "", "")
            }
        }
      val result = matches.maxBy(_._3.length())
      (result._1, result._2)
    }

  val findInMap = find("Uber", keywords)
  println(findInMap)

  // Step 3: find Expression in a case class structure
  case class Category(name: String, keywords: List[String], subcats: List[Subcategory])
    case class Subcategory(name: String, keywords: List[String])

  val matches = List(
    Category("Skydive", List("PETERBOROUGH", "SKYDIVE", "RICHARD OWEN", "WINGGLIDER LIMITED"),
      List(
        Subcategory("Equipment", List("THE JUMP SHOP", "DEKUNU", "CHUTINGSTAR", "AIR SUPPLY", "JEDI.*SUIT", "SUNSHINE FACTORY")),
        Subcategory("Transports", List("RICHARD OWEN\\s+(LIFT|UBER)\\s*$", "A2B EURO CARS"))
      )
    ),
    Category("Transports", List(),
      List(
        Subcategory("Tube/Bus", List("TFL.GOV.UK", "TFL TRAVEL", "UBER"))
      )
    )
  )




  //
  // String operations
  //
  // val find = findString("I am looking for the king of Skating.", " ")
  // println(find)

  // val comparison = StringOperator.compareStrings("Is it the same?", "is it the same?")
  // val comparison = StringOperator.compareStringsScalaMeth("", "d")
  // val comparison2 = compareStringsScalaMeth("This is identical.", "This is identical.")
}
