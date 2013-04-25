package by.vkulinsky.week4.number

/**
 * The class represents all natural numbers
 */
abstract class Nat {

  /**
   * Returns true is the given natural number equals to zero
   * @return true - if the number is zero, false - otherwise
   */
  def isZero: Boolean

  /**
   * Returns previous natural number
   *
   * @return previous natural number
   */
  def predessor: Nat

  /**
   * Returns next natural number
   *
   * @return next previous number
   */
  def successor: Nat

  /**
   * Returns new natural number that is the summary of the current and
   * the given number
   */
  def +(that:Nat) : Nat

  /**
   * Returns new natural number that is the substraction of the current
   * and the given number
   */
  def -(that: Nat) : Nat

}