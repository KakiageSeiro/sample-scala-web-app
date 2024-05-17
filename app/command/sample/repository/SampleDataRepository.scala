package command.sample.repository

import command.sample.domain.Sample

trait SampleDataRepository {
  def selectSample(col1: String) : Option[Sample]
}
