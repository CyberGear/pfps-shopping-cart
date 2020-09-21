package lt.markavl.shopingcart.algebras

import java.util.UUID

import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype

@newtype class BrandId(value: UUID)
@newtype case class BrandName(value: NonEmptyString)
case class Brand(id: BrandId, name: BrandName)

trait Brands[F[_]] {
  def findAll: F[List[Brand]]
  def create(brand: BrandName): F[Unit]
}
