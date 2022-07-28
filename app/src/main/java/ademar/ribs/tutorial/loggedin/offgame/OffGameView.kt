package ademar.ribs.tutorial.loggedin.offgame

import ademar.ribs.tutorial.R
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.uber.rib.core.Initializer
import io.reactivex.Observable


class OffGameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), OffGameInteractor.OffGamePresenter {

    private lateinit var button: Button
    private lateinit var playerOneName: TextView
    private lateinit var playerTwoName: TextView
    private lateinit var playerOneScore: TextView
    private lateinit var playerTwoScore: TextView

    @Initializer
    override fun onFinishInflate() {
        super.onFinishInflate()
        button = findViewById(R.id.start_game_button)
        playerOneName = findViewById(R.id.player_one_name)
        playerTwoName = findViewById(R.id.player_two_name)
        playerOneScore = findViewById(R.id.player_one_win_count)
        playerTwoScore = findViewById(R.id.player_two_win_count)
    }

    override fun startGameRequest(): Observable<Any> = RxView.clicks(button)

}
