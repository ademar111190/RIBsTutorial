package ademar.ribs.tutorial.loggedin.offgame

import ademar.ribs.tutorial.R
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
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

    @Initializer
    override fun onFinishInflate() {
        super.onFinishInflate()
        button = findViewById(R.id.start_game_button)
    }

    override fun startGameRequest(): Observable<Any> = RxView.clicks(button)

}
