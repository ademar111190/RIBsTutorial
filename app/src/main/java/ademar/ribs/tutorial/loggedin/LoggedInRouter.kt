package ademar.ribs.tutorial.loggedin

import ademar.ribs.tutorial.loggedin.offgame.OffGameBuilder
import ademar.ribs.tutorial.loggedin.offgame.OffGameRouter
import ademar.ribs.tutorial.loggedin.tictactoe.TicTacToeBuilder
import ademar.ribs.tutorial.loggedin.tictactoe.TicTacToeRouter
import android.view.ViewGroup
import com.uber.rib.core.Router

class LoggedInRouter(
    interactor: LoggedInInteractor,
    component: LoggedInBuilder.Component,
    private val rootView: ViewGroup,
    private val offGameBuilder: OffGameBuilder,
    private val ticTacToeBuilder: TicTacToeBuilder,
) : Router<LoggedInInteractor>(interactor, component) {

    private var offGameRouter: OffGameRouter? = null
    private var ticTacToeRouter: TicTacToeRouter? = null

    override fun willDetach() {
        super.willDetach()
        detachOffGame()
        detachTicTacToe()
    }

    fun attachOffGame() {
        offGameBuilder.build(rootView).let {
            offGameRouter = it
            attachChild(it)
            rootView.addView(it.view)
        }
    }

    fun detachOffGame() {
        offGameRouter?.let {
            detachChild(it)
            rootView.removeView(it.view)
            offGameRouter = null
        }
    }

    fun attachTicTacToe() {
        ticTacToeBuilder.build(rootView).let {
            ticTacToeRouter = it
            attachChild(it)
            rootView.addView(it.view)
        }
    }

    fun detachTicTacToe() {
        ticTacToeRouter?.let {
            detachChild(it)
            rootView.removeView(it.view)
            ticTacToeRouter = null
        }
    }

}
