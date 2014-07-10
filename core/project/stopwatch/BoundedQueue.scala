package stopwatch

import scala.collection.immutable.{Iterable, Queue}

object BoundedQueue {
  def apply[A](max: Int) = new BoundedQueue[A](max)
}

/** BoundedQueue allows only 'max' items.
 * The oldest entry is automatically removed when adding a new item.
 */
class BoundedQueue[+A](max: Int, in: List[A] = Nil, out: List[A] = Nil) extends Queue[A](in, out) {
  private def mkQueue[B >: A](in: List[B], out: List[B]) = new BoundedQueue(max, in, out)

  override def enqueue[B >: A](elem: B): BoundedQueue[B] = 
    if (length >= max) mkQueue(elem :: in.take(max - 1), out) else mkQueue(elem :: in, out)
  
  override def enqueue[B >: A](iter: Iterable[B]) = {
    var q: List[B] = in
    iter.iterator.foreach(e => q = e :: q)
    mkQueue(q.take(max), out)
  }
}
