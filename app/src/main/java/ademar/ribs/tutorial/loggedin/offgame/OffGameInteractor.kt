package ademar.ribs.tutorial.loggedin.offgame

import com.uber.rib.core.Interactor
import io.reactivex.Observable
import javax.inject.Inject

class OffGameInteractor : Interactor<OffGameInteractor.OffGamePresenter, OffGameRouter>() {

    @Inject lateinit var listener: Listener
    @Inject lateinit var presenter: OffGamePresenter

    interface Listener {
        fun onStartGame()
    }

    interface OffGamePresenter {
        fun startGameRequest(): Observable<Any>
    }

}
