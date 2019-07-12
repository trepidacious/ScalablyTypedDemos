package demo

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.LinkingInfo

import slinky.core._
import slinky.web.ReactDOM

import org.scalajs.dom

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.web.html._

object Main {

  def main(argv: Array[String]): Unit = {
    // Knowledge.asOption(window.document.getElementById("container")) match {
    //   case None            =>
    //   case Some(container) =>
    //     /* set up redux store with devtools*/
    //     val Store: Store[GithubSearch.State, GithubSearch.SearchAction] =
    //       createStore(GithubSearch.Reducer, devToolsEnhancer(EnhancerOptions(name = "github search store")))

    //     /* Connect `Demo` component */
    //     val ConnectedDemo: FC[Demo.Props] =
    //       ReduxFacade.simpleConnect(Store, Demo.C)

    //     /* And use `Provider` instead of just passing a normal, goddamn parameter */
    //     render(
    //       Provider[GithubSearch.SearchAction].props(
    //         reactDashReduxMod.ProviderProps[GithubSearch.SearchAction](store = Store),
    //         ConnectedDemo.props(new Demo.Props("Welcome"))
    //       ),
    //       container
    //     )
    // }

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    // ReactDOM.render(App(), container)
    ReactDOM.render(
      div()(
        SuiFacade.Header(SuiFacade.HeaderProps(as="h2"))("Header!"),
        SuiFacade.Card(SuiFacade.CardProps())("Hi!"),
        SuiFacade.Button(SuiFacade.ButtonProps())("Button!")
      )
    , container)
    

  }

}