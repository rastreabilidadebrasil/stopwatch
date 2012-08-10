package stopwatch

case class MovingAverageRange(maxDataPoints: Int, period: TimeUnit)

trait CumulativeMovingAverage {
  def range: MovingAverageRange

  /** Total number of hits within range (maxDataPoints * period).
   */
  def hits: Long

  /** Total number of errors within range (maxDataPoints * period).
   */
  def errors: Long

  /** Average number of hits within period.
   */
  def averageHits: Double

  /** Average number of errors within period.
   */
  def averageErrors: Double

  /** Hits per second within range (maxDataPoints * period).
   */
  def hitsPerSec: Double
}
