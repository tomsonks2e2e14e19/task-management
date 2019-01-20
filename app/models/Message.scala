package models

import java.time.ZonedDateTime

import scalikejdbc._, jsr310._ // java.time.ZonedDateTimeを利用するため、jsr310は手動でインポートしてください。
import skinny.orm._

/**
  * Message
  */
case class Message(id: Option[Long], body: String, createAt: ZonedDateTime, updateAt: ZonedDateTime)

object Message extends SkinnyCRUDMapper[Task] {

  override def tableName = "messages"

  override def defaultAlias: Alias[Task] = createAlias("m")

  override def extract(rs: WrappedResultSet, n: ResultName[Task]): Task =
    autoConstruct(rs, n)

  private def toNamedValues(record: Task): Seq[(Symbol, Any)] = Seq(
    'body     -> record.body,
    'createAt -> record.createAt,
    'updateAt -> record.updateAt
  )

  def create(message: Task)(implicit session: DBSession): Long =
    createWithAttributes(toNamedValues(message): _*)

  def update(message: Task)(implicit session: DBSession): Int =
    updateById(message.id.get).withAttributes(toNamedValues(message): _*)

}
