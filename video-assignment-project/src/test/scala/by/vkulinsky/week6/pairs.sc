package by.vkulinsky.week6

object pairs {
  def isPrime(x : Int) = (2 until x) forall (i => x % i != 0)
                                                  //> isPrime: (x: Int)Boolean
  isPrime(1)                                      //> res0: Boolean = true
  isPrime(4)                                      //> res1: Boolean = false
  isPrime(7)                                      //> res2: Boolean = true
  isPrime(10)                                     //> res3: Boolean = false
  isPrime(11)                                     //> res4: Boolean = true

  val n = 7                                       //> n  : Int = 7
  val listOfLists = (1 until n) map ( i =>
    (1 until i) map (j => (i,j)) )                //> listOfLists  : scala.collection.immutable.IndexedSeq[scala.collection.immuta
                                                  //| ble.IndexedSeq[(Int, Int)]] = Vector(Vector(), Vector((2,1)), Vector((3,1), 
                                                  //| (3,2)), Vector((4,1), (4,2), (4,3)), Vector((5,1), (5,2), (5,3), (5,4)), Vec
                                                  //| tor((6,1), (6,2), (6,3), (6,4), (6,5)))

  val primePairs = listOfLists.flatten            //> primePairs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,
                                                  //| 1), (3,1), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6
                                                  //| ,2), (6,3), (6,4), (6,5))
  for {
    i <- (1 until n)
    j <- (1 until i)
    if isPrime(i + j)
  } yield (i,j)                                   //> res5: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((2,1), (3,2
                                                  //| ), (4,1), (4,3), (5,2), (6,1), (6,5))

  def scalarProduct(xs: List[Double], ys: List[Double]) =
    (for {
 	     (x, y) <- xs zip ys
    } yield x*y).sum                              //> scalarProduct: (xs: List[Double], ys: List[Double])Double

   scalarProduct(List(1,2,3), List(1,2,3))        //> res6: Double = 14.0

}