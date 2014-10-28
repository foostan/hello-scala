package com.github.foostan.genericsSheet

import com.github.foostan.genericsSheet.schema.PersonSchema
import org.scalatest.{Matchers, FlatSpec}

class SchemaSpec extends FlatSpec with Matchers {
  val schemaA = PersonSchema("1", "foo", "10")
  val schemaB = PersonSchema("2", "bar", "12")
  val schemaC = PersonSchema("2", "bazz", "20")
  val schemaD = PersonSchema("3", "bazz", "20")

  "A Schema" should "comparable by identify method" in {
    schemaA.equalsIdentify(schemaA) should be(true)
    schemaA.equalsIdentify(schemaB) should be(false)
    schemaA.equalsIdentify(schemaC) should be(false)
    schemaA.equalsIdentify(schemaD) should be(false)

    schemaB.equalsIdentify(schemaB) should be(true)
    schemaB.equalsIdentify(schemaC) should be(true)
    schemaB.equalsIdentify(schemaD) should be(false)

    schemaC.equalsIdentify(schemaC) should be(true)
    schemaC.equalsIdentify(schemaD) should be(false)
  }

  it should "comparable without id" in {
    schemaB.equalsWithOutIdentify(schemaC) should be(false)
    schemaC.equalsWithOutIdentify(schemaD) should be(true)
  }
}
