package model.api.writes

import play.api.libs.json._

/**
 *
 */
// TODO：命名とかリファクタ
case class JsValueSynchroniseBalance(
  numberOfRemainingTimes: String,
  remainingAmount:        String,
)

object JsValueSynchroniseBalance {

  implicit val writes: Writes[JsValueSynchroniseBalance] = Json.writes[JsValueSynchroniseBalance]
  // TODO：命名とかリファクタ
  def build(
    numberOfRemainingTimes: String,
    remainingAmount:        String,
  ): JsValueSynchroniseBalance = {
    JsValueSynchroniseBalance(
      numberOfRemainingTimes,
      remainingAmount
    )
  }
}

