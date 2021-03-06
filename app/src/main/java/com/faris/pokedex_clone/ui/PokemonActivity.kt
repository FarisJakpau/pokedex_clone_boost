package com.faris.pokedex_clone.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.adapter.AbilitiesListAdapter
import com.faris.pokedex_clone.enum.PokemonTypeEnum
import com.faris.pokedex_clone.network.model.response.AbilityResponseModel
import com.faris.pokedex_clone.util.Const.Companion.POKEMON_ID
import com.faris.pokedex_clone.util.getImageLink
import com.faris.pokedex_clone.util.setImageUrl
import com.faris.pokedex_clone.viewModel.PokemonActivityViewModel
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.activity_pokemon.iv_pokemon


/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class PokemonActivity : AppCompatActivity() {

    private var viewModel: PokemonActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val pokemonId = intent.extras?.getString(POKEMON_ID)

        viewModel = ViewModelProviders.of(this).get(PokemonActivityViewModel::class.java)

        pokemonId?.let {
            viewModel?.getPokemon(it)
        }

        viewModel?.pokemonResponse?.observe(this, Observer {
            iv_pokemon.setImageUrl(getImageLink(it.id.toString()))
            tv_name.text = it.species?.name

            val pokemonType = PokemonTypeEnum.values().find { enum -> enum.type == it?.types?.get(0)?.type?.name.toString() }
            ll_root.setBackgroundColor(Color.parseColor(pokemonType?.colorCode))

            val adapter = AbilitiesListAdapter(it.abilities as ArrayList<AbilityResponseModel>)
            rv_abilities.layoutManager = LinearLayoutManager(this)
            rv_abilities.adapter = adapter

            adapter.evenHolder.onClick.observe(this, Observer {
                Toast.makeText(this, "${it.ability?.name}", Toast.LENGTH_LONG).show()
            })
        })
    }
}