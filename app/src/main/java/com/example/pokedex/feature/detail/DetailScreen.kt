package com.example.pokedex.feature.detail

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokedex.R
import com.example.pokedex.extensions.findColorFromType
import com.example.pokedex.ui.model.PokemonUIModel
import com.example.pokedex.ui.theme.LightGray

@Composable
internal fun DetailScreen(
    currentPokemon: PokemonUIModel
) {
    val pokemon = remember { currentPokemon }

    val mediaPlayer = remember { MediaPlayer() }

    LaunchedEffect(true) {
        mediaPlayer.setDataSource(pokemon.cries)
        mediaPlayer.prepare()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGray)
        ) {
            AsyncImage(
                model = pokemon.image,
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = "${pokemon.name}'s image",
                modifier = Modifier.align(Alignment.Center)
            )

            Text(
                text = "#" + pokemon.id.toString().padStart(4, '0'),
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

            Icon(
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.BottomStart)
                    .clickable {
                        mediaPlayer.start()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.type))
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            pokemon.types.forEach {
                val color = it.findColorFromType()

                Text(
                    text = it,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .background(
                            color = color,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 2.dp, horizontal = 4.dp)
                )

                Spacer(modifier = Modifier.width(2.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(id = R.string.height, pokemon.height))
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(id = R.string.weight, pokemon.weight))
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(id = R.string.stats))
        Spacer(modifier = Modifier.width(8.dp))

        pokemon.stats.forEach {
            Text(
                text = "${it.first}: ${it.second}"
            )
        }
    }
}
