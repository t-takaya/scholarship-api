package model.site

/**
 *
 */
// TODO: カテゴリ毎にクラスを分離する
case class SiteScholarShip(
  loginUrl:               String,
  loggedInUrl:            String,
  myPageTopUrl:           String,
  myPageDetailUrl:        String,
  idAttribute:            String,
  passwordAttribute:      String,
  tokenNameAttribute:     String,
  tokenValueAttribute:    String,
  failedCountAttribute:   String,
  memberNumber1Attribute: String,
  memberNumber2Attribute: String,
  memberNumber3Attribute: String,
  balanceAttribute:       String,
)

object SiteScholarShip {
  // TODO: confファイルから読み込ませる
  def build(): SiteScholarShip = {
    val domain = "https://scholar-ps.sas.jasso.go.jp"
    SiteScholarShip(
      loginUrl               = s"$domain/mypage/login_open.do",
      loggedInUrl            = s"$domain/mypage/login_submit.do",
      myPageTopUrl           = s"$domain/mypage/syogkseiBgKakunin_submit.do",
      myPageDetailUrl        = s"$domain/mypage/syosaiJoho_open.do",
      idAttribute            = "userId",
      passwordAttribute      = "password",
      tokenNameAttribute     = "struts.token.name",
      tokenValueAttribute    = "token",
      failedCountAttribute   = "failedCount",
      memberNumber1Attribute = "syogkseiBg1",
      memberNumber2Attribute = "syogkseiBg2",
      memberNumber3Attribute = "syogkseiBg3",
      balanceAttribute       = "td.content-td-syosaiJoho",
    )
  }
}

