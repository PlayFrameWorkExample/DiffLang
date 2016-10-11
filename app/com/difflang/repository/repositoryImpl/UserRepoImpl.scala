package com.difflang.repository.repositoryImpl

import javax.inject.Inject

import com.difflang.model.User
import play.api.libs.json.{JsObject, Json}
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json._
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json.collection.JSONCollection
import com.difflang.repository.UserRepository

import scala.concurrent.{ExecutionContext, Future}
/**
  * Created by KUYLIM on 10/9/2016.
  */
class UserRepoImpl @Inject()(reactiveMongoApi: ReactiveMongoApi) extends UserRepository {

  //def collection = reactiveMongoApi.db.collection[JSONCollection]("users");
  //def DbDefault: DefaultDB = null
  def collection (implicit ec: ExecutionContext)  = reactiveMongoApi.database.map(db => db.collection[JSONCollection]("users"))
 // def collection: Future[JSONCollection] = reactiveMongoApi.connection.database("mymongo").
  //  map(_.collection[JSONCollection]("users"))



  override def find()(implicit ec: ExecutionContext): Future[List[JsObject]] = {
    val genericQueryBuilder = collection.map(_.find(Json.obj()))
    val cursor = genericQueryBuilder.map(_.cursor[JsObject](ReadPreference.Primary))
    cursor.flatMap(_.collect[List]())
  }

  override def update(id: BSONDocument, update: User)(implicit ec: ExecutionContext): Future[WriteResult] = {
    collection.flatMap(_.update(id, update))
  }

  override def remove(id: BSONDocument)(implicit ec: ExecutionContext): Future[WriteResult] = {
    collection.flatMap(_.remove(id))
  }

  override def select(id: BSONDocument)(implicit ec: ExecutionContext): Future[Option[JsObject]] = {
    collection.flatMap(_.find(id).one[JsObject])
  }

  override def save(document: User)(implicit ec: ExecutionContext): Future[WriteResult] = {
    collection.flatMap(_.insert(document))
  }
}
