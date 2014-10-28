package com.github.foostan.genericsSheet

trait Schema {
  val id: String

  def equalsIdentify(schema: Schema): Boolean = {
    this.id == schema.id
  }

  def equalsWithOutIdentify(schema: Schema): Boolean = {
    val thisFields = this.productIterator.toList.tail
    val schemaFields = schema.productIterator.toList.tail

    thisFields == schemaFields
  }

  def productIterator: Iterator[Any]
}
