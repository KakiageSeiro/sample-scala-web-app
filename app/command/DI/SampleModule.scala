package command.DI

import com.google.inject.name.Names
import com.google.inject.AbstractModule
import command.sample.repository.SampleDataRepository
import command.sample.infrastructure.DBAccessSampleDataSource

class SampleModule extends AbstractModule {
  override def configure() = {
    bind(classOf[SampleDataRepository])
      .to(classOf[DBAccessSampleDataSource])
    
      // ↑と同じrepositoryに対して、別の実装を紐付けたいときは以下のようにつなげる
      // bind(classOf[SampleDataRepository])
      // .annotatedWith(Names.named("sample2")) // これは一つ目の設定にも追加する必要がある
      // .to(classOf[DBAccessSampleDataSource2])
      
      // さらに、コントローラでTriteを引数に指定した部分に@Namedを追加する。これによってコントローラごとにどの実装を利用するかを指定する
      // class JsonController @Inject() (@Named("sample") sampleDataRepository: SampleDataRepository) {
      
      // 別のrepositoryに対して、別の実装を紐付けたいときは以下のようにつなげる
      // bind(classOf[SampleDataRepository3])
      // .annotatedWith(Names.named("sample3"))
      // .to(classOf[DBAccessSampleDataSource3])
  }
}