package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.types.string._
import io.estatico.newtype.macros._
import lt.markavl.shopingcart.algebras.brands._
import lt.markavl.shopingcart.algebras.categories._
import squants.market._

object items {

  trait Items[F[_]] {
    def findAll: F[List[Item]]
    def findByBrand(brand: BrandName): F[List[Item]]
    def findById(id: ItemId): F[Option[Item]]
    def create(item: CreateItem): F[Unit]
    def update(item: UpdateItem): F[Unit]
  }

  @newtype case class ItemId(value: UUID)
  @newtype case class ItemName(value: NonEmptyString)
  @newtype case class ItemDescription(value: NonEmptyString)

  case class Item(
    id: ItemId,
    name: ItemName,
    description: ItemDescription,
    price: Money,
    brand: Brand,
    category: Category)

  case class CreateItem(
    name: ItemName,
    description: ItemDescription,
    price: Money,
    brand: Brand,
    category: Category)

  case class UpdateItem(id: ItemId, price: Money)

}
