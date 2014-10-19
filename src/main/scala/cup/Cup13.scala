package cup

package object test {
  def add() {}
  def del() {}
}

object Cup13 {
  import test._
  add()
  del()
}
