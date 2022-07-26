package ademar.ribs.tutorial.loggedin

import ademar.ribs.tutorial.root.RootView
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

class LoggedInBuilder(
    dependency: ParentComponent,
) : Builder<LoggedInRouter, LoggedInBuilder.ParentComponent>(dependency) {

    fun build(): LoggedInRouter {
        val interactor = LoggedInInteractor()
        val component:Component = DaggerLoggedInBuilder_Component.builder()
            .parentComponent(dependency)
            .interactor(interactor)
            .build()
        return component.loggedInRouter()
    }

    interface ParentComponent {
        fun rootView(): RootView
    }

    @dagger.Module
    object Module {
        @[LoggedInScope Provides]
        fun presenter() = EmptyPresenter()

        @[LoggedInScope Provides]
        fun router(
            component: Component,
            interactor: LoggedInInteractor,
            rootView: RootView,
        ) = LoggedInRouter(
            interactor,
            component,
            rootView,
        )
    }

    @[LoggedInScope dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )]
    interface Component : InteractorBaseComponent<LoggedInInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance fun interactor(interactor: LoggedInInteractor): Builder
            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

        // generated code uses arg0 instead of interactor so override it here, TODO remove this when code generator fix this
        override fun inject(arg0: LoggedInInteractor)
    }

    interface BuilderComponent {
        fun loggedInRouter(): LoggedInRouter
    }

    @[Scope Retention(BINARY)]
    annotation class LoggedInScope

}
