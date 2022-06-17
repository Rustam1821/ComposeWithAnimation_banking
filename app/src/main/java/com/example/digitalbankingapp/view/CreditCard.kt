package com.example.digitalbankingapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.data.creditCardData
import com.example.digitalbankingapp.model.CreditCardModel
import com.example.digitalbankingapp.utils.CardNumberSplitter
import com.example.digitalbankingapp.utils.cardMeasuredHeight
import java.text.DecimalFormat
import kotlin.math.abs

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
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                    },
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
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
                color = Color.Black,
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
            .width(280.dp)
            .padding(
                top = 8.dp,
                end = 8.dp
            )
            .cardMeasuredHeight(),

        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
        ),
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
        letterSpacing = 1.sp,
        color = Color.Black,
        text = "${cardNumber.first}  ${cardNumber.second}  ${cardNumber.third}  ${cardNumber.fourth}"
    )
}


@Composable
fun TwoCards() {
    var upperCardNumber by remember {
        mutableStateOf(0)
    }
    ConstraintLayout(
        Modifier.clickable {
            upperCardNumber = abs(upperCardNumber - 1)
        }.fillMaxWidth()
    ) {
        val (upperCard, lowerCard, bottomBalance) = createRefs()
        var lowerCardNumber = abs(upperCardNumber - 1)
        Box(
            modifier = Modifier
                .constrainAs(lowerCard) {
                    end.linkTo(parent.end)
                }
        ) {
            CreditCard(
                model = creditCardData()[lowerCardNumber],
                backgroundColor = fetchCardColor(lowerCardNumber),
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(upperCard) {
                    start.linkTo(parent.start)
                }
        ) {
            CreditCard(
                model = creditCardData()[upperCardNumber],
                backgroundColor = fetchCardColor(upperCardNumber),
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(bottomBalance) {
                    start.linkTo(upperCard.start)
                    top.linkTo(upperCard.bottom)
                    end.linkTo(lowerCard.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()

        ) {
            val amount = creditCardData()[lowerCardNumber].balance
            Balance(formattedBalance(amount))
        }
    }
}

@Composable
private fun Balance(amount: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),

        shape = RoundedCornerShape(
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
        backgroundColor = MaterialTheme.colors.onBackground,
    ) {
        Column {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                ),
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontSize = 14.sp,
                color = Color.Gray,
                text = "Balance",
            )
            Text(
                modifier = Modifier.padding(
                    top = 2.dp,
                    start = 16.dp,
                ),
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                text = "USD $amount",
                color = MaterialTheme.colors.background,
            )

        }
    }
}

@Preview(showBackground = true, name = "Two Credit card")
@Composable
fun CreditCardPreview() {
    TwoCards()
}

fun formattedBalance(balance: Double): String {
    val dec = DecimalFormat("#,###.00")
    return dec.format(balance)
}

@Composable
fun fetchCardColor(number: Int): Color {
    val id = if (number == 0) R.color.aero_blue else R.color.soap
    return colorResource(id)
}
