import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import extention.minesweeperColor
import extention.pxToDp
import model.Field

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Workspace(modifier: Modifier) {
    val field = rememberSaveable { Field(20, 12) }
    var spacingPx by remember { mutableStateOf(0) }
    LazyVerticalGrid(
        GridCells.Fixed(field.width),
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(spacingPx.pxToDp()),
        horizontalArrangement = Arrangement.spacedBy(spacingPx.pxToDp()),
        verticalArrangement = Arrangement.spacedBy(spacingPx.pxToDp())
    ) {
        items(field.size) { number ->
            Surface(
                Modifier.aspectRatio(1f).onGloballyPositioned { coordinates ->
                    spacingPx = coordinates.size.height / 20
                }.clip(RoundedCornerShape((spacingPx * 2).pxToDp())).combinedClickable(onClick = {
                    field.onCellClick(number)
                }, onLongClick = {
                    field.onCellLongClick(number)
                }),
                color = animateColorAsState(field.getCellState(number).color).value,
                shape = RoundedCornerShape((spacingPx * 2).pxToDp())
            ) {
                Box() {
                    Text(
                        field.getCellPoints(number),
                        Modifier.align(Alignment.Center),
                        color = field.getCellPoints(number).toIntOrNull()?.minesweeperColor ?: Color.Unspecified,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}

