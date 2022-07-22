package ademar.ribs.tutorial.root

import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

    @Inject lateinit var resenter: RootPresenter

    interface RootPresenter

}
