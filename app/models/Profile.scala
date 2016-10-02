package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import java.util
import util.{Date, Calendar}
import java.math.BigDecimal
import scala.language.postfixOps

case class Profile(id: Pk[Long] = NotAssigned, name: String, login: String)

object Profile {

    val simple = {
      get[Pk[Long]]("profiles.id") ~
      get[String]("profiles.name") ~
      get[String]("profiles.login") map {
      case id~name~login => Profile(id, name, login)
    }
  }
  // -- Queries

  def findById(id: Long): Option[Profile] = {
    DB.withConnection { implicit connection =>
      SQL("select * from profiles where id = {id}").on('id -> id).as(Profile.simple.singleOpt)
    }
  }

  def findAll(): Seq[Profile] = {
    DB.withConnection { implicit connection =>
      SQL("select * from profiles")
      .as(Profile.simple *)
    }
  }

  def insert(profile: Profile) = {
    DB.withConnection { implicit connection =>
      SQL("insert into profiles(name, login) values({name}, {login})").on(
        'name -> profile.name, 'login -> profile.login
      ).executeInsert()
    }
  }

  def delete(profileId: Long) = {
    DB.withConnection { implicit connection =>
      SQL("delete from profiles where id = {id}").on(
        'id -> profileId
      ).executeUpdate()
    }
  }
}
