package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype
import squants.market.Money

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

trait Items[F[_]] {
  def findAll: F[List[Item]]
  def find(brand: BrandName): F[List[Item]]
  def find(id: ItemId): F[Option[Item]]
  def create(item: CreateItem): F[Unit]
  def update(item: UpdateItem): F[Unit]
}
