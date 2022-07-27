package ademar.ribs.tutorial.loggedout

import ademar.ribs.tutorial.R
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.uber.rib.core.Initializer
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject.create
import io.reactivex.subjects.Subject

class LoggedOutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), LoggedOutInteractor.LoggedOutPresenter {

    private val loginNameSubject: Subject<String> = create()
    private val loginSubmitSubject: Subject<String> = create()

    @Initializer
    override fun onFinishInflate() {
        super.onFinishInflate()
        val editText = findViewById<EditText>(R.id.edit_text)
        findViewById<View>(R.id.login_button).setOnClickListener {
            loginSubmitSubject.onNext(editText.text.toString())
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginNameSubject.onNext(s.toString())
            }
        })
    }

    override fun loginName(): Observable<String> = loginNameSubject

    override fun loginSubmit(): Observable<String> = loginSubmitSubject

}
