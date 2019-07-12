package demo

import slinky.web.ReactDOM
import slinky.web.html._

import org.scalajs.dom

object Main {

  def main(argv: Array[String]): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    ReactDOM.render(App(), container)

  }

}