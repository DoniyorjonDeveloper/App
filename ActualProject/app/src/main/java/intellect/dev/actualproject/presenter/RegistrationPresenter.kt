package intellect.dev.actualproject.presenter

import android.os.Handler
import android.os.Looper
import intellect.dev.actualproject.contracts.RegistrationContract
import intellect.dev.actualproject.room.entities.UserData
import java.util.*
import java.util.concurrent.Executors

class RegistrationPresenter(
    private val model: RegistrationContract.Model,
    private val view: RegistrationContract.View
) : RegistrationContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())
    override fun clickRegistration() {
        if (view.getLogin().toLowerCase(Locale.ROOT) == "admin" && view.getPassword()
                .toLowerCase(Locale.getDefault()) == "password"
        ) {
            view.setToast("Soryy that indate private")
            return
        }
        when {
            view.getLogin().length !in 4..10 -> {
                view.setToast("the length must be in 4..10")
            }
            view.getPassword().length !in 4..10 -> {
                view.setToast("the length must be in 4..10")
            }
            view.getConfirmPassword() != view.getPassword() -> {
                view.setToast("Confirm password doesn't correspond to password")
            }
            else -> {
                val user = UserData(0, view.getLogin(), view.getPassword())
                runOnWorkerThread {
                    model.insertUser(user)
                }
                view.setToast("Succesfull registered")
                view.close()
            }
        }
    }

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }
}