package lt.markavl.shopingcart.algebras

import java.util.UUID

import io.estatico.newtype.macros._
import lt.markavl.shopingcart.algebras.auth.UserId
import lt.markavl.shopingcart.algebras.items.ItemId
import lt.markavl.shopingcart.algebras.payments.PaymentId
import lt.markavl.shopingcart.algebras.shoppingCart._
import squants.market._

object orders {

  trait Orders[F[_]] {
    def get(userId: UserId, orderId: OrderId): F[Option[Order]]
    def find(userId: UserId): F[List[Order]]
    def create(userId: UserId, paymentId: PaymentId, Items: List[CartItem], total: Money): F[OrderId]
  }

  @newtype case class OrderId(value: UUID)

  case class Order(id: OrderId, paymentId: PaymentId, items: Map[ItemId, Quantity])

}
