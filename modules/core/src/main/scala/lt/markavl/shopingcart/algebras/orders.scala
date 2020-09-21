package lt.markavl.shopingcart.algebras

import java.util.UUID

import io.estatico.newtype.macros._
import squants.market._

@newtype case class OrderId(value: UUID)

case class Order(id: OrderId, paymentId: PaymentId, items: Map[ItemId, Quantity])

trait Orders[F[_]] {
  def get(userId: UserId, orderId: OrderId): F[Option[Order]]
  def find(userId: UserId): F[List[Order]]
  def create(userId: UserId, paymentId: PaymentId, Items: List[CartItem], total: Money): F[OrderId]
}
