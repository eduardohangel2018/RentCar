package com.android.rentmycar_v1.view.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.viewmodel.RegisterUserViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment() : Fragment() {

    val firebaseAuth = Firebase.auth
    private val controlador by lazy {
        findNavController()
    }

//    private val statusAppViewModel: StatusAppViewModel by sharedViewModel()
    private val viewModel: RegisterUserViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        statusAppViewModel.temComponentes = ComponentesVisuais()
        buttonRegister.setOnClickListener {
            val email = formEmailRegister.editableText?.toString()
            val password = formPasswdRegister.editableText?.toString()
            if (email != null) {
                if (password != null) {
                    viewModel.register(email, password).observe(viewLifecycleOwner, Observer {
                        it?.let { registered: Boolean ->
                            if (registered) {
                                Snackbar.make(view, "Sucesso no Cadastro", Snackbar.LENGTH_SHORT).show()
                                controlador.popBackStack()
                            } else {
                                Snackbar.make(view, "Erro no Cadastro", Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }
            controlador.popBackStack()
        }
    }
}