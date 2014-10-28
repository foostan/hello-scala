package com.github.foostan.genericsSheet

import com.github.foostan.genericsSheet.schema.PersonSchema
import org.scalatest.{Matchers, FlatSpec}

class SchemaSpec extends FlatSpec with Matchers {

  "A Schema" should "comparable by identify method" in {
    val schemaA = PersonSchema("1", "foo", "10")
    val schemaB = PersonSchema("2", "bar", "12")
    val schemaC = PersonSchema("2", "bazz", "20")

    schemaA.identify(schemaA) should be(true)
    schemaA.identify(schemaB) should be(false)
    schemaA.identify(schemaC) should be(false)
    schemaB.identify(schemaB) should be(true)
    schemaB.identify(schemaC) should be(true)
  }
}
