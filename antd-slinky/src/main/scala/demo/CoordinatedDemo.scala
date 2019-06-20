package demo

import demo.ScalableSlinky._
import org.scalajs.dom.console
import slinky.core.{ExternalComponentNoProps, FunctionalComponent, ReactComponentClass}
import typings.antdLib.antdLibComponents.{Button, FormItem, Input, Option, Select, Form => FormAlt}
import typings.antdLib.antdLibStrings
import typings.antdLib.esButtonButtonMod.ButtonProps
import typings.antdLib.esFormFormItemMod.FormItemProps
import typings.antdLib.esFormFormMod.{
  FormCreateOption,
  FormProps,
  GetFieldDecoratorOptions,
  ValidationRule,
  WrappedFormUtils,
  default => Form
}
import typings.antdLib.esGridColMod.ColProps
import typings.antdLib.esSelectMod.{OptionProps, SelectProps}
import typings.reactLib.reactMod.{ComponentType, FormEventHandler}

import scala.scalajs.js
import scala.scalajs.js.JSON

object CoordinatedDemo {

  case class Props(form: WrappedFormUtils[js.Object])

  val Base = FunctionalComponent[Props] { props =>
    val handleSubmit: FormEventHandler[js.Any] = e => {
      e.preventDefault()
      props.form.validateFields((err, values) => {
        if (err == null) {
          console.log("Received values of form: " + JSON.stringify(values))
        }
      })
    }

    def handleSelectChange(value: String, option: Any): Unit = {
      console.log(value)
      props.form.setFieldsValue(new js.Object {
        val note = "Hi, " + { if (value == "male") "man" else "lady" } + "!"
      })
    }

    val noteInput = props.form
      .getFieldDecorator(
        "note",
        GetFieldDecoratorOptions(
          rules = js.Array(ValidationRule(required = true, message = "Please input your note!"))
        )
      )
      .apply(Input.noprops().toST)

    val genderInput = props.form
      .getFieldDecorator(
        "gender",
        GetFieldDecoratorOptions(
          rules = js.Array(ValidationRule(required = true, message = "Please select your gender!'"))
        )
      )
      .apply(
        Select[String]
          .props(
            SelectProps(
              placeholder = "Select a option and change input text above",
              onChange    = handleSelectChange
            )
          )(
            Option.props(OptionProps(value = "male"))("male"),
            Option.props(OptionProps(value = "female"))("female")
          )
          .toST
      )

    FormAlt.props(
      FormProps(labelCol = ColProps(span = 5), wrapperCol = ColProps(span = 12), onSubmit = handleSubmit)
    )(
      FormItem.props(FormItemProps(label      = "Note"))(noteInput.fromST),
      FormItem.props(FormItemProps(label      = "Gender"))(genderInput.fromST),
      FormItem.props(FormItemProps(wrapperCol = ColProps(span = 12, offset = 5)))(
        Button.props(ButtonProps(`type` = antdLibStrings.primary, htmlType = antdLibStrings.submit))("Submit")
      )
    )
  }

  val Component = Form
    .create(FormCreateOption(name = "coordinated"))(ReactComponentClass.functionalComponentToClass(Base))
    .asInstanceOf[ComponentType[js.Object]] // drop too complex type
}

object CoordinatedControls extends ExternalComponentNoProps {
  override val component = CoordinatedDemo.Component
}
