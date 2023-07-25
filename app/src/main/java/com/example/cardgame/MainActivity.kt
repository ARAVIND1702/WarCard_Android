package com.example.cardgame

import android.app.AlertDialog
import android.graphics.Color.rgb
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.example.cardgame.ui.theme.CardGameTheme
import kotlin.random.Random
class MainActivity : ComponentActivity() {
    private var playerNo by mutableStateOf(0)
    private var playerScore by mutableStateOf(0)
    private var CpuScore by mutableStateOf(0)
    private var cpuNo by mutableStateOf(0)
    private val random = Random.Default
    private var player by mutableStateOf(
        arrayOf(
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6,
            R.drawable.card7,
            R.drawable.card8,
            R.drawable.card9,
            R.drawable.card10,
            R.drawable.card11,
            R.drawable.card12,
            R.drawable.card13,
            R.drawable.card14
        )
    )
    var flag by mutableStateOf(true)
    var showDialog by mutableStateOf(false)
    private val openDialog by mutableStateOf(true)


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContent {
            CardGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {
                    Backgroundimg();
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center // Center the child elements both horizontally and vertically
                    ) {
                        if (playerScore == 10) {
                            AlertDialogScreen()

                        }
                        else if (CpuScore == 10){
                            AlertDialogScreenLoss()
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                            Logo()
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ImageExample()
                                Cpu()
                            }
                            Spacer(modifier = Modifier.height(25.dp))

                            Btn()
                            Spacer(modifier = Modifier.height(25.dp))

                            Row {
                                Column() {
                                    PlayerCard()
                                    Text(
                                        text = playerScore.toString(),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                                        color = Color.White,
                                        fontSize = 28.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(144.dp))
                                Column() {
                                    CpuCard()
                                    Text(
                                        text = CpuScore.toString(),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                                        color = Color(0xFFF4725D),
                                        fontSize = 28.sp
                                    )

                                }

                            }

                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    // @Composable
    //   fun ImageExample() {

//        val imageLoader = ImageLoader.Builder(LocalContext.current)
//            .components {
//                add(SvgDecoder.Factory())
//            }
//            .build()
//
//
//        Image(
//            painter = rememberAsyncImagePainter(player[no], imageLoader),
//            contentDescription = null,
//            modifier = Modifier
//                .width(180.dp)
//                .padding(12.dp)
//
//        )
//        Crossfade(
//            flag,
//            animationSpec = tween(1000)
//        ) { targetState ->
//            Image(
//                painterResource(if (targetState == true) player[no] else player[no]),
//                contentDescription = null,
//            )
//        }
//    }


    @Composable
    fun ImageExample() {
        var targetState by remember { mutableStateOf(false) }
        val targetImage = player[playerNo]

        Crossfade(
            targetState = targetState,
            animationSpec = tween(1000)
        ) { isTargetState ->
            Image(
                painter = painterResource(if (isTargetState) targetImage else targetImage),
                contentDescription = null,
                modifier = Modifier
                    .width(180.dp)
                    .padding(12.dp)
            )
        }

        // Call the Deal function to update the image
        LaunchedEffect(targetImage) {
            targetState = !targetState
        }
    }

    @Composable
    fun Cpu() {
        var targetState by remember { mutableStateOf(false) }
        val targetImage = player[cpuNo]

        Crossfade(
            targetState = targetState,
            animationSpec = tween(1000)
        ) { isTargetState ->
            Image(
                painter = painterResource(if (isTargetState) targetImage else targetImage),
                contentDescription = null,
                modifier = Modifier
                    .width(180.dp)
                    .padding(12.dp)
            )
        }

        // Call the Deal function to update the image
        LaunchedEffect(targetImage) {
            targetState = !targetState
        }
    }

    @Composable
    fun PlayerCard() {
        Text(
            text = "Player",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

    }

    @Composable
    fun CpuCard() {
        Text(
            text = "CPU",
            color = Color(0xFFF4725D),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

    }

//    @Composable
//    fun Btn() {
//        Box(modifier = Modifier.width(90.dp)) {
//            IconButton(
//                onClick = {},
//               content = {
//                    Image(
//                        painter = painterResource(id = R.drawable.btn),
//                        contentDescription = "demo",
//
//                        )
//                })}
//
//    }

    @Composable
    fun Btn() {
        Button(
            onClick = {
                // Show a toast message on button click
                flag = !flag
                Deal()
            },
            content = {
                Image(
                    painter = painterResource(id = R.drawable.btn),
                    contentDescription = null,

                    )
            }, colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Transparent,
                disabledContentColor = Color.Transparent,

                )
        )

    }


    @Composable
    fun Logo() {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()


        Image(
            painter = rememberAsyncImagePainter(R.drawable.logo, imageLoader),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .offset(y = (-39).dp)


        )
    }

    @Composable
    fun Backgroundimg() {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.backgroundplain),
                contentDescription = "demo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

        }
    }


    private @Composable
    fun ImageDialog(imageResId: Int, onClose: () -> Unit) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .offset(x = 75.dp, y = 20.dp)
        )
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CardGameTheme {
            Greeting("Android")
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////

    fun Deal() {

        playerNo = random.nextInt(0, 12)
        cpuNo = random.nextInt(0, 12)
        if (playerNo > cpuNo) {
            playerScore++;
        } else if (playerNo == cpuNo) {
            playerScore--;
            CpuScore--;
        } else {
            CpuScore++;
        }

        showDialog = !showDialog
    }


    @Composable
    fun AlertDialogScreen() {
        var isDialog by remember {
            mutableStateOf(true)
        }
//    Column(modifier = Modifier.fillMaxSize(),
//           horizontalAlignment = Alignment.CenterHorizontally,
//           verticalArrangement = Arrangement.Center
//
//    ){
//        Button(onClick = {
//            isDialog = true
//        }) {
//            Text(text = "Click Here")
//        }
//    }

        if (isDialog) {
            AnimatedVisibility(
                visible = isDialog,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)),
                exit = fadeOut(animationSpec = tween(durationMillis = 5000))
            ) {
                AlertDialog(
                    onDismissRequest = {
                        isDialog = false
                        playerScore = 0
                        CpuScore = 0
                    },
                    title = {
                        Text(
                            "Victory",
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold,
                            fontSize = 34.sp
                        )
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.trophy),
                            contentDescription = null
                        )
                    },
                    text = {
                        Text(
                            "You Won the Game",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 50.dp)
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                isDialog = false
                                playerScore = 0
                                CpuScore = 0
                            }
                        ) {
                            Text("Play Again")
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun AlertDialogScreenLoss(){
        var isDialog by remember {
            mutableStateOf(true)

        }
//    Column(modifier = Modifier.fillMaxSize(),
//           horizontalAlignment = Alignment.CenterHorizontally,
//           verticalArrangement = Arrangement.Center
//
//    ){
//        Button(onClick = {
//            isDialog = true
//        }) {
//            Text(text = "Click Here")
//        }
//    }

        if (isDialog) {
            AnimatedVisibility(
                visible = isDialog,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 2200)
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 5500)
                ) + fadeOut(),
                modifier = Modifier.animateContentSize() // Apply animation to the content size
            ) {
                AlertDialog(
                    onDismissRequest = {
                        isDialog = false
                        playerScore = 0
                        CpuScore = 0
                    },
                    title = {
                        Text("Loss", color = Color(0xFFF4725D), fontWeight = FontWeight.Bold, fontSize = 34.sp)
                    },
                    icon = { Image(painter = painterResource(id = R.drawable.trophybroken), contentDescription = null) },
                    text = {
                        Text(
                            "You Lose the Game",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 50.dp)
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                isDialog = false
                                playerScore = 0
                                CpuScore = 0
                            }
                        ) {
                            Text("Play Again")
                        }
                    }
                )
            }
        }
        }
}

