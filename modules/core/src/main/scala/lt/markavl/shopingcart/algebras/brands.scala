package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.types.string._
import io.estatico.newtype.macros._

object brands {

  trait Brands[F[_]] {
    def findAll: F[List[Brand]]
    def create(brand: BrandName): F[Unit]
  }

  @newtype class BrandId(value: UUID)
  @newtype case class BrandName(value: NonEmptyString)

  case class Brand(id: BrandId, name: BrandName)

}
