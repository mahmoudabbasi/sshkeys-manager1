package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import java.util
import util.{Date, Calendar}
import java.math.BigDecimal
import scala.language.postfixOps

case class Server(id: Pk[Long] = NotAssigned, name: String, comment: String, status: Int = 0, /*status is 0 (init), 1 (group), 2 (private)*/ port: Int = 22, authorized_keys_path: String)

object Server {

    val simple = {
      get[Pk[Long]]("servers.id") ~
      get[String]("servers.name") ~
      get[String]("servers.comment") ~
      get[Int]("servers.status") ~
      get[Int]("servers.port") ~
      get[String]("servers.authorized_keys_path") map {
      case id~name~comment~status~port~authorized_keys_path => Server(id, name, comment, status, port, authorized_keys_path)
    }
  }
  // -- Queries

  def findById(id: Long): Option[Server] = {
    DB.withConnection { implicit connection =>
      SQL("select * from servers where id = {id}").on('id -> id).as(Server.simple.singleOpt)
    }
  }

  def findAll(): Seq[Server] = {
    DB.withConnection { implicit connection =>
      SQL("select * from servers")
      .as(Server.simple *)
    }
  }

  def insert(server: Server) = {
    DB.withConnection { implicit connection =>
      SQL("insert into servers(name, comment, status, port, authorized_keys_path) values({name}, {comment}, {status}, {port}, {authorized_keys_path})").on(
        'name -> server.name, 'comment -> server.comment, 'status -> server.status, 'port -> server.port, 'authorized_keys_path -> server.authorized_keys_path
      ).executeInsert()
    }
  }

  def delete(serverId: Long) = {
    DB.withConnection { implicit connection =>
      SQL("delete from servers where id = {id}").on(
        'id -> serverId
      ).executeUpdate()
    }
  }
}
