package com.example.mob_app_kotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mob_app_kotlin.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            if(!binding.etUsr.text.toString().isNullOrEmpty() && !binding.etPass.text.toString().isNullOrEmpty()){
                Toast.makeText(context, "Hi:" + binding.etUsr.text.toString(), Toast.LENGTH_LONG).show()

                // findNavController().navigate(R.id.action_loginFragment_to_listFragment) Without Parameter

                // With parameter
                var action = LoginFragmentDirections.actionLoginFragmentToListFragment(binding.etUsr.text.toString())
                findNavController().navigate(action)
            }
        }
    }
}