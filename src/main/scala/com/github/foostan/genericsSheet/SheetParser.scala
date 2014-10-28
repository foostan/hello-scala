package com.github.foostan.genericsSheet

import scala.reflect.runtime.{universe => ru}

case class SheetParser(data: List[List[String]]) {
  def extract[S <: Schema](implicit ttag: ru.TypeTag[S]): List[S] = {
    val ttagMirror = ttag.mirror

    val schemaClassSymbol = ru.typeOf[S].typeSymbol.asClass
    val schemaClassMirror = ttagMirror.reflectClass(schemaClassSymbol)

    val constructorMethodSymbol = ru.typeOf[S].declaration(ru.nme.CONSTRUCTOR).asMethod
    val constructorMethodMirror = schemaClassMirror.reflectConstructor(constructorMethodSymbol)

    data.map { row =>
      try {
        Some(constructorMethodMirror(row.map(_.asInstanceOf[AnyRef]): _*).asInstanceOf[S])
      } catch {
        case e: IllegalArgumentException => println(s"parse error: $row, ${e.getMessage}"); None
      }
    }.withFilter(_.isDefined).map(_.get)
  }
}

