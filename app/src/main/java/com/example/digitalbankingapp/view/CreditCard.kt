package com.example.digitalbankingapp.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.data.creditCardData
import com.example.digitalbankingapp.model.CreditCardModel
import com.example.digitalbankingapp.utils.CardNumberSplitter
import com.example.digitalbankingapp.utils.cardMeasuredHeight

@Composable
fun CreditCard(
    model: CreditCardModel,
    backgroundColor: Color
) {
    CreditCardContainer(
        backgroundColor = backgroundColor,
    ) {
        ConstraintLayout {
            val (
                refCardNumber,
                refName,
                refExpiration,
                refExpirationDate,
                refHolderName,
                icCardEntity,
            ) = createRefs()

            val cardNumber = CardNumberSplitter(number = model.encryptedCardNumber)

            val cardPadding = dimensionResource(id = R.dimen.credit_card_start_padding)

            model.logoCardIssuer?.let { issuer ->
                Image(
                    modifier = Modifier
                        .constrainAs(icCardEntity) {
                            top.linkTo(parent.top, margin = 8.dp)
                            end.linkTo(parent.end, margin = cardPadding)
                        }
                        .size(50.dp),
                    painter = painterResource(id = issuer),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }

            CardNumberBlock(
                modifier = Modifier
                    .constrainAs(refCardNumber) {
                        top.linkTo(icCardEntity.bottom, margin = 2.dp)
                        start.linkTo(parent.start, margin = cardPadding)
                        end.linkTo(icCardEntity.end)
                    },
                cardNumber = cardNumber
            )

            Text(
                modifier = Modifier
                    .constrainAs(refName) {
                        start.linkTo(parent.start, margin = cardPadding)
                        bottom.linkTo(refHolderName.top)
                    },
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontSize = 12.sp,
                color = Color.Gray,
                text = "Name"
            )



            Text(
                modifier = Modifier
                    .constrainAs(refHolderName) {
                        start.linkTo(parent.start, margin = cardPadding)
                        bottom.linkTo(parent.bottom, margin = 30.dp)
                    },
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface,
                text = model.holderName.uppercase()
            )

            Text(
                modifier = Modifier
                    .constrainAs(refExpiration) {
                        start.linkTo(refExpirationDate.start)
                        bottom.linkTo(refHolderName.top)
                    },
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontSize = 12.sp,
                color = Color.Gray,
                text = "Exp"
            )

            Text(
                modifier = Modifier
                    .constrainAs(refExpirationDate) {
                        bottom.linkTo(refHolderName.bottom)
                        end.linkTo(parent.end, margin = cardPadding)
                    },
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface,
                text = model.formattedExpiration
            )

        }
    }
}

@Composable
private fun CreditCardContainer(
    backgroundColor: Color = Color.Gray,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .padding(5.dp)
            .cardMeasuredHeight(),

        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        backgroundColor = backgroundColor,
    ) {
        content()
    }
}

@Composable
fun CardNumberBlock(cardNumber: CardNumberSplitter, modifier: Modifier) {
    Text(
        modifier = modifier,
        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
        fontSize = 20.sp,
        letterSpacing = 2.sp,
        color = MaterialTheme.colors.onSurface,
        text = "${cardNumber.first}  ${cardNumber.second}  ${cardNumber.third}  ${cardNumber.fourth}"
    )
}


@Composable
fun TwoCards() {
    ConstraintLayout {
        val (upperCard, lowerCard, bottomBalance) = createRefs()
        Box(
            modifier = Modifier.constrainAs(lowerCard) {
                start.linkTo(upperCard.start, margin = 42.dp)
            }
        ) {
            CreditCard(
                model = creditCardData()[1],
                backgroundColor = colorResource(id = R.color.soap),
            )
        }
        Box(
            modifier = Modifier.constrainAs(upperCard) {
                start.linkTo(parent.start)
            }
        ) {
            CreditCard(
                model = creditCardData()[0],
                backgroundColor = colorResource(id = R.color.aero_blue),
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(bottomBalance) {
//                    start.linkTo(upperCard.start)
                    bottom.linkTo(parent.bottom)
                }

        ) {
            RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 30.dp,
                bottomStart = 30.dp

            )
            Canvas(
                modifier = Modifier
            ) {
                val cornerRadius = CornerRadius(10f, 10f)
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(0f, 0f),
                                size = Size(500f, 100f),
                            ),
                            bottomLeft = cornerRadius,
                            bottomRight = cornerRadius,
                        )
                    )
                }
                drawPath(path = path, color = Color.Red)
            }
            Text(
                text = "SomeText here"
            )
        }
    }
}

@Composable
private fun Balance(
    backgroundColor: Color = Color.Black,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .padding(5.dp)
            .height(60.dp),

        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = 30.dp,
            bottomStart = 30.dp
        ),
        backgroundColor = backgroundColor,
    ) {
        content()
    }
}

@Preview(showBackground = true, name = "Two Credit card")
@Composable
fun CreditCardPreview() {
    TwoCards()
}
