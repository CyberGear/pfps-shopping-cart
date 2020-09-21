package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.types.string._
import io.estatico.newtype.macros._

object categories {

  trait Categories[F[_]] {
    def findAll: F[List[Category]]
    def create(name: CategoryName): F[Unit]
  }

  @newtype case class CategoryId(value: UUID)
  @newtype case class CategoryName(value: NonEmptyString)

  case class Category(id: CategoryId, name: CategoryName)

}
