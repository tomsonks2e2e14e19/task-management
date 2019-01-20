package controllers

import javax.inject._

import models.Message
import play.api.i18n.I18nSupport
import play.api.mvc._

@Singleton
class GetMessageController @Inject()(components: ControllerComponents)
    extends AbstractController(components) with I18nSupport {

  def index(messageId: Long): Action[AnyContent] = Action { implicit request =>
    val message = Message.findById(messageId).get
    Ok(views.html.show(message))
  }

}
