package com.faris.pokedex_clone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.adapter.PokemonListAdapter
import com.faris.pokedex_clone.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel?.getPokemonList(20)
        viewModel?.pokemonListResponse?.observe(this, Observer {
            val adapter = PokemonListAdapter(it.results)
            rv_pokemon.layoutManager = GridLayoutManager(this, 3)
            rv_pokemon.adapter = adapter
        })
    }
}