package by.vkulinsky.week4.expr

/**
 * Represents a summary expression
 */
case class Sum(leftOperand: Expr, rightOperand: Expr) extends Expr{

  /**
   * Returns left operand of the summary expression
   */
  def left(): Expr = leftOperand


  /**
   * Return right operand of the summary expression
   */
  def right(): Expr = rightOperand
}