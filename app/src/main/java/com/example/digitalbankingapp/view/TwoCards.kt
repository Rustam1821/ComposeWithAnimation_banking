package com.example.digitalbankingapp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.digitalbankingapp.utils.DECIMAL_FORMAT_PATTERN
import java.text.DecimalFormat

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
                        .size(40.dp),
                    painter = painterResource(id = issuer),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }

            CardNumberBlock(
                modifier = Modifier
                    .constrainAs(refCardNumber) {
                        top.linkTo(icCardEntity.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(horizontal = cardPadding),
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
                        end.linkTo(refExpirationDate.end)
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
            .width(250.dp)
            .height(170.dp)//todo: or 165
            .padding(
                top = 16.dp,
//                end = 16.dp,
            ),
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
        modifier = modifier.fillMaxWidth(),
        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
        fontSize = 19.sp,
        color = Color.Black,
        text = "${cardNumber.first}  ${cardNumber.second}  ${cardNumber.third}  ${cardNumber.fourth}"
    )
}


@Composable
fun TwoCards() {
    var isGreenCardUpper by rememberSaveable { mutableStateOf(true) }
    ConstraintLayout(
        Modifier
            .clickable {
                isGreenCardUpper = !isGreenCardUpper
            }
            .fillMaxWidth()
    ) {
        val (upperGreenCard, lowerGreenCard, upperPurpleCard, lowerPurpleCard, bottomBalance) = createRefs()
        val delay = 300

        Box(
            modifier = Modifier
                .constrainAs(lowerPurpleCard) {
                    end.linkTo(parent.end)
                }
        ) {
            AnimatedVisibility(
                visible = isGreenCardUpper,
                enter = slideInHorizontally(
                    animationSpec = tween(
                        delayMillis = delay,
                    )
                )  { fullWidth -> -fullWidth },
                exit = slideOutHorizontally { fullWidth -> fullWidth/2 }

            ) {
                CreditCard(
                    model = creditCardData()[1],
                    backgroundColor = fetchCardColor(1),
                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(lowerGreenCard) {
                    end.linkTo(parent.end)
                }
        ) {
            AnimatedVisibility(
                visible = !isGreenCardUpper,
                enter = slideInHorizontally(
                    animationSpec = tween(
                        delayMillis = delay,
                    )
                ) { fullWidth -> -fullWidth },
                exit = slideOutHorizontally { fullWidth -> fullWidth/2 }
            ) {
                CreditCard(
                    model = creditCardData()[0],
                    backgroundColor = fetchCardColor(0),
                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(upperPurpleCard) {
                    start.linkTo(parent.start)
                }
        ) {
            AnimatedVisibility(
                visible = !isGreenCardUpper,
                enter = slideInHorizontally (
                    animationSpec = tween(
                        delayMillis = delay,
                    )
                ) { fullWidth -> fullWidth*2 },
                exit = slideOutHorizontally { fullWidth -> -fullWidth/2 },
            ) {
                CreditCard(
                    model = creditCardData()[1],
                    backgroundColor = fetchCardColor(1),
                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(upperGreenCard) {
                    start.linkTo(parent.start)
                }
        ) {
            AnimatedVisibility(
                visible = isGreenCardUpper,
                enter = slideInHorizontally (
                    animationSpec = tween(
                        delayMillis = delay,
                    )
                ) { fullWidth -> fullWidth*2 },
                exit = slideOutHorizontally { fullWidth -> -fullWidth/2 },
            ) {
                CreditCard(
                    model = creditCardData()[0],
                    backgroundColor = fetchCardColor(0),
                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(bottomBalance) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()

        ) {
            val amount = creditCardData()[1].balance
            Balance(formattedBalance(amount))
        }
    }
}

@Composable
private fun Balance(amount: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp),

        shape = RoundedCornerShape(
            bottomStart = 10.dp,
            bottomEnd = 10.dp,
        ),
        backgroundColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans)),
                fontSize = 10.sp,
                color = Color.Gray,
                text = stringResource(id = R.string.credit_card_balance),
            )
            Text(
                fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
                text = "USD $amount",
                fontSize = 12.sp,
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
    val dec = DecimalFormat(DECIMAL_FORMAT_PATTERN)
    return dec.format(balance)
}

@Composable
fun fetchCardColor(number: Int): Color {
    val id = if (number == 0) R.color.aero_blue else R.color.soap
    return colorResource(id)
}
