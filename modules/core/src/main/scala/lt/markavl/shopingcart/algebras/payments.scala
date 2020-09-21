package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.api._
import eu.timepit.refined.string.MatchesRegex
import io.estatico.newtype.macros._
import squants.market._

@newtype case class PaymentId(value: UUID)
@newtype case class Card(
  name: String Refined MatchesRegex[".+ .+"],
  number: String Refined MatchesRegex["""(?:\d{4}(?: |)){4}"""],
  expiration: String Refined MatchesRegex["""\d\d/\d\d"""],
  cvv: String Refined MatchesRegex["""\d\d\d"""])

case class Payment(id: UserId, total: Money, card: Card)

trait PaymentClient[F[_]] {
  def process(payment: Payment): F[PaymentId]
}
