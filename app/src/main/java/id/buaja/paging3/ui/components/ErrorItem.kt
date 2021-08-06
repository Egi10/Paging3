package id.buaja.paging3.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorItem(
    error: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = error,
            modifier = Modifier
                .weight(1f)
        )
        Button(
            onClick = onClick,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(text = "Retry")
        }
    }
}