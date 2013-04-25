package by.vkulinsky.week4.list

/**
 * Represents immutable parametrized list of elements
 */
trait List[T] {

  /**
   * Returns a flag that denotes whethe the given list is empty
   */
  def isEmpty: Boolean

  /**
   * Returns the head of the list
   */
  def head:T

  /**
   * Returns the tail of the list
   */
  def tail: List[T]

  /**
   * Returns the element by the given index
   *
   * @return element from the list with the given index
   * @throws IndexOutOfBoundsException is the index is either less than 0,
   * or greater the than the size of the list
   */
  def nth(index:Int): T
}