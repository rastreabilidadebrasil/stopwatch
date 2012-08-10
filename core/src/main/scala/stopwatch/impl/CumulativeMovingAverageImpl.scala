package stopwatch.impl

import stopwatch._

private case class Snapshot(time: Long, stats: StopwatchStatistic)

private[stopwatch] class CumulativeMovingAverageImpl(val range: MovingAverageRange) 
                   extends CumulativeMovingAverage {
  @volatile private var dataPoints = BoundedQueue[Snapshot](range.maxDataPoints + 1)

  def ::=(snapshot: StopwatchStatistic) = 
    dataPoints = dataPoints.enqueue(Snapshot(System.currentTimeMillis, snapshot))

  def hits: Long = totals(_.hits)

  def errors: Long = totals(_.errors)

  def hitsPerSec: Double = averageHits / range.period.toSeconds

  def averageHits: Double = 
    if (dataPoints.size == 0) 0 
    else (hits: Double) / dataPoints.size

  def averageErrors: Double = 
    if (dataPoints.size == 0) 0 
    else (errors: Double) / dataPoints.size

  private def totals(value: StopwatchStatistic => Long): Long = {
    val points = dataPoints
    points.size match {
      case 0 => 0
      case n if n <= range.maxDataPoints => value(points.last.stats)
      case _ => value(points.last.stats) - value(points.head.stats)
    }
  }
}
