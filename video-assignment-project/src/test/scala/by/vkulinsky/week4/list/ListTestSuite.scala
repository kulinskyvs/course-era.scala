package by.vkulinsky.week4.list

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListTestSuite extends FunSuite{

  trait ListTest {
    val l1 = Cons("first")
    val l2 = Cons("first", "second")
    val l3 = Cons("first", "second", "third")
    val l4 = Cons("first", "second", "third", "forth")
    val l5 = Cons("first", "second", "third", "forth", "fifth")
  }


  test("Cons is created") {
    new ListTest {
	    assert("first" === l1.head, "first element must be actually \"first\"")
	    assert(l1.tail.isEmpty, "tail must be empty")
    }
  }

  test("check nth method") {
	  new ListTest {
	    assert("third" === l5.nth(2), "expected \"third\", obtained:"+l5.nth(2))
	    intercept[IndexOutOfBoundsException] {
	      l5.nth(-1)
	    }

	    intercept[IndexOutOfBoundsException] {
	      l5.nth(5)
	    }
	  }
  }

}