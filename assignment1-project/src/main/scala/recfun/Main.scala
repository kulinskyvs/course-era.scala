package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0 || r < 0) 0
    else if 
       (c == 0 && r == 0) 1
    else 
       pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    if (chars.isEmpty) true

    def loopForBrackets(subChars: List[Char], stillOpenedBrackets: Int): Int = {
      if (subChars.isEmpty) stillOpenedBrackets
      else 
        if ('(' == subChars.head)
          loopForBrackets(subChars.tail, stillOpenedBrackets + 1)
          
      else 
        if (')' == subChars.head)
          if (stillOpenedBrackets > 0)
             loopForBrackets(subChars.tail, stillOpenedBrackets - 1)
          else -1
          
      else 
        loopForBrackets(subChars.tail, stillOpenedBrackets);
    }
    
    0 == loopForBrackets(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
    def count(amount:Int, coins: List[Int] ): Int = {
      if (0 == amount) 1
      else 
        if (amount < 0) 0
      else 
        if (coins.isEmpty && amount >= 1) 0
      else
        count(amount, coins.tail) + count(amount - coins.head, coins)
    }
    
    count(money, coins)
  }
}

