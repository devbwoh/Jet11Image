package kr.ac.kumoh.ce.prof01.jet11image

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.ce.prof01.jet11image.ui.theme.Jet11ImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jet11ImageTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: CardDealerViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF006600))
                .padding(8.dp)
        ) {
            CardImageSection()
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    viewModel.shuffleCards()
                }
            ) {
                Text("Good Luck", fontSize = 30.sp)
            }

        }
    }
}

@Composable
fun ColumnScope.CardImageSection() {
    val viewModel: CardDealerViewModel = viewModel()
    BoxWithConstraints(Modifier.weight(1F)) {
        val offsetX = (maxWidth / viewModel.cards.size)

        viewModel.cards.mapIndexed { index, card ->
            Box(
                Modifier
                    .fillMaxHeight()
                    .offset(x = offsetX * index)
            ) {
                if (LocalConfiguration.current.orientation
                    == Configuration.ORIENTATION_LANDSCAPE
                )
                    CardImage(
                        Modifier.fillMaxHeight(),
                        card
                    )
                else
                    CardImage(
                        Modifier.fillMaxSize(),
                        card
                    )
            }
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun CardImage(
    modifier: Modifier,
    card: Int,
    viewModel: CardDealerViewModel = viewModel()
) {
    val id = LocalContext.current.resources.getIdentifier(
        viewModel.getCardName(card),
        "drawable",
        LocalContext.current.packageName
    )

    Image(
        painter = painterResource(id),
        contentDescription = "카드 이미지",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Jet11ImageTheme {
        MainScreen()
    }
}

@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun MainScreenLandscapePreview() {
    Jet11ImageTheme {
        MainScreen()
    }
}