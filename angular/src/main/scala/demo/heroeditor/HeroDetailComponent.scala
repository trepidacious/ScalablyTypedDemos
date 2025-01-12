package demo
package heroeditor

import typings.atAngularCommonLib.atAngularCommonMod.Location
import typings.atAngularCoreLib.atAngularCoreMod.{Component, ComponentCls, OnInit, Type}
import typings.atAngularRouterLib.atAngularRouterMod.ActivatedRoute

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportStatic

final class HeroDetailComponent(
    route:       ActivatedRoute,
    heroService: HeroService,
    location:    Location
) extends OnInit {

  var hero: js.UndefOr[Hero] = js.undefined

  def getHero(): Unit =
    asOption(route.snapshot.paramMap.get("id")) match {
      case Some(id) if id.forall(_.isDigit) =>
        hero = heroService.getHero(id.toInt)
      case _ => ()
    }

  override def ngOnInit(): Unit = {
    getHero()
    println(hero)
  }

  def goBack(): Unit =
    location.back()
}

object HeroDetailComponent {
  @JSExportStatic
  val annotations = js.Array(
    new ComponentCls(
      Component(
        selector = "app-hero-detail",
        inputs   = js.Array("hero"),
        template = """
          |<div *ngIf="hero">
          |    <h2>{{hero.name | uppercase}} Details</h2>
          |    <div><span>id: </span>{{hero.id}}</div>
          |    <div>
          |        <label>name:
          |            <input [(ngModel)]="hero.name" placeholder="name"/>
          |        </label>
          |    </div>
          |    <button (click)="goBack()">go back</button>
          |</div>
        """.stripMargin,
        styles   = js.Array("""
          |/* HeroDetailComponent's private CSS styles */
          |label {
          |  display: inline-block;
          |  width: 3em;
          |  margin: .5em 0;
          |  color: #607D8B;
          |  font-weight: bold;
          |}
          |input {
          |  height: 2em;
          |  font-size: 1em;
          |  padding-left: .4em;
          |}
          |button {
          |  margin-top: 20px;
          |  font-family: Arial;
          |  background-color: #eee;
          |  border: none;
          |  padding: 5px 10px;
          |  border-radius: 4px;
          |  cursor: pointer; cursor: hand;
          |}
          |button:hover {
          |  background-color: #cfd8dc;
          |}
          |button:disabled {
          |  background-color: #eee;
          |  color: #ccc;
          |  cursor: auto;
          |}
        """.stripMargin)
      )
    )
  )

  @JSExportStatic
  val parameters: js.Array[Type[_]] = js.Array(
    typeOf[ActivatedRoute],
    typeOf[HeroService],
    typeOf[Location]
  )
}
