package demo

import slinky.core.facade.ReactElement
import typings.antdLib.esFormFormMod.default.{create => createForm}
import typings.antdLib.esFormFormMod.{FormCreateOption, GetFieldDecoratorOptions, WrappedFormUtils}
import typings.antdLib.{antdLibComponents => Antd}
import typings.reactLib.ScalableSlinky._
import typings.reactLib.ScalableSlinky.fromSt._
import typings.reactLib.reactMod.{ComponentType, ReactNode}

import scala.scalajs.js

/**
  * This is a proposal for what a slinky facade for antd could look like.
  *
  * It would obviously need to be completed, right now it just has what the demo needs.
  */
object AntdFacade {
  /* rewrites to slinky external components */
  def Select[T]: ExternalComponentP[SelectProps[T]] = Antd.Select[T].fromST
  def Table[T]:  ExternalComponentP[TableProps[T]]  = Antd.Table[T].fromST
  val Alert:     ExternalComponentP[AlertProps]     = Antd.Alert.fromST
  val Button:    ExternalComponentP[ButtonProps]    = Antd.Button.fromST
  val Col:       ExternalComponentP[ColProps]       = Antd.Col.fromST
  val Form:      ExternalComponentP[FormProps]      = Antd.Form.fromST
  val FormItem:  ExternalComponentP[FormItemProps]  = Antd.FormItem.fromST
  val Icon:      ExternalComponentP[IconProps]      = Antd.Icon.fromST
  val Input:     ExternalComponentP[InputProps]     = Antd.Input.fromST
  val Modal:     ExternalComponentP[ModalProps]     = Antd.Modal.fromST
  val Option:    ExternalComponentP[OptionProps]    = Antd.Option.fromST
  val Password:  ExternalComponentP[PasswordProps]  = Antd.Password.fromST
  val Row:       ExternalComponentP[RowProps]       = Antd.Row.fromST
  val Spin:      ExternalComponentP[SpinProps]      = Antd.Spin.fromST
  val Tag:       ExternalComponentP[TagProps]       = Antd.Tag.fromST

  /* export unchanged */
  @inline def AlertProps = typings.antdLib.esAlertMod.AlertProps
  type AlertProps = typings.antdLib.esAlertMod.AlertProps
  @inline def ButtonProps = typings.antdLib.esButtonButtonMod.ButtonProps
  type ButtonProps = typings.antdLib.esButtonButtonMod.ButtonProps
  @inline def ColProps = typings.antdLib.esGridColMod.ColProps
  type ColProps = typings.antdLib.esGridColMod.ColProps
  @inline def ColumnProps = typings.antdLib.esTableInterfaceMod.ColumnProps
  type ColumnProps[T] = typings.antdLib.esTableInterfaceMod.ColumnProps[T]
  @inline def FormItemProps = typings.antdLib.esFormFormItemMod.FormItemProps
  type FormItemProps = typings.antdLib.esFormFormItemMod.FormItemProps
  @inline def FormProps = typings.antdLib.esFormFormMod.FormProps
  type FormProps = typings.antdLib.esFormFormMod.FormProps
  @inline def IconProps = typings.antdLib.esIconMod.IconProps
  type IconProps = typings.antdLib.esIconMod.IconProps
  @inline def InputProps = typings.antdLib.esInputInputMod.InputProps
  type InputProps = typings.antdLib.esInputInputMod.InputProps
  @inline def ModalProps = typings.antdLib.esModalModalMod.ModalProps
  type ModalProps = typings.antdLib.esModalModalMod.ModalProps
  @inline def OptionProps = typings.antdLib.esSelectMod.OptionProps
  type OptionProps = typings.antdLib.esSelectMod.OptionProps
  @inline def PasswordProps = typings.antdLib.esInputPasswordMod.PasswordProps
  type PasswordProps = typings.antdLib.esInputPasswordMod.PasswordProps
  @inline def RowProps = typings.antdLib.esGridRowMod.RowProps
  type RowProps = typings.antdLib.esGridRowMod.RowProps
  @inline def SelectProps = typings.antdLib.esSelectMod.SelectProps
  type SelectProps[T] = typings.antdLib.esSelectMod.SelectProps[T]
  @inline def SpinProps = typings.antdLib.esSpinMod.SpinProps
  type SpinProps = typings.antdLib.esSpinMod.SpinProps
  @inline def TableProps = typings.antdLib.esTableInterfaceMod.TableProps
  type TableProps[T] = typings.antdLib.esTableInterfaceMod.TableProps[T]
  @inline def TagProps = typings.antdLib.esTagMod.TagProps
  type TagProps = typings.antdLib.esTagMod.TagProps

  /**
    * This is an example of something a bit more complicated than just rewriting component types, and which a manually
    *  written facade. Given an implementation of a component which has a `form` prop which is to be prefilled,
    *  this will generate a ready-to-use `ExternalComponent` for it.
    */
  def formComponent[Props <: js.Object](
      options: FormCreateOption[Props]
  )(f:         js.Function1[Props with WithForm, ReactElement]): ExternalComponentP[Props] =
    createForm(FormCreateOption[Props](name = "coordinated"))(f).asInstanceOf[ComponentType[Props]].fromST

  trait WithForm extends js.Object {
    val form: WrappedFormUtils[js.Object]
  }

  def decoratedField(form: WrappedFormUtils[js.Object], fieldName: String, options: GetFieldDecoratorOptions)(
      children:            ReactElement
  ): ReactNode =
    form.getFieldDecorator(fieldName, options).apply(children).fromST
}
