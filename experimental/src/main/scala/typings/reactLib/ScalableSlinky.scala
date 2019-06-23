package typings.reactLib

import org.scalablytyped.runtime.{Instantiable1, Instantiable2}
import slinky.core.{
  BuildingComponent,
  ExternalComponent,
  KeyAddingStage,
  KeyAndRefAddingStage,
  TagMod,
  WithAttrs,
  facade => S
}
import typings.reactLib.{reactMod => ST}

import scala.language.implicitConversions
import scala.scalajs.js

object ScalableSlinky {
  /* Does this not exist? Am i completely blind? */
  trait ExternalComponentP[P] extends ExternalComponent { type Props = P }

  /**
    * Support using ScalablyTyped components as slinky ExternalComponents
    *
    * We hide this in an object, because it'll be better for users if they won't have to do this
    */
  object fromSt {
    @inline implicit def fromExoticComponent[P <: js.Object](c: ST.ExoticComponent[P]): Rewriting[P] =
      new Rewriting[P](c.asInstanceOf[js.Object])
    @inline implicit def fromComponentClass[P <: js.Object](c: ST.ComponentClass[P, _]): Rewriting[P] =
      new Rewriting[P](c.asInstanceOf[js.Object])
    @inline implicit def fromInstantiable1[P <: js.Object](c: Instantiable1[P, ST.ReactElement]): Rewriting[P] =
      new Rewriting[P](c.asInstanceOf[js.Object])
    @inline implicit def fromInstantiable2[P <: js.Object](c: Instantiable2[P, _, ST.ReactElement]): Rewriting[P] =
      new Rewriting[P](c.asInstanceOf[js.Object])
    @inline implicit def fromComponentType[P <: js.Object](c: ST.ComponentType[P]): Rewriting[P] =
      new Rewriting[P](c.asInstanceOf[js.Object])
    @inline implicit def fromFc[P <: js.Object](c: ST.FunctionComponent[P]): Rewriting[P] =
      new Rewriting[P](c.asInstanceOf[js.Object])

    @inline final class Rewriting[P <: js.Object](private val comp: js.Object) extends AnyVal {
      @inline def fromST: ExternalComponentP[P] =
        new ExternalComponentP[P] {
          override val component = comp
        }
    }
  }

  @inline implicit final class FromStReactNode(val node: ST.ReactNode) {
    def fromST: TagMod[Any] = node.asInstanceOf[TagMod[Any]]
  }

  /* Support using Slinky things when a ScalablyTyped `ReactElement` is expected */
  @inline final class ToStReactElement(val elem: S.ReactElement) extends AnyVal {
    @inline def toST: ST.ReactElement = elem.asInstanceOf[ST.ReactElement]
  }

  @inline implicit def buildExternal[E, R <: js.Object](comp: BuildingComponent[E, R]): ToStReactElement =
    new ToStReactElement(BuildingComponent.make[E, R](comp))

  @inline implicit def buildWithAttrs[A](withAttrs: WithAttrs[A]): ToStReactElement =
    new ToStReactElement(WithAttrs.build(withAttrs))

  @inline implicit def buildKeyAddingState(stage: KeyAddingStage): ToStReactElement =
    new ToStReactElement(KeyAddingStage.build(stage))

  @inline final implicit def buildKeyAndRefAddingStage[D](stage: KeyAndRefAddingStage[D]): ToStReactElement =
    new ToStReactElement(KeyAndRefAddingStage.build(stage))
}
