package com.eldarovich99.tangotestapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eldarovich99.tangotestapp.ui.model.ProductUiModel
import com.eldarovich99.tangotestapp.ui.ui.theme.Purple200
import com.eldarovich99.tangotestapp.ui.ui.theme.TangoTestAppTheme

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
                is ProductState.Success -> ProductList((state as ProductState.Success).data)
                ProductState.Loading -> LoadingScreen()
            }
        }
    }
}

@Composable
fun ProductList(products: List<ProductUiModel>) {
    LazyColumn {
        items(products) {
            ProductRow(product = it)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .width(24.dp)
            .height(24.dp), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ProductRow(product: ProductUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(color = Purple200,
                shape = RoundedCornerShape(size = 4.dp)),
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