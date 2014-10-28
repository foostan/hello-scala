package com.github.foostan.genericsSheet

import com.github.foostan.genericsSheet.schema.PersonSchema
import org.scalatest._

class SheetParserSpec extends FlatSpec with Matchers {

  "A SheetParser" should "parse and extract from list data" in {
    val personData = List(
      List("1", "foo", "10"),
      List("2", "bar", "12"),
      List("3", "bazz"))

    val sheet = SheetParser(personData).extract[PersonSchema]

    sheet should be(List(
      PersonSchema("1", "foo", "10"),
      PersonSchema("2", "bar", "12")))
  }
}