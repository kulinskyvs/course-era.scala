package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
      assert(weight(t2) === 9)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") ===
      List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("chars2String(List('a','b','c')") {
    assert(chars2String('a' :: 'b' :: 'c' :: Nil) == "abc")
  }



  test("times of char in string") {
    val timeList:List[(Char, Int)] = times(string2Chars("abc abc cba cba d d a"))
    assert(timeList.size === 5)
    assert(timeList.find(pair => pair._1 == 'a').get._2 === 5)
    assert(timeList.find(pair => pair._1 == 'b').get._2 === 4)
    assert(timeList.find(pair => pair._1 == 'c').get._2 === 4)
    assert(timeList.find(pair => pair._1 == 'd').get._2 === 2)
    assert(timeList.find(pair => pair._1 == ' ').get._2 === 6)
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) ===
                               List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singletone") {
    assert(singleton(Leaf('a',1) :: Nil))
    assert(!singleton(Leaf('a',1) :: Leaf('b', 2) :: Nil))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) ===
      List(Fork(Leaf('e',1), Leaf('t',2), List('e', 't'), 3),
            Leaf('x',4)))

    val leaflist1 = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4))
    assert(combine(leaflist1) ===
      List(Leaf('x',4),
           Fork(Leaf('e',2), Leaf('t',3), List('e', 't'), 5) ))

    val leafList2 = Leaf('a',1) :: Leaf('b', 2) :: Nil
    assert(combine(leafList2) ===
      List(Fork(Leaf('a', 1), Leaf('b', 2), List('a', 'b'), 3) ))
  }

  test("create code tree") {
    val chars = string2Chars("abcaba")
    val codeTree = createCodeTree(chars)

    assert(codeTree ===
      Fork(Leaf('a', 3),
             Fork(Leaf('c', 1), Leaf('b', 2), List('c','b'), 3),
                 List('a', 'c', 'b'), 6))
  }

  test("decode test") {
    val chars = string2Chars("abcaba")
    val codeTree = Fork(Leaf('a', 3),
             Fork(Leaf('c', 1), Leaf('b', 2), List('c','b'), 3),
                 List('a', 'c', 'b'), 6)
    // 0 - a
    // 11 - b
    // 10 - c
    val encodedBits: List[Bit] = List(0,1,1,1,0, 0,1,1, 0)

    val decodedChars = decode(codeTree, encodedBits)
    assert( decodedChars === chars)
  }

  test("encode with standard encoding algorithm") {
    doEncode(encode)
  }

  test ("codeBits") {
    val codeTable:CodeTable = ('a', List(1,0,1)) :: ('b', List(0,0)) ::
      ('c' , List(1,1,1)) :: Nil
    assert(codeBits(codeTable)('a') == List(1,0,1))
    assert(codeBits(codeTable)('b') == List(0,0))
    assert(codeBits(codeTable)('c') == List(1,1,1))
  }


  test("convert") {
    val codeTree = Fork(Leaf('a', 3),
             Fork(Leaf('c', 1), Leaf('b', 2), List('c','b'), 3),
                 List('a', 'c', 'b'), 6)
    // 0 - a
    // 11 - b
    // 10 - c

    val codeTable = convert(codeTree)
    val expectedCodeTable = ('a', 0::Nil) :: ('c' , 1 :: 0 :: Nil) ::
           ('b',  1 :: 1 :: Nil) :: Nil
    assert(codeTable === expectedCodeTable)
  }

  test("encode with quick encoding algorithm") {
    doEncode(quickEncode)
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }


  def doEncode(encodeFund:CodeTree => (List[Char] => List[Bit])) = {
    val chars = string2Chars("abcaba")
    val codeTree = Fork(Leaf('a', 3),
             Fork(Leaf('c', 1), Leaf('b', 2), List('c','b'), 3),
                 List('a', 'c', 'b'), 6)
    // 0 - a
    // 11 - b
    // 10 - c
    val bits: List[Bit] = List(0,1,1,1,0, 0,1,1, 0)

    val encodedBits = encode(encodeFund)(codeTree, chars)
    assert( encodedBits === bits)
  }
}
