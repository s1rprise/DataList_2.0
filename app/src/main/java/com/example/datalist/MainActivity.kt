package com.example.datalist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datalist.data.Product
import com.example.datalist.data.products
import com.example.datalist.ui.theme.DataListTheme
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductItems()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Box(modifier = modifier) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItems() {
    Scaffold(
        topBar = {
            ProductsTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(products) {
                ProductList(
                    product = it,
                    modifier = Modifier
                        .padding(
                            dimensionResource(
                                R.dimen.padding_small
                            )
                        )
                )
            }
        }
    }

}

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    product: Product
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            bottomStart = 35.dp,
            bottomEnd = 10.dp,
            topStart = 10.dp,
            topEnd = 35.dp
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProductImage(productImage = product.image)
                ProductInformation(
                    name = product.name,
                    brand = product.brand,
                    price = product.price
                )
                AppIconButton(
                    expanded = isExpanded,
                    onClick = { isExpanded = !isExpanded },
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                )
            }
            if (isExpanded) {
                ProductDescription(
                    description = product.description,
                    modifier = Modifier.padding(
                        bottom = dimensionResource(R.dimen.padding_medium),
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small)
                    )
                )
            }
        }
    }
}

@Composable
fun ProductImage(
    @DrawableRes productImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small)),
        painter = painterResource(productImage),
        contentDescription = null
    )
}

private fun formatCurrency(number: Float): String {
    val locale = Locale("pt", "BR")
    val format = NumberFormat.getCurrencyInstance(locale)
    return format.format(number)
}

@Composable
fun ProductInformation(
    modifier: Modifier = Modifier,
    @StringRes name: Int,
    @StringRes brand: Int,
    price: Float
) {
    Column(modifier = modifier) {
        Row {
            Text(
                text = stringResource(name),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = " - ")
            Text(
                text = stringResource(brand),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = formatCurrency(price)
        )
    }
}

@Composable
fun AppIconButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null
        )
    }
}

@Composable
fun ProductDescription(
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(description),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DataListPreview() {
    DataListTheme {
        ProductItems()
    }
}