package by.vkulinsky.week4.number

/**
 * The class represents strictly positive natural numbers
 */
class Succ(val n: Nat) extends Nat{

  def isZero: Boolean = false

  def predessor: Nat = n

  def successor: Nat = new Succ(this)

  def +(that:Nat) : Nat = new Succ(n + that)

  def -(that: Nat) : Nat =
    if (that.isZero) this else n - that.predessor

}