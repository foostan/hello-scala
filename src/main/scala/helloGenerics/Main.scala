package helloGenerics

import scala.reflect.runtime.{universe => ru}

object Main {
  def main(args: Array[String]) {
    val personData = List(
      List("foo", "10"),
      List("bar", "12"),
      List("bazz"))

    val sheet = SheetParser(personData).extract[PersonSchema]
    sheet.foreach(row=>println(row.name, row.age))
  }
}

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

trait Schema

case class PersonSchema
(name: String,
 age: String) extends Schema
