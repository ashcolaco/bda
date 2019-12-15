import scala.io.Source
 
object WordCount extends App {
 
  //val url = Source.fromFile("file.txt")
  val header = "Rank Word  Frequency\n==== ======== ======"
 
  def wordCnt =
    Source.fromFile("file.txt").getLines()
      .filter(_.nonEmpty)
      .flatMap(_.split("""\W+""")).toSeq
      .groupBy(_.toLowerCase())
      .mapValues(_.size).toSeq
      .sortWith { case ((_, v0), (_, v1)) => v0 > v1 }
      .take(10).zipWithIndex
 
  println(header)
  wordCnt.foreach {
    case ((word, count), rank) => println(f"${rank + 1}%4d $word%-8s $count%6d")
  }
 
  println(s"\nSuccessfully completed without errors. [total ${scala.compat.Platform.currentTime - executionStart} ms]")
 
}
