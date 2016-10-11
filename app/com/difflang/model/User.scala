package com.difflang.model

import play.api.libs.json._

/**
  * Created by KUYLIM on 10/8/2016.
  */
case class User (
                  name: String,
                  email: String
                )

/*object User {
  val userReads: Reads[User] = (
    (__ \ "name").read[String] and
      (__ \ "email").read[String]

    )(User.apply _)

  val userWrites: Writes[User] = (
      (__ \ "name").write[String] and
      (__ \ "email").write[String]
    )(unlift(User.unapply))

  implicit val userFormat: Format[User] =
    Format(userReads, userWrites)

} */

object User
{
  implicit object UserWrites extends OWrites[User] {
    def writes(user: User): JsObject = Json.obj(
      "name" -> user.name,
      "email" -> user.email
    )
  }

  implicit object UserReads extends Reads[User] {
    def reads(json: JsValue): JsResult[User] = json match {
      case obj: JsObject => try {

        val name = (obj \ "name").as[String]
        val email = (obj \ "email").as[String]
        JsSuccess(User(name, email))

      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ => JsError("expected.jsobject")
    }
  }
}
