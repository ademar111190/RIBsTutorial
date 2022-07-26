package ademar.ribs.tutorial.root

import ademar.ribs.tutorial.loggedin.LoggedInBuilder
import ademar.ribs.tutorial.loggedout.LoggedOutBuilder
import ademar.ribs.tutorial.loggedout.LoggedOutRouter
import com.uber.rib.core.ViewRouter

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val loggedOutBuilder: LoggedOutBuilder,
    private val loggedInBuilder: LoggedInBuilder,
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {

    private var loggedOutRouter: LoggedOutRouter? = null

    fun attachLoggedOut() {
        val router = loggedOutBuilder.build(view)
        attachChild(router)
        view.addView(router.view)
        loggedOutRouter = router
    }

    fun detachLoggedOut() {
        loggedOutRouter?.let {
            detachChild(it)
            view.removeView(it.view)
            loggedOutRouter = null
        }
    }

    fun attachLoggedIn(userName: String) {
        attachChild(loggedInBuilder.build())
    }

}
