package ademar.ribs.tutorial.loggedin.offgame

import ademar.ribs.tutorial.R
import ademar.ribs.tutorial.loggedin.offgame.OffGameInteractor.OffGamePresenter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.*
import javax.inject.Scope

class OffGameBuilder(
    dependency: ParentComponent,
) : ViewBuilder<OffGameView, OffGameRouter, OffGameBuilder.ParentComponent>(dependency) {

    fun build(parentViewGroup: ViewGroup): OffGameRouter {
        val view = createView(parentViewGroup)
        val interactor = OffGameInteractor()
        val component = DaggerOffGameBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.offGameRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): OffGameView {
        return inflater.inflate(R.layout.off_game_rib, parentViewGroup, false) as OffGameView
    }

    interface ParentComponent {
        fun listener(): OffGameInteractor.Listener
    }

    @dagger.Module
    abstract class Module {
        @[OffGameScope Binds]
        abstract fun presenter(view: OffGameView): OffGamePresenter

        companion object {
            @[OffGameScope Provides]
            fun router(
                component: Component,
                view: OffGameView,
                interactor: OffGameInteractor,
            ) = OffGameRouter(view, interactor, component)
        }
    }

    @[OffGameScope dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class],
    )]
    interface Component : InteractorBaseComponent<OffGameInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: OffGameInteractor): Builder

            @BindsInstance
            fun view(view: OffGameView): Builder
            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

        // generated code uses arg0 instead of interactor so override it here, TODO remove this when code generator fix this
        override fun inject(arg0: OffGameInteractor)
    }

    interface BuilderComponent {
        fun offGameRouter(): OffGameRouter
    }

    @[Scope Retention(AnnotationRetention.BINARY)]
    annotation class OffGameScope

}
