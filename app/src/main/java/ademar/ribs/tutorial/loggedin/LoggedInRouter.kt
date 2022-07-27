package ademar.ribs.tutorial.loggedin

import android.view.ViewGroup
import com.uber.rib.core.Router

class LoggedInRouter(
    interactor: LoggedInInteractor,
    component: LoggedInBuilder.Component,
    rootView: ViewGroup,
) : Router<LoggedInInteractor>(interactor, component) {

    fun attachOffGame() {}

    fun detachOffGame() {}

    fun attachTicTacToe() {}

}
