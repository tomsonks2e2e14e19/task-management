package controllers

import javax.inject._

import models.Message
import play.api.i18n.I18nSupport
import play.api.mvc._

@Singleton
class GetMessagesController @Inject()(components: ControllerComponents)
    extends AbstractController(components) with I18nSupport {

  def index: Action[AnyContent] = Action { implicit request =>
    val result = Message.findAll()
    Ok(views.html.index(result))
  }

}
