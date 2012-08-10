package stopwatch.impl

import stopwatch._
import org.scalacheck._
import stopwatch.Stopwatch
import stopwatch.TimeUnit._

object CumulativeMovingAverageSpec extends Properties("CumulativeMovingAverage") {
  property("Just n most recent snapshots are considered") = 
    Prop.forAll(smallInteger, listOfInts)((n: Int, hits: List[Int]) => { 
      val stat = new StopwatchStatisticImpl(Stopwatch, "test", Some(mkMovingAverage(n)))
      hits.foreach { h =>
        (0 until h).foreach { _ => stat.notifyStart(0); stat.notifyStop(0, 0, false) }
        stat.notifyPeriodChange
      }
      val total = hits.reverse.take(n).foldLeft(0)(_ + _)
      total == stat.movingAverage.get.hits
    })

  def smallInteger = Gen.choose(1, 10)
  def listOfInts = Gen.containerOf[List, Int](smallInteger)

  private def mkMovingAverage(maxDataPoints: Int) = 
    new CumulativeMovingAverageImpl(MovingAverageRange(maxDataPoints, 5 seconds))
}
