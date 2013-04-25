package by.vkulinsky.week4.expr

/**
 * Represents number expression
 */
case class Number(n: Int) extends Expr{

  /**
   * Returns the value of the expression
   */
  def getValue() : Int = n
}