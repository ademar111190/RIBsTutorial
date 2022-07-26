package ademar.ribs.tutorial.loggedout

import ademar.ribs.tutorial.loggedout.LoggedOutInteractor.Listener
import ademar.ribs.tutorial.loggedout.LoggedOutInteractor.LoggedOutPresenter
import com.uber.rib.core.InteractorHelper.attach
import com.uber.rib.core.RibTestBasePlaceholder
import io.reactivex.Observable.empty
import io.reactivex.Observable.just
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class LoggedOutInteractorTest : RibTestBasePlaceholder() {

    @Mock lateinit var presenter: LoggedOutPresenter
    @Mock lateinit var router: LoggedOutRouter
    @Mock lateinit var listener: Listener

    private lateinit var interactor: LoggedOutInteractor

    @Before
    fun setup() {
        openMocks(this)
        interactor = TestLoggedOutInteractor.create(presenter, listener)
    }

    @Test
    fun attach_whenViewEmitsName_shouldCallListener() {
        whenever(presenter.loginName()).thenReturn(empty())
        whenever(presenter.loginSubmit()).thenReturn(just("fakename"))
        attach(interactor, presenter, router, null)
        verify(listener).login(anyString())
    }

    @Test
    fun attach_whenViewEmitsEmptyName_shouldNotCallListener() {
        whenever(presenter.loginName()).thenReturn(empty())
        whenever(presenter.loginSubmit()).thenReturn(just(""))
        attach(interactor, presenter, router, null)
        verifyNoInteractions(listener)
    }

}
