package com.clairedl.scala

import scala.collection.mutable.ListBuffer
// import scala.io._
// import org.json4s._
// import org.json4s.native.JsonMethods._

object IdentifyTransactionType {
/**
* Given a transaction label, returns its category and subcategory
*/
  def findMatches(description: String, keywordMatches: Map[String, Map[String, List[String]]]): (String, String) = {
    // Matches stores all matches but there may be more than one hit because one word appears in different keywords
    // The longest keyword is the more specific and, therefore, more accurate one.
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
}
