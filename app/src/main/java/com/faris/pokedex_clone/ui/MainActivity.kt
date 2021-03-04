package com.faris.pokedex_clone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel?.getPokemon("1")
        viewModel?.pokemonListResponse?.observe(this, Observer {
            tv_result.text = it?.abilities?.get(0)?.ability?.name
        })
    }
}