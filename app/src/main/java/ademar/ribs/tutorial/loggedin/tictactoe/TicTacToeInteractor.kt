package ademar.ribs.tutorial.loggedin.tictactoe

import ademar.ribs.tutorial.loggedin.tictactoe.MarkerType.CROSS
import ademar.ribs.tutorial.loggedin.tictactoe.MarkerType.NOUGHT
import ademar.ribs.tutorial.loggedin.tictactoe.TicTacToeInteractor.TicTacToePresenter
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@RibInteractor
class TicTacToeInteractor : Interactor<TicTacToePresenter, TicTacToeRouter>() {

    private val subscriptions = CompositeDisposable()

    @Inject lateinit var board: Board
    @Inject lateinit var presenter: TicTacToePresenter

    private val playerOne = "Ada"
    private val playerTwo = "Chacha"
    private var currentPlayer = CROSS

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        subscriptions.add(
            presenter
                .squareClicks()
                .subscribe { xy ->
                    val (x, y) = xy
                    if (board.cells[x][y] == null) {
                        if (currentPlayer === CROSS) {
                            board.cells[x][y] = CROSS
                            board.currentRow = x
                            board.currentCol = y
                            presenter.addCross(xy)
                            currentPlayer = NOUGHT
                        } else {
                            board.cells[x][y] = NOUGHT
                            board.currentRow = x
                            board.currentCol = y
                            presenter.addNought(xy)
                            currentPlayer = CROSS
                        }
                    }
                    if (board.hasWon(CROSS)) {
                        presenter.setPlayerWon(playerOne)
                    } else if (board.hasWon(NOUGHT)) {
                        presenter.setPlayerWon(playerTwo)
                    } else if (board.isDraw()) {
                        presenter.setPlayerTie()
                    } else {
                        updateCurrentPlayer()
                    }
                }
        )
        updateCurrentPlayer()
    }

    override fun willResignActive() {
        super.willResignActive()
        subscriptions.clear()
    }

    private fun updateCurrentPlayer() {
        if (currentPlayer === CROSS) {
            presenter.setCurrentPlayerName(playerOne)
        } else {
            presenter.setCurrentPlayerName(playerTwo)
        }
    }

    interface TicTacToePresenter {
        fun squareClicks(): Observable<BoardCoordinate>
        fun setCurrentPlayerName(currentPlayer: String)
        fun setPlayerWon(playerName: String)
        fun setPlayerTie()
        fun addCross(xy: BoardCoordinate)
        fun addNought(xy: BoardCoordinate)
    }

    interface Listener

}
