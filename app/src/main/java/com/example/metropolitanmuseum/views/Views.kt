package com.example.metropolitanmuseum.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.metropolitanmuseum.R
import com.example.metropolitanmuseum.mvvm.ArtObject
import com.example.metropolitanmuseum.mvvm.ArtViewModel
import com.example.metropolitanmuseum.utils.State

@Composable
fun ArtView() {
    val viewModel: ArtViewModel =
        viewModel(factory = ArtViewModel.Factory)
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()
    val artObject = viewModel.artObject

    when (state.value) {
        State.Loading -> {
            ShowProgressBar()
        }

        State.Success -> {
            ShowArtObject(artObject = artObject, context = context)
        }

        is State.Error -> {
            Toast.makeText(
                context,
                (state.value as State.Error).errorMessage.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun ShowProgressBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun ShowArtObject(artObject: ArtObject?, context: Context) {
    val artistDisplayName = artObject?.artistDisplayName
    val artistDisplayBio = artObject?.artistDisplayBio
    val artistInfo: String = if (artistDisplayName == "" && artistDisplayBio == "")
        ""
    else if (artistDisplayBio == "")
        artistDisplayName.toString()
    else
        "${artObject?.artistDisplayName} (${artObject?.artistDisplayBio})"

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (artObject?.primaryImageSmall == "") {
            ShowInfo(
                info = "${artObject.objectBeginDate} - ${artObject.objectEndDate}",
                name = artObject.title
            )
        } else
            ShowPicture(artObject = artObject, context = context)
        ShowInfo(info = artistInfo, name = artObject?.artistRole?.uppercase() ?: "")
        ShowInfo(info = artObject?.department!!, name = "DEPARTMENT")
        ShowInfo(info = artObject.culture, name = "CULTURE")
        ShowInfo(info = artObject.period, name = "PERIOD")
        ShowInfo(info = artObject.medium, name = "MEDIUM")
        ShowInfo(info = artObject.dimensions, name = "DIMENSIONS")
    }
}

@Composable
fun ShowPicture(artObject: ArtObject?, context: Context) {
    Box {
        Image(
            painter = rememberImagePainter(artObject?.primaryImageSmall),
            contentDescription = "Object Image",
            modifier = Modifier
                .height(286.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(start = 15.dp, top = 15.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.back_button),
                contentDescription = null,
                modifier = Modifier.clickable {
                    Toast.makeText(
                        context,
                        "Назад",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(end = 15.dp, top = 15.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.increase_button),
                contentDescription = null,
                modifier = Modifier.clickable {
                    Toast.makeText(
                        context,
                        "Увеличить",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(start = 15.dp, bottom = 10.dp)
        ) {
            Column {
                Text(
                    text = artObject?.title?.uppercase().toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${artObject?.objectBeginDate} - ${artObject?.objectEndDate}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun ShowInfo(info: String, name: String) {
    if (info != "") {
        Text(
            text = name,
            modifier = Modifier.padding(start = 15.dp, top = 20.dp, bottom = 3.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = info,
            modifier = Modifier
                .padding(start = 15.dp)
                .fillMaxWidth(),
            fontSize = 14.sp,
        )
    }
}