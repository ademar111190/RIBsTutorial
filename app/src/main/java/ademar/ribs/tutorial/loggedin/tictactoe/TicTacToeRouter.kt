package ademar.ribs.tutorial.loggedin.tictactoe

import com.uber.rib.core.ViewRouter

class TicTacToeRouter(
    view: TicTacToeView,
    interactor: TicTacToeInteractor,
    component: TicTacToeBuilder.Component,
) : ViewRouter<TicTacToeView, TicTacToeInteractor>(view, interactor, component)
