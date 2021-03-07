package com.faris.pokedex_clone.ui

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.faris.pokedex_clone.R
import com.faris.pokedex_clone.adapter.AbilitiesListAdapter
import com.faris.pokedex_clone.enum.PokemonTypeEnum
import com.faris.pokedex_clone.localDb.model.FavouriteModel
import com.faris.pokedex_clone.network.model.response.AbilityResponseModel
import com.faris.pokedex_clone.util.Const.Companion.POKEMON_ID
import com.faris.pokedex_clone.util.getImageLink
import com.faris.pokedex_clone.util.setImageUrl
import com.faris.pokedex_clone.viewModel.PokemonActivityViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.activity_pokemon.tv_name
import kotlinx.android.synthetic.main.item_ability_description.*
import kotlinx.android.synthetic.main.item_ability_description.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class PokemonActivity : AppCompatActivity() {

    private var viewModel: PokemonActivityViewModel? = null
    private var bottomSheetBehaviour: BottomSheetBehavior<LinearLayout>? = null
    private var pokemonName = ""
    private var pokemonId: String? = null
    private var favouriteList: ArrayList<FavouriteModel>? = ArrayList()
    private var isFavourite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        pokemonId = intent.extras?.getString(POKEMON_ID)
        val adapter = AbilitiesListAdapter(ArrayList())
        bottomSheetBehaviour = BottomSheetBehavior.from(bottom_sheet)

        viewModel = ViewModelProviders.of(this)[PokemonActivityViewModel::class.java]

        pokemonId?.let {
            viewModel?.getPokemon(it)
        }

        viewModel?.pokemonResponse?.observe(this, Observer {
            iv_pokemon.setImageUrl(getImageLink(it.id.toString()))
            tv_name.text = it.species?.name
            pokemonName = it.name.toString()

            val pokemonType = PokemonTypeEnum.values()
                .find { enum -> enum.type == it?.types?.get(0)?.type?.name.toString() }
            ll_root.setBackgroundColor(Color.parseColor(pokemonType?.colorCode))

            adapter.updateData(it.abilities as ArrayList<AbilityResponseModel>)
            rv_abilities.layoutManager = LinearLayoutManager(this)
            rv_abilities.adapter = adapter
        })

        viewModel?.errorResponse?.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        })

        getAllFavouritePokemon()

        adapter.evenHolder.onClick.observe(this, Observer {
            bottomSheetBehaviour?.state = BottomSheetBehavior.STATE_EXPANDED
            viewModel?.getPokemonAbility(it?.ability?.name)
        })

        viewModel?.pokemonAbilityResponse?.observe(this, Observer {
            bottom_sheet.tv_name.text = it.name

            it.flavor_text_entries?.forEach { flavourText ->
                if (flavourText.language?.name == "en") {
                    bottom_sheet.tv_description.text = flavourText.flavor_text
                    return@forEach
                }
            }

            it.effect_entries?.forEach { effectEntries ->
                if (effectEntries.language?.name == "en") {
                    bottom_sheet.tv_effect.text = effectEntries.effect
                    bottom_sheet.tv_depth_effect.text = effectEntries.short_effect
                    return@forEach
                }
            }
        })

        btn_favourite.setOnClickListener {
            if (isFavourite) deleteFavourite(FavouriteModel(pokemonId?.toInt(), pokemonName))
            else insertFavourite(FavouriteModel(pokemonId?.toInt(), pokemonName))
        }

    }

    private fun insertFavourite(favouriteModel: FavouriteModel) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel?.insertFavourite(favouriteModel).also {

            }
        }
    }

    private fun deleteFavourite(favouriteModel: FavouriteModel) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel?.deleteFavourite(favouriteModel).also {
                getAllFavouritePokemon()
            }
        }
    }

    private fun getAllFavouritePokemon() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel?.getFavouritePokemon()?.observe(this@PokemonActivity, Observer { it ->
                favouriteList?.clear()
                favouriteList?.addAll(it)

                isFavourite = favouriteList?.any { it.id == pokemonId?.toInt() } ?: false
                if (isFavourite)
                    btn_favourite.text = getString(R.string.remove_favourite)
                else
                    btn_favourite.text = getString(R.string.add_to_favourite)
            })
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action != null) {
            if (bottomSheetBehaviour?.state == BottomSheetBehavior.STATE_EXPANDED) {
                val outRect = Rect()
                bottom_sheet.getGlobalVisibleRect(outRect)

                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    bottomSheetBehaviour?.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}