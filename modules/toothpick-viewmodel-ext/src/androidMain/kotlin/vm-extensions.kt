package libs.toothpick.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import toothpick.Scope
import toothpick.ktp.KTP


/**
 * Created at February 2020
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

class NamedScopeViewModelFactory(private val scopeName: Any) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        if (KTP.isScopeOpen(scopeName)) {
            KTP.openScope(scopeName).getInstance(modelClass) as T
        } else {
            KTP.openRootScope().openSubScope(scopeName).getInstance(modelClass) as T
        }
}

class ScopedViewModelFactory(private val scope: Scope) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = scope.getInstance(modelClass) as T
}

/**
 * Created a viewModel with auto-generated scope.
 */
inline fun <reified T : ViewModel> ViewModelStoreOwner.viewModel(): Lazy<T> = lazy {
    ViewModelProvider(this, NamedScopeViewModelFactory(this::class.java)).get(T::class.java)
}

/**
 * Creates a viewModel from provided scope.
 *
 * @param scope from which viewModel will be injected.
 */
inline fun <reified T : ViewModel> ViewModelStoreOwner.viewModel(scope: Scope): Lazy<T> = lazy {
    ViewModelProvider(this, ScopedViewModelFactory(scope)).get(T::class.java)
}
