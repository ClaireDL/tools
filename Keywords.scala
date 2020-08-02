package com.clairedl.scala

object Keywords {
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
}
