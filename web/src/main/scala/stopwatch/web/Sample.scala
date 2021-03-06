/*
 *  Copyright 2009-2010 Alex Boisvert
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stopwatch.web

import stopwatch.Stopwatch
import stopwatch.StopwatchGroup
import stopwatch.StopwatchRange
import stopwatch.TimeUnit._

object SampleServer {
  def main(args: Array[String]) {
    val sample = new Sample()
    sample.main(args)
  }
}

/** Sample stopwatch server for demonstration/testing */
class Sample {
  def main(args: Array[String]) {
    val server = new Server

    server.productionMode = false // print error stack traces

    server.stopwatch.enabled = true
    server.stopwatch.range = StopwatchRange(0 millis, 1000 millis, 5 millis)

    val group2 = new StopwatchGroup("Lorem Ipsum")
    group2.enabled = true
    group2.range = StopwatchRange(0 millis, 1000 millis, 10 millis)

    server.groups ::= Stopwatch
    server.groups ::= group2
    server.groups ::= server.stopwatch

    server.start()

    val r = new java.util.Random
    def random(min: Int, max: Int) = r.nextInt(max-min)+min


    // generate some random content
    val words =
      ("consectetur adipisicing incididunt exercitation reprehenderit " +
       "voluptate deserunt consequat laborum") split(" ") toList

    Stopwatch.enabled = true
    for (i <- 1 to 60*100) {
      Stopwatch("foo") {
        Thread.sleep(random(100, 800))
      }
      Stopwatch("bar") {
        Thread.sleep(random(300, 500))
      }
      (words zipWithIndex) foreach { case (word, i) =>
        group2(word) {
          Thread.sleep(random(1, 100*(i+1)))
        }
      }
    }
  }
}