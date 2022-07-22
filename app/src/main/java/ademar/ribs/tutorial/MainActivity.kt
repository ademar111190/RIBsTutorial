package ademar.ribs.tutorial

import ademar.ribs.tutorial.root.RootBuilder
import ademar.ribs.tutorial.root.RootBuilder.ParentComponent
import android.view.ViewGroup
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class MainActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        return RootBuilder(object : ParentComponent {}).build(parentViewGroup)
    }
}
