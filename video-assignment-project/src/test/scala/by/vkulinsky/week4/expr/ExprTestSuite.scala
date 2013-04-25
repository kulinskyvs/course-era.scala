package by.vkulinsky.week4.expr

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import scala.actors.Eval

@RunWith(classOf[JUnitRunner])
class ExprTestSuite extends FunSuite{

  test("eval: number") {
    assert(Number(1).eval === 1)
  }

  test("eval: sum") {
    assert(Sum(Number(1), Number(2)).eval === 3)
  }

  test("eval: prod") {
    assert(Prod(Number(2), Number(3)).eval === 6)
  }

  test("eval: var") {
    intercept[IllegalStateException] {
	  new Var("x").eval
	}
  }


  test("show: number") {
    assert(Number(1).show === "1")
  }

  test("show: sum") {
    val sumExp: Expr = Sum(Number(1), Number(2))
    val expectedShowExpr = "1 + 2"
    assert(sumExp.show === expectedShowExpr)
  }

  test("show: var") {
    assert(Var("x").show === "x")
  }

  test("show: prod") {
    val expr0:Expr = Prod(Number(2), Var("x"))
    val expr1:Expr = Sum(Prod(Number(2), Var("x")), Var("y"))
    val expr2:Expr = Prod(Sum(Number(2), Var("x")), Var("y"))
    val expr3:Expr = Prod(Var("y"), Sum(Number(2), Var("x")))

    val expr0ExpValue:String = "2 * x"
    val expr1ExpValue:String = "2 * x + y"
    val expr2ExpValue:String = "(2 + x) * y"
    val expr3ExpValue:String = "y * (2 + x)"

    val expValueBinder = Map(
        expr0 -> expr0ExpValue,
        expr1 -> expr1ExpValue,
        expr2 -> expr2ExpValue,
        expr3 -> expr3ExpValue)

   expValueBinder.foreach(arg => assert(arg._1.show === arg._2))
  }
}