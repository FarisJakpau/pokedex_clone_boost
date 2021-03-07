package com.faris.pokedex_clone.util

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.faris.pokedex_clone.R

/**
 * Created by FarisJakpau on 5/03/2021
 *
 **/

/**
 * getPokemonId return the pokemon id from pokemon link
 * ex: https://pokeapi.co/api/v2/pokemon/3/
 * split '/' from the url
 * will return 3
 * if split.size == 1, will return return index 0
 */
fun String?.getPokemonId(): String {
    val splitText = this?.trim()?.split("/")

    splitText?.let {
        if (it.size > 1)
            return it[it.size - 2]
        return it[0]
    }
    return this.toString()
}

/**
 * get Pokemon image from the different endpoint
 */
fun getImageLink(pokemonId: String): String {
    return Const.BASE_API_IMAGE_URL + pokemonId + ".png"
}

fun ImageView?.setImageUrl(url: String) {
    this?.let {
        Glide.with(it.context)
            .load(url)
            .into(this)
    }
}

fun pokemonTypeTextview(
    activity: Activity,
    pokemonType: String,
    backgroundColor: String
): TextView {
    return TextView(activity).apply {
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(10, 10, 10, 10)
            setPadding(10, 10, 10, 10)
        }
        text = pokemonType
        textSize = 18F
        setTextColor(ContextCompat.getColor(activity, R.color.white))
        setBackgroundColor(Color.parseColor(backgroundColor))
        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
    }
}
