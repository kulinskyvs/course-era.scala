package by.vkulinsky.week4.number

/**
 * The object represents natural zero
 */
object Zero extends Nat{

  def isZero: Boolean = true

  def predessor:Nat = throw new Error("0.predessor")

  def successor: Nat = new Succ(this)

  def +(that:Nat) : Nat = that

  def -(that: Nat) : Nat =
    if (that.isZero) this else throw new Error("-.negative")

}