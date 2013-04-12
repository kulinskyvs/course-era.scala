object videoassng {
  val x = new Rational(1,3)                       //> x  : Rational = 1/3
  val y = new Rational(5,7)                       //> y  : Rational = 5/7
  val z = new Rational(3,2)                       //> z  : Rational = 3/2

  x - y - z                                       //> res0: Rational = -79/42
  y + y                                           //> res1: Rational = 10/7
  x.<(y)                                          //> res2: Boolean = true
  x < y                                           //> res3: Boolean = true
  -y                                              //> res4: Rational = 5/-7
}

class Rational(x:Int , y:Int) {
  private def gcd(a:Int, b:Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)

  def numerator = x
  def denominator = y

  def + (that: Rational) =
    new Rational(
      numerator * that.denominator + that.numerator * denominator,
      denominator * that.denominator
    )

  def unary_- = new Rational(-numerator, denominator)

  def - (that : Rational) = this + -that

  def <(that: Rational):Boolean =
     numerator*that.denominator < denominator*that.numerator

  override def toString = (numerator / g) +  "/" + (denominator/g)
}