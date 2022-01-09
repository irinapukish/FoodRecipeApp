package com.foodreceipeapp.app.ui.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.foodreceipeapp.app.ui.theme.DelishComposeTheme
import com.foodreceipeapp.app.ui.theme.compositedOnSurface
import com.foodreceipeapp.app.ui.widget.BookMarkButton
import com.foodreceipeapp.app.ui.widget.NetworkImage
import com.foodreceipeapp.model.RecipesItem

@Composable
fun InspirationItem(
    recipe: RecipesItem,
    modifier: Modifier = Modifier,
    onDetails: (Int) -> Unit,
    onBookMark: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .width(250.dp)
            .padding(8.dp)
            .clickable { onDetails(recipe.id ?: 0) }
    ) {
        val (image, time, title, source) = createRefs()
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .constrainAs(image) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    width = Dimension.fillToConstraints
                },
            color = MaterialTheme.colors.background,
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NetworkImage(
                    url = recipe.image ?: "",
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.compositedOnSurface(alpha = 0.2f))
                    )
                }
                BookMarkButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    onBookMark = {
                        onBookMark()
                        recipe.saved = !recipe.saved
                    },
                    selected = recipe.saved
                )
            }
        }
        Text(
            text = "${recipe.readyInMinutes}MIN",
            style = MaterialTheme.typography.subtitle2,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .constrainAs(time) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = image.bottom,
                        bottom = title.top
                    )
                }
        )
        Text(
            text = recipe.title ?: "",
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = time.bottom,
                        bottom = source.top
                    )
                }
        )
        Text(
            text = "by ${recipe.sourceName}",
            style = MaterialTheme.typography.subtitle2,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .constrainAs(source) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = title.bottom,
                        bottom = parent.bottom
                    )
                }
        )
    }
}

@Preview
@Composable
fun PreviewInspirationItem() {
    DelishComposeTheme {
        InspirationItem(RecipesItem(), onDetails = {}) {}
    }
}
