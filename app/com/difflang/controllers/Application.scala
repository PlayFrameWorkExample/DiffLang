package com.difflang.controllers

import play.api.mvc._
class Application extends Controller {

  def index = Action {
   Ok(com.difflang.views.html.index("hello"))
  }
}