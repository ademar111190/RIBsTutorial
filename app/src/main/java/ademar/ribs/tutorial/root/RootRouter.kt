package ademar.ribs.tutorial.root

import ademar.ribs.tutorial.loggedout.LoggedOutBuilder
import com.uber.rib.core.ViewRouter

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    var loggedOutBuilder: LoggedOutBuilder,
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {

    fun attachLoggedOut() {
        val router = loggedOutBuilder.build(view)
        attachChild(router)
        view.addView(router.view)
    }

    fun detachLoggedOut() {
    }

    fun attachLoggedIn(userName: String) {
    }

}
