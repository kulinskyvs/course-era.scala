package by.vkulinsky.week4.worksheets

object ListWorksheet {
  def isort(list: List[Int]): List[Int] = list match {
    case List() => list
    case head :: tail => insert(head, isort(tail))
  }                                               //> isort: (list: List[Int])List[Int]


  def insert(elem: Int, list: List[Int]) : List[Int] = list match {
    case List() => elem :: list

    case head :: tail => {
       if (elem <= head)  elem :: list
       else head :: insert(elem, tail)
    }
  }                                               //> insert: (elem: Int, list: List[Int])List[Int]

  val list = List(7,9,4,10,8)                     //> list  : List[Int] = List(7, 9, 4, 10, 8)
  isort(list)                                     //> res0: List[Int] = List(4, 7, 8, 9, 10)
}