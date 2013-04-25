package by.vkulinsky.week4.expr

/**
 * Expression that represents production of two expression
 */
case class Prod(leftOperand: Expr, rightOperand: Expr) extends Expr{

  /**
   * Returns left operand of the summary expression
   */
  def left(): Expr = leftOperand


  /**
   * Return right operand of the summary expression
   */
  def right(): Expr = rightOperand

}