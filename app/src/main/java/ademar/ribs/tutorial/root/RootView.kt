package ademar.ribs.tutorial.root

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class RootView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), RootInteractor.RootPresenter
