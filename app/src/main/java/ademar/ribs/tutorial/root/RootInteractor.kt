package ademar.ribs.tutorial.root

import ademar.ribs.tutorial.loggedout.LoggedOutInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachLoggedOut()
    }

    interface RootPresenter

    inner class LoggedOutListener : LoggedOutInteractor.Listener {
        override fun login(userName: String) {
            router.detachLoggedOut()
            router.attachLoggedIn(userName)
        }
    }

}
