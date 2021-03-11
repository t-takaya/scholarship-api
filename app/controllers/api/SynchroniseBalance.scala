package controllers.api

import model.api.reads.JsValueSynchroniseBalance
import model.site.SiteScholarShip
import org.jsoup.Connection.Method
import org.jsoup.Jsoup
import play.api.Environment
import play.api.libs.json.Json
import play.api.mvc._

/**
 * Synchronise balance
 */
class SynchroniseBalanceController @javax.inject.Inject()(implicit
  env: Environment,
  cc:  MessagesControllerComponents
) extends AbstractController(cc) {

  // --[ Methods ]--------------------------------------------------------------
  /**
   *
   */
    // TODO: 全体的にリファクタ
  def synchronise() = Action(parse.json[JsValueSynchroniseBalance]) { implicit request =>
    val post         = request.body
    val siteAttr     = SiteScholarShip.build
    val loginPageRes = {
      Jsoup.connect(siteAttr.loginUrl)
        .method(Method.GET)
        .execute()
    }
    val loginPageDoc = loginPageRes.parse()
    val tokenName    = loginPageDoc.selectFirst(s"""[name="${siteAttr.tokenNameAttribute}"]""").`val`()
    val tokenValue   = loginPageDoc.selectFirst(s"""[name="${siteAttr.tokenValueAttribute}"]""").`val`()
    val failedCount  = loginPageDoc.selectFirst(s"""[name="${siteAttr.failedCountAttribute}"]""").`val`()
    val loggedInPageRes = {
      Jsoup.connect(siteAttr.loggedInUrl)
        .data(siteAttr.idAttribute, post.id)
        .data(siteAttr.passwordAttribute, post.password)
        .data(siteAttr.tokenNameAttribute, tokenName)
        .data(siteAttr.tokenValueAttribute, tokenValue)
        .data(siteAttr.failedCountAttribute, failedCount)
        .cookies(loginPageRes.cookies())
        .method(Method.POST)
        .execute()
    }
    Jsoup.connect(siteAttr.myPageTopUrl)
      .data(siteAttr.memberNumber1Attribute, post.memberNumber1)
      .data(siteAttr.memberNumber2Attribute, post.memberNumber2)
      .data(siteAttr.memberNumber3Attribute, post.memberNumber3)
      .cookies(loggedInPageRes.cookies())
      .method(Method.POST)
      .execute()
    val myPageDetailDoc = {
      Jsoup.connect(siteAttr.myPageDetailUrl)
        .cookies(loggedInPageRes.cookies())
        .method(Method.POST)
        .execute()
        .parse()
    }
    val elements = myPageDetailDoc.select(siteAttr.balanceAttribute)
    Ok(
      Json.toJson(model.api.writes.JsValueSynchroniseBalance(
        numberOfRemainingTimes = elements.get(12).text(),
        remainingAmount        = elements.get(13).text(),
      ))
    )
  }
}
