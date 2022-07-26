package ademar.ribs.tutorial.loggedout

import ademar.ribs.tutorial.R
import ademar.ribs.tutorial.loggedout.LoggedOutInteractor.LoggedOutPresenter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.*
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

class LoggedOutBuilder(
    dependency: ParentComponent,
) : ViewBuilder<LoggedOutView, LoggedOutRouter, LoggedOutBuilder.ParentComponent>(dependency) {

    fun build(parentViewGroup: ViewGroup): LoggedOutRouter {
        val view = createView(parentViewGroup)
        val interactor = LoggedOutInteractor()
        val component: Component = DaggerLoggedOutBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.loggedOutRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LoggedOutView {
        return inflater.inflate(R.layout.logged_out_rib, parentViewGroup, false) as LoggedOutView
    }

    interface ParentComponent {
        fun listener(): LoggedOutInteractor.Listener
    }

    @dagger.Module
    abstract class Module {
        @[LoggedOutScope Binds]
        abstract fun presenter(view: LoggedOutView): LoggedOutPresenter

        companion object {
            @[LoggedOutScope Provides]
            fun router(
                component: Component,
                view: LoggedOutView,
                interactor: LoggedOutInteractor
            ) = LoggedOutRouter(view, interactor, component)
        }
    }

    @[LoggedOutScope dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )]
    interface Component : InteractorBaseComponent<LoggedOutInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance fun interactor(interactor: LoggedOutInteractor): Builder
            @BindsInstance fun view(view: LoggedOutView): Builder
            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

        // generated code uses arg0 instead of interactor so override it here, TODO remove this when code generator fix this
        override fun inject(arg0: LoggedOutInteractor)
    }

    interface BuilderComponent {
        fun loggedOutRouter(): LoggedOutRouter
    }

    @[Scope Retention(BINARY)]
    annotation class LoggedOutScope

}
