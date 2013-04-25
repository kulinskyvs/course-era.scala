package by.vkulinsky.week4.expr

/**
 * Expression that represents a variable
 */
case class Var(variableName:String) extends Expr {

  /**
   * Returns variable name
   */
  def varName(): String = variableName

}