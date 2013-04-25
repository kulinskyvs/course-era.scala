package by.vkulinsky.week4.list

class Cons[T](val head: T, val tail: List[T]) extends List[T] {

  def isEmpty: Boolean = false

  def nth(index: Int): T = {

    def nthHelper(nextIndex: Int, fromList: List[T]): T =
      if (nextIndex < 0)
        throw new IndexOutOfBoundsException("Index can't be negative")
      else if (fromList.isEmpty)
        throw new IndexOutOfBoundsException("Index must be less than the list's size")
      else if (0 == nextIndex) fromList.head
      else nthHelper(nextIndex - 1, fromList.tail)

    nthHelper(index, this)
  }
}

object Cons {
  def apply[T](x1: T): List[T] = new Cons(x1, new Nil)

  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))

  def apply[T](x1: T, x2: T, x3: T): List[T] =
    new Cons(x1, new Cons(x2, new Cons(x3, new Nil)))

  def apply[T](x1: T, x2: T, x3: T, x4: T): List[T] =
    new Cons(x1, new Cons(x2, new Cons(x3, new Cons(x4, new Nil))))

  def apply[T](x1: T, x2: T, x3: T, x4: T, x5: T): List[T] =
    new Cons(x1, new Cons(x2, new Cons(x3, new Cons(x4, new Cons(x5, new Nil)))))
}