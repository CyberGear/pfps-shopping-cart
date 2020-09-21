package lt.markavl.shopingcart.programs

import cats._
import cats.implicits._
import lt.markavl.shopingcart.algebras.auth._
import lt.markavl.shopingcart.algebras.orders._
import lt.markavl.shopingcart.algebras.payments._
import lt.markavl.shopingcart.algebras.shoppingCart._

final class CheckoutProgram[F[_]: Monad](
  paymentClient: PaymentClient[F],
  shoppingCart: ShoppingCart[F],
  orders: Orders[F]) {

  def checkout(userId: UserId, card: Card): F[OrderId] = {
    for {
      cart      <- shoppingCart.get(userId)
      paymentId <- paymentClient.process(Payment(userId, cart.total, card))
      orderId   <- orders.create(userId, paymentId, cart.items, cart.total)
      _         <- shoppingCart.delete(userId)
    } yield orderId
  }

}
