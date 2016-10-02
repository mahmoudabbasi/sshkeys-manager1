package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import java.util
import util.{Date, Calendar}
import java.math.BigDecimal
import scala.language.postfixOps

case class Environment(id: Pk[Long] = NotAssigned, name: String, description: String)

object Environment {

    val simple = {
      get[Pk[Long]]("environments.id") ~
      get[String]("environments.name") ~
      get[String]("environments.login") map {
      case id~name~description => Environment(id, name, description)
    }
  }
  // -- Queries

  def findById(id: Long): Option[Environment] = {
    DB.withConnection { implicit connection =>
      SQL("select * from environments where id = {id}").on('id -> id).as(Environment.simple.singleOpt)
    }
  }

  def findAll(): Seq[Environment] = {
    DB.withConnection { implicit connection =>
      SQL("select * from environments")
      .as(Environment.simple *)
    }
  }

  def insert(environment: Environment) = {
    DB.withConnection { implicit connection =>
      SQL("insert into environments(name, description) values({name}, {description})").on(
        'name -> environment.name, 'description -> environment.description
      ).executeInsert()
    }
  }

  def delete(environmentId: Long) = {
    DB.withConnection { implicit connection =>
      SQL("delete from environments where id = {id}").on(
        'id -> environmentId
      ).executeUpdate()
    }
  }
}
