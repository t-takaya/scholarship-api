package model.api.reads

import play.api.libs.json._

/**
 *
 */
// TODO: ログイン情報は、認証機能実装リファクタ
case class JsValueSynchroniseBalance(
  id:            String,
  password:      String,
  memberNumber1: String,
  memberNumber2: String,
  memberNumber3: String
)

object JsValueSynchroniseBalance {

  implicit val reads: Reads[JsValueSynchroniseBalance] = Json.reads[JsValueSynchroniseBalance]
}

