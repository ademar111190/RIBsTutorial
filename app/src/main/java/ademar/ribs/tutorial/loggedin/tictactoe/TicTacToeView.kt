package ademar.ribs.tutorial.loggedin.tictactoe

import ademar.ribs.tutorial.R
import ademar.ribs.tutorial.loggedin.tictactoe.TicTacToeInteractor.TicTacToePresenter
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jakewharton.rxbinding2.view.RxView
import com.uber.rib.core.Initializer
import io.reactivex.Observable

class TicTacToeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), TicTacToePresenter {

    private lateinit var titleView: TextView
    private lateinit var imageButtons: Array<Array<TextView>>

    @Initializer
    override fun onFinishInflate() {
        super.onFinishInflate()
        titleView = findViewById(R.id.title)
        imageButtons = arrayOf(
            arrayOf(
                findViewById(R.id.button11),
                findViewById(R.id.button12),
                findViewById(R.id.button13),
            ),
            arrayOf(
                findViewById(R.id.button21),
                findViewById(R.id.button22),
                findViewById(R.id.button23),
            ),
            arrayOf(
                findViewById(R.id.button31),
                findViewById(R.id.button32),
                findViewById(R.id.button33),
            ),
        )
    }

    override fun squareClicks(): Observable<BoardCoordinate> {
        val observables = ArrayList<Observable<BoardCoordinate>>()
        for (i in 0..2) {
            for (j in 0..2) {
                observables.add(RxView.clicks(imageButtons[i][j]).map { BoardCoordinate(i, j) })
            }
        }
        return Observable.merge(observables)
    }

    override fun addCross(xy: BoardCoordinate) {
        val textView = imageButtons[xy.x][xy.y]
        textView.text = "x"
        textView.isClickable = false
    }

    override fun addNought(xy: BoardCoordinate) {
        val textView = imageButtons[xy.x][xy.y]
        textView.text = "O"
        textView.isClickable = false
    }

    override fun setCurrentPlayerName(currentPlayer: String) {
        titleView.text = context.getString(R.string.current_player, currentPlayer)
    }

    override fun setPlayerWon(playerName: String) {
        titleView.text = context.getString(R.string.player_won, playerName)
    }

    override fun setPlayerTie() {
        titleView.text = context.getString(R.string.tie_game)
    }

}
