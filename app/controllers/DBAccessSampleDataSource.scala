package controllers

import scalikejdbc._

class DBAccessSampleDataSource {
  def selectSample(col1: String) : Option[Sample] =
    DB readOnly { implicit session =>
      sql"SELECT col1, col2, col3 FROM hogeschema.sample WHERE col1 = ${col1}"
        .map(rs => {
          new Sample(
            rs.string("col1"),
            rs.string("col2"),
            rs.string("col3")
          )
        })
        .single.apply()
    }
}

case class Sample(col1: String, col2: String, col3: String)
object Sample {
//  def save(sample: Sample): Unit = {
//    list = list ::: List(sample)
//  }

  def unapply(s: Sample): Option[(String, String, String)] = Some(s.col1, s.col2, s.col3)
}
