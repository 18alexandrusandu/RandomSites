package com.example.jokeapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jokeapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        binding.loginButton.setOnClickListener {
            viewModel.onLogin()
        }

        binding.usernameInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onUsernameChanged(text.toString())
        }

        binding.passwordInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.onPasswordChanged(text.toString())
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect {
                when (it.action) {
                    LoginViewAction.LoggedIn -> {


                        val direction = LoginFragmentDirections.actionLoginFragmentToJokesFragment3(it.username)
                        findNavController().navigate(direction)

                    }
                    is LoginViewAction.ShowInputErrors -> {

                        if(it.action.usernameError!=null)
                        {
                            binding.errorsUsername.visibility=View.VISIBLE
                            if(it.action.usernameError==InputErrorType.TooShort)
                                binding.errorsUsername.text="Username too short"
                            if(it.action.usernameError==InputErrorType.Empty)
                                binding.errorsUsername.text="Username field is empty"
                            if(it.action.usernameError==InputErrorType.Invalid)
                                binding.errorsUsername.text="Username is invalid"

                        }
                        else
                            binding.errorsUsername.visibility=View.GONE


                        if(it.action.passwordError!=null)
                        {
                            binding.errorsPassword.visibility=View.VISIBLE
                            if(it.action.passwordError==InputErrorType.TooShort)
                                binding.errorsPassword.text="Password too short"
                            if(it.action.passwordError==InputErrorType.Empty)
                                binding.errorsPassword.text="Password field is empty"
                            if(it.action.passwordError==InputErrorType.Invalid)
                                binding.errorsPassword.text="Password is invalid"

                        }
                        else
                            binding.errorsPassword.visibility=View.GONE


                    }
                    null -> Unit
                }
            }
        }
    }


}