package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.types.numeric.PosInt
import io.estatico.newtype.macros.newtype
import lt.markavl.shopingcart.algebras.auth.UserId
import lt.markavl.shopingcart.algebras.items.Item
import lt.markavl.shopingcart.algebras.items.ItemId
import squants.market.Money

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
