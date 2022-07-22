package ademar.ribs.tutorial.root

import com.uber.rib.core.ViewRouter

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {
}
