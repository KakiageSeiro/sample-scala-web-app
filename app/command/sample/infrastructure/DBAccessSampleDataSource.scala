package command.sample.infrastructure

import command.sample.domain.Sample
import command.sample.repository.SampleDataRepository as SampleDataRepositoryInterface
import scalikejdbc.*

class DBAccessSampleDataSource extends SampleDataRepositoryInterface {
  /*
    こんな感じのテーブルがある前提
    CREATE TABLE hogeschema.sample (
      col1 VARCHAR(10),
      col2 VARCHAR(10),
      col3 VARCHAR(10),
      PRIMARY KEY (col1)
    );
  */
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
