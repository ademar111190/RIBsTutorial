package ademar.ribs.tutorial.loggedin

import ademar.ribs.tutorial.loggedin.offgame.OffGameInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor


class LoggedInInteractor: Interactor<EmptyPresenter, LoggedInRouter>() {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachOffGame()
    }

    inner class OffGameListener : OffGameInteractor.Listener {
        override fun onStartGame() {
            router.detachOffGame()
            router.attachTicTacToe()
        }
    }

}
