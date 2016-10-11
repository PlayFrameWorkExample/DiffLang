package com.difflang.controllers

import javax.inject.Inject

import play.api.mvc.Controller
import play.modules.reactivemongo.{ReactiveMongoComponents, MongoController, ReactiveMongoApi}

/**
  * Created by acer on 10/11/2016.
  */
class TranslatorController @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends Controller
  with MongoController with ReactiveMongoComponents{


}
