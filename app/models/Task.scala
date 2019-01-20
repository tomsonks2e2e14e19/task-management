package models

import java.time.ZonedDateTime

import scalikejdbc._, jsr310._ // java.time.ZonedDateTimeを利用するため、jsr310は手動でインポートしてください。
import skinny.orm._

/**
  * Task
  */
case class Task(id: Option[Long],
                content: String,
                status: String,
                deadline: ZonedDateTime,
                completeAt: ZonedDateTime,
                createAt: ZonedDateTime,
                updateAt: ZonedDateTime)

object Task extends SkinnyCRUDMapper[Task] {

  override def tableName = "tasks"

  override def defaultAlias: Alias[Task] = createAlias("t")

  override def extract(rs: WrappedResultSet, n: ResultName[Task]): Task =
    autoConstruct(rs, n)

  private def toNamedValues(record: Task): Seq[(Symbol, Any)] = Seq(
    'content  -> record.content,
    'status   -> record.status,
    'deadline -> record.deadline,
    'completeAt -> record.completeAt,
    'createAt -> record.createAt,
    'updateAt -> record.updateAt
  )

  def create(task: Task)(implicit session: DBSession): Long =
    createWithAttributes(toNamedValues(task): _*)

  def update(task: Task)(implicit session: DBSession): Int =
    updateById(task.id.get).withAttributes(toNamedValues(task): _*)

}
