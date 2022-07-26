package ademar.ribs.tutorial.loggedout

import android.util.Log
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@RibInteractor
class LoggedOutInteractor : Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter>() {

    private val subscriptions = CompositeDisposable()

    @Inject lateinit var presenter: LoggedOutPresenter
    @Inject lateinit var listener: Listener

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        subscriptions.add(presenter.loginName().subscribe({
            Log.d("Ademar", "name: $it")
        }) {
            Log.e("Ademar", "error: " + it.message)
        })
        subscriptions.add(presenter.loginSubmit().subscribe({
            if (it.isNotBlank()) {
                listener.login(it)
            }
        }) {
            Log.e("Ademar", "error: " + it.message)
        })
    }

    override fun willResignActive() {
        super.willResignActive()
        subscriptions.clear()
    }

    interface LoggedOutPresenter {
        fun loginName(): Observable<String>
        fun loginSubmit(): Observable<String>
    }

    interface Listener {
        fun login(userName: String)
    }

}
