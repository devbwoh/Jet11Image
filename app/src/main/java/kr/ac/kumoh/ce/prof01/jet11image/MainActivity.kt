package kr.ac.kumoh.ce.prof01.jet11image

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Column {
            CardImage(card = viewModel.cards[4])
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

@SuppressLint("DiscouragedApi")
@Composable
fun ColumnScope.CardImage(card: Int, viewModel: CardDealerViewModel = viewModel()) {
    val id = LocalContext.current.resources.getIdentifier(
        viewModel.getCardName(card),
        "drawable",
        LocalContext.current.packageName
    )

    Image(
        painter = painterResource(id = id),
        contentDescription = "카드 이미지",
        modifier = Modifier.weight(1F).fillMaxSize()

    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Jet11ImageTheme {
        MainScreen()
    }
}