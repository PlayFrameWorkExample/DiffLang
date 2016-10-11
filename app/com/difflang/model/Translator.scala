package com.difflang.model

import scala.collection.mutable.ListBuffer

/**
  * Created by acer on 10/11/2016.
  */
case class Translator
(
  firstname: String,
  lastname: String,
  companyName: String,
  email: String,
  phone: String,
  address: String,
  state: String,
  zipcode: String,
  TargetLanguage: ListBuffer[String],
  nativeLanguage: ListBuffer[String],
  officialTranslator: Boolean,
  translateSince: Int,
  serviceTranslate: ListBuffer[String],
  translateExpertise: ListBuffer[String]
)
/*
object Translator
{
  implicit object TranslatorWrite extends OWrites[Translator]{
    def writes(translator: Translator) : JsObject[Translator] = Json.obj(
      "firstname" -> translator.firstname,
      "lastname" -> translator.lastname
    )
  }
}*/