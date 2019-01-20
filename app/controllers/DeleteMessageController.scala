package controllers

import javax.inject._

import models.Message
import play.api.i18n.{ I18nSupport, Messages }
import play.api.mvc._
import scalikejdbc.AutoSession

@Singleton
class DeleteMessageController @Inject()(components: ControllerComponents)
  extends AbstractController(components) with I18nSupport {

  def delete(messageId: Long): Action[AnyContent] = Action { implicit request =>
    implicit val session = AutoSession
    val result           = Message.deleteById(messageId)
    if (result > 0) {
      Redirect(routes.GetMessagesController.index())
    } else {
      InternalServerError(Messages("DeleteMessageError"))
    }
  }

}
