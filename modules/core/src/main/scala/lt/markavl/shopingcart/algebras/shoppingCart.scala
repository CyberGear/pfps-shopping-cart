package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.types.numeric._
import io.estatico.newtype.macros._
import lt.markavl.shopingcart.algebras.auth._
import lt.markavl.shopingcart.algebras.items._
import squants.market._

object shoppingCart {

  trait ShoppingCart[F[_]] {
    def add(userId: UserId, itemId: ItemId, quantity: Quantity): F[Unit]
    def delete(userId: UserId): F[Unit]
    def get(userId: UserId): F[CartTotal]
    def removeItem(userId: UserId, itemId: ItemId): F[Unit]
    def update(userId: UserId, cart: Cart): F[Unit]
  }

  @newtype case class Quantity(value: PosInt)
  @newtype case class CartId(value: UUID)
  @newtype case class Cart(items: Map[ItemId, Quantity])

  case class CartItem(item: Item, quantity: Quantity)
  case class CartTotal(items: List[CartItem], total: Money)

}
