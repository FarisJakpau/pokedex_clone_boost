package com.faris.pokedex_clone.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.adapter.PokemonListAdapter
import com.faris.pokedex_clone.util.Const.Companion.POKEMON_ID
import com.faris.pokedex_clone.util.getPokemonId
import com.faris.pokedex_clone.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel: MainActivityViewModel? = null
    private var isLoading = false
    private var offset = 0
    private val LIMIT_POKEMON = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PokemonListAdapter(ArrayList())
        val layoutManager = GridLayoutManager(this, 3)
        rv_pokemon.layoutManager = layoutManager
        rv_pokemon.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel?.getPokemonList(LIMIT_POKEMON, offset)

        viewModel?.pokemonListResponse?.observe(this, Observer {
            isLoading = true
            adapter.updateList(it.results)
        })

        viewModel?.errorResponse?.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })

        isLoading = true

        adapter.evenHolder.onClick.observe(this, Observer {
            val intent = Intent(this, PokemonActivity::class.java)
            intent.putExtra(POKEMON_ID, it.url.getPokemonId())
            startActivity(intent)
        })

        /**
         * implement infinite scroll for pokemon list
         */
        rv_pokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()

                    if (isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                            isLoading = false
                            offset += LIMIT_POKEMON
                            viewModel?.getPokemonList(LIMIT_POKEMON, offset)
                        }
                    }
                }
            }
        })

        fab_favourite.setOnClickListener {
            startActivity(Intent(this, FavouriteActivity::class.java))
        }
    }
}