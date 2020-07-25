package intellect.dev.actualproject.presenter

import android.os.Handler
import android.os.Looper
import intellect.dev.actualproject.contracts.LoginContract
import java.util.concurrent.Executors

class LoginPresenter(
    private var model: LoginContract.Model,
    private var view: LoginContract.View
) : LoginContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }

    override fun clickRegistration() {
        view.toRegistration()
    }

    override fun clickLogin() {
        if (view.getLogin().toLowerCase() == "admin" && view.getPassword()
                .toLowerCase() == "password"
        ) {
            view.toAdminScreen()
            return
        }
        if (view.getLogin().isEmpty()) {
            view.setToast("Login is empty")
            return
        }
        if (view.getPassword().isEmpty()) {
            view.setToast("Password is empty")
            return
        }
        when {
            view.getLogin().length !in 4..10 -> {
                view.setToast("The length of login must be in 4..10")
                return
            }
            view.getPassword().length !in 4..10 -> {
                view.setToast("The length of password must be in 4..10")
                return
            }
            else -> {
                runOnWorkerThread {
                    val ls = model.getUsers()
                    runOnUIThread {
                        var key = 0
                        for (i in ls.indices) {
                            var user = ls[i]
                            if (view.getLogin() == user.login && view.getPassword() == user.password) {
                                view.setToast("Welcome")
                                view.toUserScreen(user.id, user.login)
                                key = 1
                            }
                        }
                        if (key == 0) {
                            view.setToast("Login or password incorrect")
                        }
                    }
                }
            }
        }
    }

    override fun clickInfo() {
        view.toInfoActivity()
    }
}