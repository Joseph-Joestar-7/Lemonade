package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
               LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp()
{
   var step by remember { mutableStateOf(1) }
   var squeezes by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) {innerPadding->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when(step)
            {
                1->{
                    LemonTextandImage(
                        image = R.drawable.lemon_tree,
                        label = R.string.lemon_select ,
                        description = R.string.lemon_tree_content_description,
                        onImageClick = {
                            step=2
                            squeezes=(2..6).random()
                        })
                }
                2->{
                    LemonTextandImage(
                        image = R.drawable.lemon_squeeze,
                        label = R.string.lemon_squeeze,
                        description = R.string.lemon_content_description,
                        onImageClick = {
                            squeezes--
                            if(squeezes==0) step=3
                        })}
                3->{
                    LemonTextandImage(
                        image = R.drawable.lemon_drink,
                        label = R.string.lemon_drink,
                        description = R.string.lemonade_content_description,
                        onImageClick = { step=4 })
                }
                4->{
                    LemonTextandImage(
                        image = R.drawable.lemon_restart,
                        label = R.string.lemon_empty_glass,
                        description = R.string.empty_glass_content_description,
                        onImageClick = { step=1 })
                }
                }
            }

        }
    }


@Composable
fun LemonTextandImage(
    @DrawableRes image:Int,
    @StringRes label:Int,
    @StringRes description:Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier,
)
{
    Box(modifier=modifier)
    {
         Column(
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center,
             modifier = Modifier.fillMaxSize())
             {
             Button(onClick =onImageClick,
                 shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                 colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer) ) {
                 Image(painter = painterResource(image),
                     contentDescription = stringResource(description),
                     modifier = Modifier
                         .width(dimensionResource(R.dimen.button_image_width))
                         .height(dimensionResource(R.dimen.button_image_height))
                         .padding(dimensionResource(R.dimen.button_interior_padding)))
             }
             Spacer(modifier =Modifier.height(dimensionResource(R.dimen.padding_vertical)))
             Text(text= stringResource(label),
                 style = MaterialTheme.typography.bodyLarge
             )
         }
    }

}


@Preview
@Composable
fun LemonadePreview()
{
    LemonadeTheme(darkTheme = true) {
        LemonadeApp()
    }
}