package com.example.artspace

import android.graphics.fonts.FontStyle.FONT_WEIGHT_BOLD
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.webkit.WebSettings.TextSize
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.layout.getDefaultLazyLayoutKey
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getNamedString
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artspace.data.DataSource
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      ArtSpaceTheme {
        NavHost(
          navController = navController, startDestination = Screen.Home.route + "/{id}"
        ) {
          composable(
            Screen.Home.route + "/{id}", arguments = listOf(navArgument("id") {
              type = NavType.IntType
              defaultValue = 0
            })
          ) {
            HomePage(navController)
          }
          composable(
            Screen.Artist.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
          ) {
            ArtistPage(navController)
          }
        }
      }
    }
  }
}

@Composable
fun ArtistPage(navController: NavController) {
  val id = navController.currentBackStackEntry?.arguments?.getInt("id") ?: 0
  val art = DataSource.arts[id]
  val artist = stringResource(id = art.artistId)
  val artistInfo = stringResource(id = art.artistInfoId)
  val title = stringResource(id = art.titleId)
  val year = stringResource(id = art.yearId)
  val description = stringResource(id = art.descriptionId)
  val artistBio = stringResource(id = art.artistBioId)
  val artistImage = painterResource(id = art.artistImageId)

  // ARTIST PAGE section A
  // TODO: 1. Artist Profile including image, name, and info (birthplace, and years alive)
  Column (
    modifier = Modifier
      .verticalScroll(rememberScrollState())
      .safeDrawingPadding(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Row {
      Column (
      ){
        Text(
          text = stringResource(id =art.titleId),
          fontSize = 25.sp,
          fontWeight = FontWeight(FONT_WEIGHT_BOLD),
          lineHeight = 40.sp,
          textAlign = TextAlign.Center)
        Text(
          text = "$artistInfo $year",
          fontSize = 18.sp,
          lineHeight = 40.sp,
          textAlign = TextAlign.Center)
        }
      Image(
        painter = artistImage,
        contentDescription = artistInfo,
        modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp))
      }
    // ARTIST PAGE section B
    // TODO: 2  Artist bio
    Text(text = artistBio)
    Button(onClick = {
      navController.navigate(Screen.Home.route + "/$id")
    }) {
      Text(text = stringResource(id = R.string.back))
    }

    }


    // <--- Safely REMOVE the following code and ADD your code here --->
    //  Text(text = "(D) Display Artist Profile and Bio here as per the design")

    // DO NOT MODIFY THE FOLLOWING CODE
    // You can use the following code to navigate to the previous screen:
    // ARTIST PAGE section C
    // TODO: 3 place the code below in the proper Row or Column layout


  }

@Composable
fun ArtWall(
  artistId: Int,
  artImageId: Int,
  artDescriptionId: Int,
  navController: NavController,
) {

  // HOME PAGE section A

  // TODO: 4. Add image of artwork

  // TODO: 5. Add a click listener to navigate to the artist page

  val currentArtWork = painterResource(artImageId)
//  val currDesc = getString(artDescriptionId)
  Box(modifier = Modifier.clickable {
    navController.navigate(Screen.Artist.route + "/$artistId")

  }) {
    Image(painter = currentArtWork,
          contentDescription = null ,
          modifier = Modifier
            .padding(15.dp)
            .align(alignment = Alignment.Center))

  }

}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ArtDescriptor(artTitleId: Int, artistId: Int, artYearId: Int) {

  // HOME PAGE section B

  // TODO: 6. Add title of artwork

  // TODO: 7. Add artist name and year of artwork

  // <--- Safely REMOVE the following code and ADD your code here --->

  val artTitle = stringResource(id = artTitleId)
  val artistName = stringResource(id = artistId)
  val artYear = stringResource(id = artYearId)

  Box (modifier = Modifier
    .fillMaxWidth()
    .padding(15.dp)){
    Column (
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth()
    ){
      Text(
        text = artTitle,
        fontSize = 25.sp,
        fontWeight = FontWeight(FONT_WEIGHT_BOLD),
        lineHeight = 40.sp,
        textAlign = TextAlign.Center)
      Text(
        text = "$artistName $artYear",
        fontSize = 18.sp,
        lineHeight = 40.sp,
        textAlign = TextAlign.Center)
      }
    }

  }

@Composable
fun DisplayController(current: Int, move: (Int) -> Unit) {

  // HOME PAGE section C

  // TODO: 9. Add a button to navigate to the previous artwork

  // TODO: 10. Add a button to navigate to the next artwork

  // NOTE: 
  // The buttons should be disabled if there is no previous or next artwork to navigate to
  // You can use the following code to disable the button:
  //  enabled = current != 0 // for the previous button

  // You can use the following code to navigate to the previous or next artwork:
  // move(current - 1) // for the previous button
  // move(current + 1) // for the next button

  // <--- Safely REMOVE the following code and ADD your code here --->
  Box(modifier = Modifier
    .fillMaxWidth()
    .padding(25.dp)) {
    Row(
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth()
    ){
      Button(onClick = { /*TODO*/ }, modifier = Modifier
        .height(50.dp)
        .width(150.dp))
      {
        Text(text = "Previous")
      }
      Button(onClick = { /*TODO*/ }, modifier = Modifier
        .padding(start = 20.dp)
        .height(50.dp)
        .width(150.dp))
      {
        Text(text = "Next")
      }
    }
  }
}

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
  var current by remember {
    mutableIntStateOf(
      navController.currentBackStackEntry?.arguments?.getInt(
        "id"
      ) ?: 0
    )
  }
  val art = DataSource.arts[current]

  Scaffold(topBar = {
    CenterAlignedTopAppBar(
      title = { Text(text = stringResource(id = R.string.app_name)) },
      colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
      )
    )
  }) { innerPadding ->
    /**
     *The children without weight (a) are measured first. After that, the remaining space in the column
     * is spread among the children with weights (b), proportional to their weight. If you have 2
     * children with weight 1f, each will take half the remaining space.
     */
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(innerPadding)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f) // children with weight (b)
      ) {

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_extra_large)))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
          ArtWall(current, art.artworkImageId, art.descriptionId, navController)
        }
      }
      // (a) children without weight
      ArtDescriptor(art.titleId, art.artistId, art.yearId)
      DisplayController(current) {
        current = if (it !in 0..<DataSource.arts.size) 0 else it
      }
    }
  }
}

@Preview(
  showBackground = true,
  showSystemUi = true)
@Composable
fun ArtSpaceAppPreview() {
  ArtSpaceTheme {
//    HomePage(rememberNavController())
    ArtistPage(rememberNavController())
  }
}
