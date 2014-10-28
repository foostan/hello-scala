package com.github.foostan.genericsSheet

trait Schema {
  val id: String

  def identify(schema: Schema): Boolean = {
    this.id == schema.id
  }
}
