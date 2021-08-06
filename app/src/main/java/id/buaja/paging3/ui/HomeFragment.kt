package id.buaja.paging3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import id.buaja.paging3.domain.model.ResultsItem
import id.buaja.paging3.ui.components.ErrorItem
import id.buaja.paging3.ui.components.LoadingItem
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    CharacterList(resultItem = viewModel.getCharacter()) {
                        Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterList(resultItem: Flow<PagingData<ResultsItem>>, onClick: (ResultsItem) -> Unit) {
    val lazyCharacterItem: LazyPagingItems<ResultsItem> = resultItem.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(
            top = 10.dp,
            bottom = 15.dp
        )
    ) {
        items(lazyCharacterItem) { resultItem ->
            CharacterItem(resultsItem = resultItem) {
                onClick.invoke(it)
            }
        }

        lazyCharacterItem.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }

                loadState.refresh is LoadState.Error  -> {
                    val error = lazyCharacterItem.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(error.error.message.toString()) {
                            retry()
                        }
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = lazyCharacterItem.loadState.append as LoadState.Error
                    item {
                        ErrorItem(error.error.message.toString()) {
                            retry()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(resultsItem: ResultsItem?, onClick: (ResultsItem) -> Unit) {
    resultsItem?.let {
        Row(
            modifier = Modifier
                .clickable {
                    onClick.invoke(it)
                }
                .fillMaxSize()
                .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = it.image,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = it.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = it.status,
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}