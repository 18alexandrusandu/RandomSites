package com.example.jokeapp.jokes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat.ACTION_HOVER_ENTER
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapp.R
import com.example.jokeapp.R.*
import com.example.jokeapp.databinding.FragmentJokesListItemBinding
import com.example.jokeapp.models.Joke
import com.example.jokeapp.models.Joke1
import com.example.jokeapp.models.Joke2

class JokesAdapter(


    val jokeClickListener:JokesClickListener

) : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    interface JokesClickListener {
       fun makeDialogReport()
       fun addToFavorite(joke:Joke)
       fun removeFromFavorite(id:Int)
    }

    private val dataSource = mutableListOf<Joke>()

    fun setData(jokes: List<Joke>) {
        dataSource.clear()
        dataSource.addAll(jokes)
        notifyDataSetChanged()
    }

    inner class JokesViewHolder(
        val binding: FragmentJokesListItemBinding,
        val context:Context
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val binding =FragmentJokesListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false

        )
        val context=parent.context
        return JokesViewHolder(binding,context)
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = dataSource[position]


        with(holder) {
            binding.addFavorites.text = "Add to favorites"
            binding.report.text = "Report"
            if (joke.isFavorite!!) {
                binding.favorite.setImageResource(drawable.gradefill)
            } else {
                binding.favorite.setImageResource(drawable.grade)

            }

            binding.addFavorites.setOnClickListener()
            {
                binding.favorite.setImageResource(drawable.gradefill)
                //toggle is favorite
                jokeClickListener.addToFavorite(joke)


            }


            binding.favorite.setOnClickListener()
            {
                if (joke.isFavorite!!) {
                    binding.favorite.setImageResource(drawable.grade)
                    //toggle is favorite
                    jokeClickListener.removeFromFavorite(joke.id!!)

                }

            }
            binding.report.setOnClickListener()
            {
                jokeClickListener.makeDialogReport()
            }


            binding.id.text = "ID:${joke.id}"
            if (joke.type == "single") {
                val joke2: Joke1 = joke as Joke1
                binding.setup.text = joke2.joke
                binding.delivery.text = " "
            } else {

                val joke2: Joke2 = joke as Joke2
                binding.setup.text = joke2.setup
                binding.delivery.text = " "

                binding.setup.setTextColor(ContextCompat.getColor(context,R.color.green))
                binding.setup.setOnTouchListener(){
               _,e-> if(e.action== ACTION_DOWN) {
                   binding.setup.setTextColor(Color.WHITE)
                          binding.delivery.text=joke2.delivery
                          true

                            }else
                                false
                }




            }
        }
    }


                   // binding.setup.setOnHoverListener()




    override fun getItemCount(): Int = dataSource.size
}