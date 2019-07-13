package demo

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import typings.semanticDashUiDashReactLib.semanticDashUiDashReactLibStrings

import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js

// TODO This needs some webpack tweaks, avoids need for link in html
//@JSImport("semantic-ui-css/semantic.min.css", JSImport.Default)
//@js.native
//object SUICSS extends js.Any

@react object App {

  import SuiFacade._

  type Props = Unit

  //  private val suiCSS = SUICSS

  val component = FunctionalComponent[Props] { _ =>
    //    val (isModalVisible, updateIsModalVisible) = useState(false)
    //    val (selectValue, updateSelectValue) = useState("lucy")

    // val renderIntro = Row(RowProps())(
    //   Col(ColProps(span = 7)),
    //   Col(ColProps(span = 10))(
    //     header(className := "App-header")(
    //       img(src := ReactLogo.asInstanceOf[String], className := "App-logo", alt := "logo"),
    //       h1(className := "App-title")("Welcome to React (with Scala.js!)")
    //     ),
    //     p(className := "App-intro")(
    //       "To get started, edit ", code("App.scala"), " and save to reload."
    //     )
    //   ),
    //   Col(ColProps(span = 7))
    // )

    val renderCard = section(
      Header(HeaderProps(as = "h2"))("Card"),
      Card(CardProps(color = Strings.orange))("Card!")
    )

    val renderButton = section(
      Header(HeaderProps(as = "h2"))("Button"),
      Button(ButtonProps(
        primary = true
      ))("Download")
    )

    val renderProgress = section(
      Header(HeaderProps(as = "h2"))("Progress"),
      Progress(ProgressProps(
        percent = 70
      ))("Download")
    )

    val renderInput = section(
      Header(HeaderProps(as = "h2"))("Input"),
      Input(InputProps())
    )

    val renderIcon = section(
      Header(HeaderProps(as = "h2"))("Icon"),
      Icon(IconProps(disabled = true, name = semanticDashUiDashReactLibStrings.users))
    )

    div(className := "App")(
      // renderIntro,
      div(
        renderCard,
        renderInput,
        renderProgress,
        renderButton,
        renderIcon
      )
    )
  }
}
