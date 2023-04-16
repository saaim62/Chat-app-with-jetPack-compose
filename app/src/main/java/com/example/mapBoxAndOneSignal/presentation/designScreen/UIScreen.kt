package com.example.mapBoxAndOneSignal.presentation.designScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mapBoxAndOneSignal.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ecommerce App") },
                backgroundColor = Color.White,
                elevation = 4.dp
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "New Arrivals",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                HorizontalRecyclerView(
                    items = getNewArrivals(),
                    isTopItem = true
                )

                Text(
                    text = "Recommended for You",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                HorizontalRecyclerView(
                    items = getRecommendedProducts(),
                    isTopItem = false
                )

                Text(
                    text = "Trending Now",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                HorizontalRecyclerView(
                    items = getTrendingProducts(),
                    isTopItem = false
                )

                Text(
                    text = "Best Sellers",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                HorizontalRecyclerView(
                    items = getBestSellingProducts(),
                    isTopItem = false
                )
            }
        }
    )
}

@Composable
fun HorizontalRecyclerView(
    items: List<Product>,
    isTopItem: Boolean
) {
    val horizontalPadding = if (isTopItem) 0.dp else 8.dp
    val itemWidth = if (isTopItem) 200.dp else 120.dp
    val itemHeight = if (isTopItem) 300.dp else 200.dp

    LazyRow(
        contentPadding = PaddingValues(horizontal = horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(itemHeight)
    ) {
        items(items) { product ->
            ProductCard(
                product = product,
                modifier = Modifier.width(itemWidth)
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
            Text(
                text = product.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// Define the Product data class
data class Product(
    val name: String,
    val price: Double,
    val imageResId: Int
)

// Define some dummy data for each of the four categories
fun getNewArrivals(): List<Product> {
    return listOf(
        Product("Product 1", 19.99, R.drawable.product),
        Product("Product 2", 29.99, R.drawable.product),
        Product("Product 3", 39.99, R.drawable.product),
        Product("Product 4", 49.99, R.drawable.product),
        Product("Product 5", 59.99, R.drawable.product),
    )
}

fun getRecommendedProducts(): List<Product> {
    return listOf(
        Product("Product 6", 69.99, R.drawable.product),
        Product("Product 7", 79.99, R.drawable.product),
        Product("Product 8", 89.99, R.drawable.product),
        Product("Product 9", 99.99, R.drawable.product),
        Product("Product 10", 109.99, R.drawable.product),
    )
}

fun getTrendingProducts(): List<Product> {
    return listOf(
        Product("Product 11", 119.99, R.drawable.product),
        Product("Product 12", 129.99, R.drawable.product),
        Product("Product 13", 139.99, R.drawable.product),
        Product("Product 14", 149.99, R.drawable.product),
        Product("Product 15", 159.99, R.drawable.product),
    )
}

fun getBestSellingProducts(): List<Product> {
    return listOf(
        Product("Product 16", 169.99, R.drawable.product),
        Product("Product 17", 179.99, R.drawable.product),
        Product("Product 18", 189.99, R.drawable.product),
        Product("Product 19", 199.99, R.drawable.product),
        Product("Product 20", 209.99, R.drawable.product),
    )
}
