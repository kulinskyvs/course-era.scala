package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
    val s7 = singletonSet(7)
    val s1000 = singletonSet(1000)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }


  test("singletonSet(1) does not contains 1") {
    new TestSets {
      assert(!contains(s1, 2))
    }
  }


  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect(1, 2) contains no elements") {
    new TestSets {
      val interSets : Set = intersect(s1, s2);
      assert(!contains(interSets, 1), "check from s1")
      assert(!contains(interSets, 2), "check from s2")
    }
  }

  test ("intersect(union(1,2), union(2,3) contains only 2") {
    new TestSets {
      val s1s2Union:Set = union(s1, s2)
      val s2s3Union:Set = union(s2, s3)
      val inter = intersect(s1s2Union, s2s3Union)

      assert(!contains(inter, 1), "1 is not common")
      assert(contains(inter, 2), "2 is the only common")
      assert(!contains(inter, 3), "3 is not common")
    }
  }


  test("diff(1,2) is 1") {
    new TestSets{
      val diffSet = diff(s1, s2)
      assert(contains(diffSet, 1), "1 must be in diff")
      assert(!contains(diffSet, 2), "2 mustn't be in diff")
    }
  }

  test("diff(2,1) is 2") {
    new TestSets{
      val diffSet = diff(s2, s1)
      assert(contains(diffSet, 2), "2 must be in diff")
      assert(!contains(diffSet, 1), "1 mustn't be in diff")
    }
  }

  test("diff(1,1) is empty") {
    new TestSets{
      val diffSet = diff(s1, s1)
      assert(!contains(diffSet, 1), "1 mustn't be in diff")
    }
  }

  test("check filter") {
    new TestSets {
      val uniSet = union(union(s1, s2), s3)
      val filter1 = filter(uniSet, x => (x == 1))
      assert(contains(filter1, 1), "1 must be in filter(union, 1)")
      assert(!contains(filter1, 2), "2 mustn't be in filter(union, 1)")
      assert(!contains(filter1, 3), "3 mustn't be in filter(union, 1)")

      val filter2 = filter(uniSet, x => (x == 1 || x == 2))
      assert(contains(filter1, 1), "1 must be in filter(union, (1,2))")
      assert(contains(filter2, 2), "2 mustn't be in filter(union, (1,2))")
      assert(!contains(filter2, 3), "3 mustn't be in filter(union, (1,2))")

      val emptyFilter = filter(uniSet, x => (x == 5))
      assert(!contains(emptyFilter, 1), "1 must be in empty filter")
      assert(!contains(emptyFilter, 2), "2 mustn't be in empty filter")
      assert(!contains(emptyFilter, 3), "3 mustn't be in empty filter")

    }
  }

  test("check forall for non-empty sets") {
     new TestSets {
       val fullSet = union(union(s1, s2), s3)
       assert(!forall(fullSet, x => false),  "must be false for false function")
       assert( forall(fullSet, x => true),   "must be true  for true function")
       assert(!forall(fullSet, x => x == 1), "must be false for (x == 1)")
       assert( forall(fullSet, x => x < 10), "must be true for (x < 10)")
       assert(!forall(fullSet, x => x > 10), "must be true for (x > 10)")
     }
  }

  test("check exists: (1,3,4,5,7,1000)") {
     new TestSets {
       val fullSet = union(s1, union(s3, union(s4, union(s5, union(s7, s1000)))))

       assert(!exists(fullSet, x => false),  "must be false for false function")
       assert( exists(fullSet, x => true),   "must be true  for true function")
       assert( exists(fullSet, x => x == 1), "must be true for (x == 1)")
       assert( exists(fullSet, x => x < 3),  "must be true for (x < 3)")
       assert(!exists(fullSet, x => x == 2), "must be false for (x == 2)")
       assert(exists(fullSet, x => x > 10),  "must be true for (x > 10)")
       assert(!exists(fullSet, x => x > 1000),"must be false for (x > 1000)")
     }
  }

  test("check map for non-empty sets") {
    new TestSets {
      val fullSet = union(union(s1, s2), s3)
      val mappedSet = map(fullSet, (x) => x*2)

      assert(forall(mappedSet, (x) => (x == 2 || x == 4 || x == 6)),
          "Map should be (2,4,6)")
      assert(!forall(mappedSet, (x) => (x != 2 && x != 4 && x != 6)),
          "Map should be (2,4,6)")
    }
  }
}
