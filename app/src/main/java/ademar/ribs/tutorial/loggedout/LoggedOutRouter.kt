package ademar.ribs.tutorial.loggedout

import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewRouter

class LoggedOutRouter(
    view: LoggedOutView,
    interactor: LoggedOutInteractor,
    component: InteractorBaseComponent<*>,
) : ViewRouter<LoggedOutView, LoggedOutInteractor>(view, interactor, component)
