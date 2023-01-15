package com.example.jokeapp.jokes


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokeapp.R
import com.example.jokeapp.databinding.JokesFragmentBinding
import com.example.jokeapp.models.Joke
import com.example.jokeapp.offerdetails.FavoritesViewModel
import java.util.*


class JokesFragment : Fragment() {

    private var _binding: JokesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<JokesViewModel>()
    private val viewModelShared by viewModels<FavoritesViewModel>()

    private val adapter = JokesAdapter(
       object: JokesAdapter.JokesClickListener{
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
           override fun addToFavorite(joke: Joke) {
             viewModel.onMakeFavorite(joke.id!!)
             viewModelShared.onAddFavorites(joke)
           }
           override fun removeFromFavorite(id:Int) {
               viewModel.onRemoveFavorite(id)
               viewModelShared.onRemoveFavorites(id)
           }
        }
               )

    private lateinit var onBackPressedCallback: OnBackPressedCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder
                .setTitle("Please confirm")
                .setMessage("Are you sure?")
                .setPositiveButton("Sign out") { _, _ ->
                    findNavController().navigateUp()
                }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = JokesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       System.out.println("Saved instance:" +savedInstanceState.toString());
        setupView()
        observeState()
    }

    private fun setupView() {
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@JokesFragment.adapter
        }

          viewModel.onReadAll();
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedCallback.handleOnBackPressed()
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
               R.id.All ->
               {  viewModel.onReadAll()
                   viewModel.onPressMenuItem(R.id.All)
                   true
               }
               R.id.christmas ->
               {
                   viewModel.onReadChristmas()
                   viewModel.onPressMenuItem(R.id.christmas)
               //set_data
                   true
               }
                R.id.pun ->
                {
                    viewModel.onReadPun()
                    viewModel.onPressMenuItem(R.id.pun)
                    true
                }
                R.id.programming ->
                {  viewModel.onReadProgramming()
                    viewModel.onPressMenuItem( R.id.programming)
                    true
                }
                R.id.dark ->
                {  viewModel.onReadDark()
                    viewModel.onPressMenuItem( R.id.dark)
                    true
                }
                R.id.spooky ->
                {  viewModel.onReadSpooky()
                    viewModel.onPressMenuItem(R.id.spooky )
                    true
                }
                R.id.misc ->
                {
                    viewModel.onReadMisc()
                    viewModel.onPressMenuItem( R.id.misc)
                    true
                }
                R.id.favorites->
                {
                  val direction=  JokesFragmentDirections.actionJokesFragment3ToFavoritesFragment()
                    findNavController().navigate(direction)
                    viewModel.onPressMenuItem(R.id.favorites)
                    true
                }
                else -> false
            }
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { viewState ->
                adapter.setData(viewState.jokes)

                if(viewState.ConnectionError==true)
                {
                    binding.eroare.setText("Connection error can't acces data check your internet connection")
                    binding.eroare.visibility=View.VISIBLE
                }
                else
                {
                    binding.eroare.setText("Connection error can't acces data check your internet connection")
                    binding.eroare.visibility=View.GONE
                }

                binding.nameUser.text="Welcome "+viewModel.name
                if(viewState.activeOption>0) {

                    val item1: MenuItem ?= binding.toolbar.menu.findItem(viewState.activeOption)
                    if(viewState.previousOption>0) {
                        val item2: MenuItem? = binding.toolbar.menu.findItem(viewState.previousOption)
                        val text = SpannableStringBuilder()
                        text.append(item2?.title)
                        text.setSpan(
                            ForegroundColorSpan(Color.BLACK),
                            0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )


                        item2?.setTitle(text)
                    }




                    val text = SpannableStringBuilder()
                    text.append(item1?.title)
                    text.setSpan(
                          ForegroundColorSpan(Color.RED),
                        0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )


                    item1?.setTitle(text)



                }

                binding.loading.visibility=
                    if(viewState.isLoading)
                        View.VISIBLE
                       else
                       View.GONE

            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}