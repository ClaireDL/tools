package com.clairedl.scala

import scala.io.Source
import scala.util.Random._
import scala.collection.mutable
import com.clairedl.scala.StringOperator._
import com.clairedl.scala.IdentifyTransactionType._
import scala.util.matching.Regex

object Main extends App {

  val databaseMatches: Map[String, Map[String, List[String]]]  =
    Map(
      ("Skydiving",
        Map(
          ("Tunnel", List("V E BRADLEY", "TUNNEL", "RICHARD OWEN\\s+TUNNEL\\s*$", "BODY FLIGHT")),
          ("Transports", List("RICHARD OWEN\\s+(LIFT|UBER)\\s*$", "A2B EURO CARS"))
          )
      ),
      ("Transports",
        Map(
          ("Tube/Bus", List("TFL.GOV.UK", "TFL TRAVEL", "UBER")),
          ("Train", List("TRAINLINE", "LNE RAILWAY", "GREATER ANGLIA", "VIRGINTRAINSEC", "RAILCARD"))
          )
      )
    )

  val example1 = "andrew cumming tunnel d4 ft"
  val example2 = "V E BRADLEY           FAB - 27/02/19     FT"
  val example3 = "TFL TRAVEL CHARGE TFL.GOV.UK/CP"
  val example4 = "RICHARD OWEN UBER"
  val example5 = "Uber trip"

  println(IdentifyTransactionType.findMatches(example1, databaseMatches))
  println(IdentifyTransactionType.findMatches(example2, databaseMatches))
  println(IdentifyTransactionType.findMatches(example3, databaseMatches))
  println(IdentifyTransactionType.findMatches(example4, databaseMatches))
  println(IdentifyTransactionType.findMatches(example5, databaseMatches))
}
