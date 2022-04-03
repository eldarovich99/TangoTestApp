package com.eldarovich99.tangotestapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eldarovich99.tangotestapp.R
import com.eldarovich99.tangotestapp.ui.model.ProductUiModel
import com.eldarovich99.tangotestapp.ui.ui.theme.Purple200
import com.eldarovich99.tangotestapp.ui.ui.theme.TangoTestAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProductApp(viewModel: ProductViewModel) {
    val state: ProductState by viewModel.state.collectAsState()

    TangoTestAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            when (state) {
                is ProductState.Success -> ProductList(
                    viewModel = viewModel,
                    state = state as ProductState.Success
                )
                is ProductState.Error -> ErrorScreen(
                    viewModel = viewModel,
                    state = state as ProductState.Error
                )
                ProductState.Loading -> LoadingScreen()
            }

        }
    }
}

@Composable
fun ProductList(viewModel: ProductViewModel, state: ProductState.Success) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
        onRefresh = {
            viewModel.loadProducts()
        },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(state.data) {
                ProductRow(product = it)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
        )
    }
}

@Composable
fun ErrorScreen(viewModel: ProductViewModel, state: ProductState.Error) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = stringResource(id = state.textRes), style = MaterialTheme.typography.h1)
            Button(onClick = { viewModel.loadProducts() }) {
                Text(
                    text = stringResource(id = R.string.retry_loading),
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}

@Composable
fun ProductRow(product: ProductUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = Purple200,
                shape = RoundedCornerShape(size = 4.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (product.images.isNotEmpty()) {
            AsyncImage(
                model = product.images[0].image,
                contentDescription = product.productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .width(48.dp)
                    .height(48.dp)
            )
        }
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = product.manufacturer.name, style = MaterialTheme.typography.h1)
            Text(text = product.productName, style = MaterialTheme.typography.body1)
        }
    }
}