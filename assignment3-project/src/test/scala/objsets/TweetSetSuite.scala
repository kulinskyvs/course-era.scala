package objsets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import sun.security.pkcs.ESSCertId

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
    val emptySet:TweetSet = new Empty
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("maxretweeted: with set5") {
    new TestSets{
      assert(20 == set5.mostRetweeted.retweets)
    }
  }

  test("maxretweeted: check set(c,d)") {
    new TestSets{
      val set = new NonEmpty(c, new NonEmpty(d, emptySet, emptySet), emptySet);
      assert(9 == set.mostRetweeted.retweets)
    }
  }

  test("maxretweeted: with empty set") {
    new TestSets{
      intercept[NoSuchElementException] {
        set1.mostRetweeted
      }
    }
  }

  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
      assert(trends.tail.head.user == "a" || trends.tail.head.user == "b")
      assert(trends.tail.tail.head.user == "d")
      assert(trends.tail.tail.tail.head.user == "c")
    }
  }

  test("reverse: set5"){
    new TestSets {
      val trends = set5.descendingByRetweet.reverse
      assert(null != trends, "reverse list nust non empty")
      assert(!trends.isEmpty)
      assert(trends.head.user == "c",
          "first element expected to be \"c\", obtained value"+trends.head.user)
      assert(trends.tail.head.user == "d",
          "second element expected to be \"c\", obtained value"+trends.tail.head.user)
      assert(trends.tail.tail.head.user == "a" || trends.tail.tail.head.user == "b",
          "second element expected to be either \"a\" or \"b\", " +
            "obtained value"+trends.tail.tail.head.user)
    }
  }
}
