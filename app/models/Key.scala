package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import java.util
import util.{Date, Calendar}
import java.math.BigDecimal
import scala.language.postfixOps

case class Key(id: Pk[Long] = NotAssigned, name: String, fingerprint: String, publicKey: String, enabled: Boolean = true)

object Key {

    val simple = {
      get[Pk[Long]]("keys.id") ~
      get[String]("keys.name") ~
      get[String]("keys.fingerprint") ~
      get[String]("keys.public_key") ~
      get[Boolean]("keys.enabled") map {
      case id~name~fingerprint~publicKey~enabled => Key(id, name, fingerprint, publicKey, enabled)
    }
  }
  // -- Queries

  def findById(id: Long): Option[Key] = {
    DB.withConnection { implicit connection =>
      SQL("select * from keys where id = {id}").on('id -> id).as(Key.simple.singleOpt)
    }
  }

  def findAll(): Seq[Key] = {
    DB.withConnection { implicit connection =>
      SQL("select * from keys")
      .as(Key.simple *)
    }
  }

  def insert(key: Key) = {
    DB.withConnection { implicit connection =>
      SQL("insert into keys(name, fingerprint, public_key, enabled) values({name}, {fingerprint}, {public_key}, {enabled})").on(
        'name -> key.name, 'fingerprint -> key.fingerprint, 'public_key -> key.publicKey, 'enabled -> key.enabled
      ).executeInsert()
    }
  }

  def delete(keyId: Long) = {
    DB.withConnection { implicit connection =>
      SQL("delete from keys where id = {id}").on(
        'id -> keyId
      ).executeUpdate()
    }
  }
}
