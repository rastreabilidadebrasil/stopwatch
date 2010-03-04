package stopwatch

import scala.collection.immutable.Queue

object BoundedQueue {
  def apply[A](max: Int) = new BoundedQueue[A](max)
}

/** BoundedQueue allows only 'max' items.
 * The oldest entry is automatically removed when adding a new item.
 */
class BoundedQueue[+A](max: Int) extends Queue[A] {
  override protected def mkQueue[A](i: List[A], o: List[A]): BoundedQueue[A] =
    new BoundedQueue[A](max) {
      override protected val in = i
      override protected val out = o
    }

  override def +[B >: A](elem: B) = 
    if (length >= max) mkQueue(elem :: in.take(max - 1), out) else mkQueue(elem :: in, out)
  
  override def +[B >: A](iter: Iterable[B]) = {
    var q: List[B] = in
    iter.elements.foreach(e => q = e :: q)
    mkQueue(q.take(max), out)
  }
}
