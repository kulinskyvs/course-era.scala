package by.vkulinsky.week5

object listsorting {

  def msort(list : List[Int]) : List[Int] = {
    val n = list.length / 2
    if (n == 0) list
    else {
		  def merge(xs: List[Int], ys: List[Int]) : List[Int] =
		    (xs, ys) match {
		      case (left, List()) => left
		      case (List(), right) => right
		      case (xsHead :: xsTail, ysHead :: ysTail) =>
		        if (xsHead < ysHead) xsHead :: merge(xsTail, ys)
		        else ysHead :: merge(xs, ysTail)
		    }

       val (left, right):(List[Int], List[Int]) = list splitAt n
       merge(msort(left), msort(right))
    }
  }                                               //> msort: (list: List[Int])List[Int]

  msort(List(1,5,8,-7,19))                        //> res0: List[Int] = List(-7, 1, 5, 8, 19)

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil     => xs
    case y :: ys => y * y :: squareList(ys)
  }                                               //> squareList: (xs: List[Int])List[Int]

  def squareListWithMap(xs: List[Int]): List[Int] =
    xs map (x => x * x)                           //> squareListWithMap: (xs: List[Int])List[Int]

 squareList(List(1,2,3,4,5))                      //> res1: List[Int] = List(1, 4, 9, 16, 25)
 squareListWithMap(List(1,2,3,4,5))               //> res2: List[Int] = List(1, 4, 9, 16, 25)
}