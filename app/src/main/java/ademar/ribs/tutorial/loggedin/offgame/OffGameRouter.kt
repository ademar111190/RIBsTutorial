package ademar.ribs.tutorial.loggedin.offgame

import com.uber.rib.core.ViewRouter

class OffGameRouter(
    view: OffGameView,
    interactor: OffGameInteractor,
    component: OffGameBuilder.Component,
) : ViewRouter<OffGameView, OffGameInteractor>(view, interactor, component)
