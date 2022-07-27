package ademar.ribs.tutorial.loggedin.tictactoe

import ademar.ribs.tutorial.R
import ademar.ribs.tutorial.loggedin.tictactoe.TicTacToeInteractor.TicTacToePresenter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.*
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

class TicTacToeBuilder(
    dependency: ParentComponent,
) : ViewBuilder<TicTacToeView, TicTacToeRouter, TicTacToeBuilder.ParentComponent>(dependency) {

    fun build(parentViewGroup: ViewGroup): TicTacToeRouter {
        val view = createView(parentViewGroup)
        val interactor = TicTacToeInteractor()
        val component = DaggerTicTacToeBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.ticTacToeRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): TicTacToeView {
        return inflater.inflate(R.layout.tic_tac_toe_rib, parentViewGroup, false) as TicTacToeView
    }

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @[TicTacToeScope Binds]
        abstract fun presenter(view: TicTacToeView): TicTacToePresenter

        companion object {
            @[TicTacToeScope Provides]
            fun router(
                component: Component,
                view: TicTacToeView,
                interactor: TicTacToeInteractor,
            ) = TicTacToeRouter(view, interactor, component)
        }
    }

    @[TicTacToeScope dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )]
    interface Component : InteractorBaseComponent<TicTacToeInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance fun interactor(interactor: TicTacToeInteractor): Builder
            @BindsInstance fun view(view: TicTacToeView): Builder
            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

        // generated code uses arg0 instead of interactor so override it here, TODO remove this when code generator fix this
        override fun inject(arg0: TicTacToeInteractor)
    }

    interface BuilderComponent {
        fun ticTacToeRouter(): TicTacToeRouter
    }

    @[Scope Retention(BINARY)]
    annotation class TicTacToeScope

}
