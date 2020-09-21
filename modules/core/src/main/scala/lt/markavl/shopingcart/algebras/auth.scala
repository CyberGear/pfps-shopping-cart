package lt.markavl.shopingcart.algebras

import java.util._

import eu.timepit.refined.api._
import eu.timepit.refined.string.MatchesRegex
import eu.timepit.refined.types.string._
import io.estatico.newtype.macros._

object auth {

  trait Auth[F[_]] {
    def find(token: JwtToken): F[Option[User]]
    def newUser(username: UserName, password: Password): F[JwtToken]
    def login(username: UserName, password: Password): F[JwtToken]
    def logout(token: JwtToken, username: UserName): F[Unit]
  }

  trait Users[F[_]] {
    def find(username: UserName, password: Password): F[Option[User]]
    def create(userName: UserName, password: Password): F[UserId]
  }

  @newtype case class UserId(value: UUID)
  @newtype case class UserName(value: String Refined MatchesRegex[""".+@.+"""])
  @newtype case class JwtToken(value: NonEmptyString)
  @newtype case class Password(
    value: String Refined MatchesRegex[
      """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\]).{8,32}$""",
    ])

  case class User(id: UserId, username: UserName)
}
