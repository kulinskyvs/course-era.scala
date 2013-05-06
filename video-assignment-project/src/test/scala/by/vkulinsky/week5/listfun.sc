package by.vkulinsky.week5

object listfun {
  val nums = List(2,-4,5,6,2)                     //> nums  : List[Int] = List(2, -4, 5, 6, 2)

  nums filter (x => x > 0)                        //> res0: List[Int] = List(2, 5, 6, 2)
  nums filterNot (x => x > 0)                     //> res1: List[Int] = List(-4)
  nums partition (x => x > 0)                     //> res2: (List[Int], List[Int]) = (List(2, 5, 6, 2),List(-4))

  nums takeWhile (x => x > 0)                     //> res3: List[Int] = List(2)
  nums dropWhile (x => x > 0)                     //> res4: List[Int] = List(-4, 5, 6, 2)
  nums span (x => x > 0)                          //> res5: (List[Int], List[Int]) = (List(2),List(-4, 5, 6, 2))

  def pack[T](xs : List[T]) : List[List[T]] = xs match {
    case Nil => Nil
    case head :: tail =>
      val (first, rest) = tail.span(x => x == head)
      (head :: first) :: pack(rest)
  }                                               //> pack: [T](xs: List[T])List[List[T]]

  def encode[T](xs : List[T]) : List[(T, Int)] = {
    pack(xs) map (x => (x.head, x.length))
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]

  pack(List('a','a','a', 'b', 'c', 'c', 'a'))     //> res6: List[List[Char]] = List(List(a, a, a), List(b), List(c, c), List(a))
  encode(List('a','a','a', 'b', 'c', 'c', 'a'))   //> res7: List[(Char, Int)] = List((a,3), (b,1), (c,2), (a,1))

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())( (x:T, acc:List[U]) => f(x) :: acc)
                                                  //> mapFun: [T, U](xs: List[T], f: T => U)List[U]

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0)( (x:T, ac:Int) => ac + 1)    //> lengthFun: [T](xs: List[T])Int

  def flatMapFun[T, U] (xs : List[T], f: T => Iterable[U]) : List[U] =
    for {
      x <- xs
      y <- f(x)
    } yield y                                     //> flatMapFun: [T, U](xs: List[T], f: T => Iterable[U])List[U]


  List(1,2,3) flatMap (x => x :: List(1))         //> res8: List[Int] = List(1, 1, 2, 1, 3, 1)
  flatMapFun[Int, Int](List(1,2,3), _ :: List(1)) //> res9: List[Int] = List(1, 1, 2, 1, 3, 1)

  mapFun(List(1,2,3,4,5), (x:Int) => x/x)         //> res10: List[Int] = List(1, 1, 1, 1, 1)
  lengthFun(List(1,2,3,4,5))                      //> res11: Int = 5

  def reverse[T](xs:List[T]):List[T]=
    (xs foldLeft List[T]()) ( (xs,x) => x :: xs ) //> reverse: [T](xs: List[T])List[T]

}