package ademar.ribs.tutorial.root

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class RootView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs), RootInteractor.RootPresenter
