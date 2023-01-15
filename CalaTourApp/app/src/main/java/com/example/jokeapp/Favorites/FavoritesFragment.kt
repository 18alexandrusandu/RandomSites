package com.example.jokeapp.Favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokeapp.databinding.FavoritesFragmentBinding
import com.example.jokeapp.offerdetails.FavoritesViewModel


class FavoritesFragment : Fragment() {

    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoritesViewModel>()


    private val adapter =FavoritesAdapter(
        object: FavoritesAdapter.JokesClickListener {
            override fun makeDialogReport() {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder
                    .setTitle("Please confirm")
                    .setMessage("Are you sure you want to report it?")
                    .setPositiveButton("Confirm") { _, _ ->

                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show()
            }

            override fun removeFromFavorite(id:Int) {
                viewModel.onRemoveFavorites(id)
            }

        })








    private lateinit var onBackPressedCallback: OnBackPressedCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {

                    findNavController().navigateUp()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritesFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
    }




    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.autoUpdate()
            binding.errors.text="Favorites list is empty"
            viewModel.viewState.collect { state ->
                binding.errors.visibility=
                  if(state.isListEmpty)
                     View.VISIBLE
                   else
                     View.GONE


                adapter.setData(state.jokes)


            }
        }
    }
    private fun setupView()
    {
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@FavoritesFragment.adapter
        }



        binding.clear.text="Clear All"
        binding.clear.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder
                .setTitle("Please confirm")
                .setMessage("Are you sure you want to report it?")
                .setPositiveButton("Confirm") { _, _ ->
                  viewModel.onClearFavorites()
                }
                .setNegativeButton("Cancel", null)
                .create()
                .show()

        }

        binding.toolbar.setNavigationOnClickListener {

            onBackPressedCallback.handleOnBackPressed()


        }

       binding.toolbar.setOnMenuItemClickListener{
           menuItem->

          when( menuItem.itemId )
          {

              else -> false

          }


       }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}