package by.vkulinsky.week5

object collections {
  val arr1to10 = Array(1,2,3,4,5,6,7,8,9,10)      //> arr1to10  : Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val range1to10 = 1 to 10                        //> range1to10  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4,
                                                  //|  5, 6, 7, 8, 9, 10)
  val s = "Hellow World"                          //> s  : String = Hellow World
  s filter (char => char.isUpper)                 //> res0: String = HW

  arr1to10 exists (x => x > 5)                    //> res1: Boolean = true
  range1to10 forall (x => x <= 10)                //> res2: Boolean = true

  val zippedPairs = arr1to10 zip s                //> zippedPairs  : Array[(Int, Char)] = Array((1,H), (2,e), (3,l), (4,l), (5,o),
                                                  //|  (6,w), (7, ), (8,W), (9,o), (10,r))
  zippedPairs.unzip                               //> res3: (scala.collection.mutable.IndexedSeq[Int], scala.collection.mutable.In
                                                  //| dexedSeq[Char]) = (ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),ArrayBuffer(H,
                                                  //|  e, l, l, o, w,  , W, o, r))

  range1to10 flatMap (x => List(x,1))             //> res4: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 1, 2, 1, 3, 1, 
                                                  //| 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10, 1)


}