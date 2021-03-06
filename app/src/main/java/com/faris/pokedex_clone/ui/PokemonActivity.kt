package com.faris.pokedex_clone.ui

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.widget.LinearLayout
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
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.activity_pokemon.tv_name
import kotlinx.android.synthetic.main.item_ability_description.*
import kotlinx.android.synthetic.main.item_ability_description.view.*

/**
 * Created by farisjakpau on 06/03/2021.
 * Pokedex_Clone.
 */

class PokemonActivity : AppCompatActivity() {

    private var viewModel: PokemonActivityViewModel? = null
    private var bottomSheetBehaviour: BottomSheetBehavior<LinearLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val pokemonId = intent.extras?.getString(POKEMON_ID)
        val adapter = AbilitiesListAdapter(ArrayList())
        bottomSheetBehaviour = BottomSheetBehavior.from(bottom_sheet)

        viewModel = ViewModelProviders.of(this).get(PokemonActivityViewModel::class.java)

        pokemonId?.let {
            viewModel?.getPokemon(it)
        }

        viewModel?.pokemonResponse?.observe(this, Observer {
            iv_pokemon.setImageUrl(getImageLink(it.id.toString()))
            tv_name.text = it.species?.name

            val pokemonType = PokemonTypeEnum.values()
                .find { enum -> enum.type == it?.types?.get(0)?.type?.name.toString() }
            ll_root.setBackgroundColor(Color.parseColor(pokemonType?.colorCode))

            adapter.updateData(it.abilities as ArrayList<AbilityResponseModel>)
            rv_abilities.layoutManager = LinearLayoutManager(this)
            rv_abilities.adapter = adapter
        })

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

            /*bottom_sheet.tv_effect.text = it.effect_entries?.get(0)?.effect
            bottom_sheet.tv_depth_effect.text = it.effect_entries?.get(0)?.short_effect*/
        })
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