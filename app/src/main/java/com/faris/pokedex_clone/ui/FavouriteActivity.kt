package com.faris.pokedex_clone.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.adapter.PokemonListAdapter
import com.faris.pokedex_clone.network.model.response.BaseResponseModel
import com.faris.pokedex_clone.util.Const
import com.faris.pokedex_clone.util.getPokemonId
import com.faris.pokedex_clone.viewModel.FavouriteActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by farisjakpau on 07/03/2021.
 * Pokedex_Clone.
 */

class FavouriteActivity : AppCompatActivity() {

    private var viewModel: FavouriteActivityViewModel? = null
    private val pokemonList: ArrayList<BaseResponseModel> = ArrayList()
    private var adapter: PokemonListAdapter? = PokemonListAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_favourite.visibility = View.GONE

        viewModel = ViewModelProviders.of(this).get(FavouriteActivityViewModel::class.java)
        rv_pokemon.layoutManager = GridLayoutManager(this, 3)
        rv_pokemon.adapter = adapter

        adapter?.evenHolder?.onClick?.observe(this, Observer {
            val intent = Intent(this, PokemonActivity::class.java)
            intent.putExtra(Const.POKEMON_ID, it.url.getPokemonId())
            startActivity(intent)
        })

        getFavouritePokemon()

    }

    private fun getFavouritePokemon() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel?.getFavouritePokemon()?.observe(this@FavouriteActivity, Observer {
                pokemonList.clear()
                it.forEach { favouriteModel ->
                    val tempModel =
                        BaseResponseModel(favouriteModel.pokemon_id, favouriteModel.id.toString())
                    pokemonList.add(tempModel)
                }
                adapter?.resetAndUpdateList(pokemonList)

                /**
                 * if no favourite pokemon added, will return to previous page
                 */
                if (pokemonList.size == 0) {
                    Toast.makeText(
                        this@FavouriteActivity,
                        getString(R.string.no_favourite),
                        Toast.LENGTH_LONG
                    ).show()
                    onBackPressed()
                }
            })
        }
    }

    /**
     * to refresh favourite data after back to this page
     */
    override fun onRestart() {
        super.onRestart()
        getFavouritePokemon()
    }
}