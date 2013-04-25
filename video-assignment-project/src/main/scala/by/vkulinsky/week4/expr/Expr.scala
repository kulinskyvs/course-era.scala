package by.vkulinsky.week4.expr

/**
 * Represents any mathematician expression
 */
trait Expr {

  /**
   * Evaluate expression.
   * The method is implemented using "pattern matching" approach
   */
  def eval: Int = this match {
    case Number(n) => n
    case Sum(l, r) => l.eval + r.eval
    case Prod(l, r) => l.eval * r.eval
    case _ => throw new IllegalStateException("The expression can't be evaluated")
  }

  /**
   * Show current expression
   */
  def show: String = this match {
    case Number(n) => n.toString()
    case Sum(e1,e2) => e1.show+ " + " +e2.show
    case Var(x) => x

    case Prod(l, r) => {
      def showWithParethess(e: Expr) = e match {
        case Sum(_, _) => "("+e.show+")"
        case _ => e.show
      }
      showWithParethess(l) + " * "+ showWithParethess(r)
    }

  }
}