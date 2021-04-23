package com.android.rentmycar_v1.view.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val controlador by lazy {
        findNavController()
    }
    private val viewModel: LoginViewModel by viewModel()
//    private val statusAppViewModel: StatusAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val btnLogin = view?.findViewById<Button>(R.id.buttonLogin)
        btnLogin?.setOnClickListener {
            val navController = this.findNavController()
            val bundle = Bundle()
            navController.navigate(R.id.action_global_nav_all, bundle)
        }
        val btnRegister = view?.findViewById<Button>(R.id.buttonGoRegister)
        btnRegister?.setOnClickListener {
            val navController = this.findNavController()
            val bundle = Bundle()
            navController.navigate(R.id.action_loginFragment_to_registerFragment, bundle)
        }
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        statusAppViewModel.temComponentes = ComponentesVisuais()
//        buttonLogin.setOnClickListener {
//            viewModel.login()
//        }
//    }
}