package com.github.foostan.genericsSheet

trait Schema {
  val id: String

  def equalsIdentify(schema: Schema): Boolean = {
    this.id == schema.id
  }

  def equalsWithOutIdentify(schema: Schema): Boolean = {
    val schemaA = this.copy(id = "")
    val schemaB = schema.copy(id = "")

    schemaA.toString == schemaB.toString
  }

  def copy(id: String = this.id): Schema
}
