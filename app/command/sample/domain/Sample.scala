package command.sample.domain

case class Sample(col1: String, col2: String, col3: String)
object Sample {
  //  def save(sample: Sample): Unit = {
  //    list = list ::: List(sample)
  //  }

  def unapply(s: Sample): Option[(String, String, String)] = Some(s.col1, s.col2, s.col3)
}

