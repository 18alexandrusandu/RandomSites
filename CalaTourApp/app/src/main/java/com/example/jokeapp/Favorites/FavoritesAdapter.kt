package com.example.jokeapp.Favorites

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.databinding.FragmentJokesListItemBinding
import com.example.jokeapp.models.Joke
import com.example.jokeapp.models.Joke1
import com.example.jokeapp.models.Joke2

class FavoritesAdapter(
    val jokeClickListener: JokesClickListener

) : RecyclerView.Adapter<FavoritesAdapter.JokesViewHolder>() {

    interface JokesClickListener {
        fun makeDialogReport()
        fun removeFromFavorite(id:Int)
    }

    private val dataSource = mutableListOf<Joke>()

    fun setData(jokes: List<Joke>) {
        dataSource.clear()
        dataSource.addAll(jokes)
        notifyDataSetChanged()
    }

    inner class JokesViewHolder(
        val binding: FragmentJokesListItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val binding = FragmentJokesListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return JokesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = dataSource[position]

        with(holder) {
            binding.addFavorites.text="Remove favorites"
            binding.report.text="Report"

            binding.favorite.setImageResource(R.drawable.gradefill)

            binding.favorite.setOnClickListener()
            {
                if(joke.isFavorite!!)
                {
                    binding.favorite.setImageResource(R.drawable.grade)
                    //toggle is favorite
                    jokeClickListener.removeFromFavorite(joke.id!!)

                }

            }
            binding.addFavorites.setOnClickListener()
            {
                if(joke.isFavorite!!)
                {
                    binding.favorite.setImageResource(R.drawable.grade)
                    //toggle is favorite
                    jokeClickListener.removeFromFavorite(joke.id!!)

                }

            }
            binding.report.setOnClickListener()
            {
                jokeClickListener.makeDialogReport()
            }

            binding.id.text="ID:,${joke.id}"
            if(joke.type=="single")
            {
                val joke2: Joke1 =joke as Joke1
                binding.setup.text=joke2.joke
                binding.setup.setTextColor(Color.YELLOW)
                binding.delivery.text=" "
            }
            else {
                val joke2: Joke2 =joke as Joke2
                binding.setup.text= joke2.setup
                binding.delivery.text = joke2.delivery
                binding.setup.setTextColor(Color.YELLOW)
                binding.delivery.setTextColor(Color.WHITE)
            }
        }
    }

    override fun getItemCount(): Int = dataSource.size
}