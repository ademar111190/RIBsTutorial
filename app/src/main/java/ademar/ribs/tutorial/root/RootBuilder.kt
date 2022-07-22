package ademar.ribs.tutorial.root

import ademar.ribs.tutorial.R
import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Scope

class RootBuilder(
    dependency: ParentComponent,
) : ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView {
        return inflater.inflate(R.layout.root_rib, parentViewGroup, false) as RootView
    }

    fun build(parentViewGroup: ViewGroup): RootRouter {
        val view = createView(parentViewGroup)
        val interactor = RootInteractor()
        val component: Component = DaggerRootBuilder_Component.builder()
            .parentComponent(dependency)
            .view(view)
            .interactor(interactor)
            .build()
        return component.rootRouter()
    }

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @[RootScope Binds]
        abstract fun presenter(
            view: RootView,
        ): RootInteractor.RootPresenter

        companion object {
            @[RootScope Provides]
            fun router(
                component: Component,
                view: RootView,
                interactor: RootInteractor
            ) = RootRouter(view, interactor, component)
        }
    }

    @[RootScope dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )]
    interface Component : InteractorBaseComponent<RootInteractor>, ParentComponent, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance fun interactor(interactor: RootInteractor): Builder
            @BindsInstance fun view(view: RootView): Builder
            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

        // generated code uses arg0 instead of interactor so override it here, TODO remove this when code generator fix this
        override fun inject(arg0: RootInteractor)
    }

    interface BuilderComponent {
        fun rootRouter(): RootRouter
    }

    @[Scope Retention(AnnotationRetention.BINARY)]
    annotation class RootScope

}
