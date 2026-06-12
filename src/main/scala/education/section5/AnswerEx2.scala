package education.section5

object AnswerEx2:
  case class Article(title: String, tags: Seq[String])

  def msEx2(articles: Seq[Article]): Set[String] =
    articles.flatMap(a => a.tags).toSet
  

  def main(args: Array[String]): Unit =
    println(msEx2(Seq(Article("入門", Seq("scala", "初心者")), Article("実践", Seq("scala", "sbt")))))
