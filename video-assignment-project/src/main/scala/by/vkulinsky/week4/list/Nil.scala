package by.vkulinsky.week4.list

/**
 * Represents empty list
 */
class Nil[T] extends List[T]{

  def isEmpty: Boolean = true

  def head:T  = throw new NoSuchElementException("Nil.head")

  def tail:List[T]  = throw new NoSuchElementException("Nil.head")

  def nth(index: Int) : T = throw new IndexOutOfBoundsException("Nil.nth")
}