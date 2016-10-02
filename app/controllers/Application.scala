package controllers

import play.api._
import play.api.mvc._
import models._

class Application extends Controller {

  def index = Action {
    Ok(views.html.servers(Server.findAll()))
  }

  def profiles = Action {
    Ok(views.html.profiles())
  }

  def environments = Action {
    Ok(views.html.environments())
  }

  def keys = Action {
    Ok(views.html.keys())
  }

}
