package com.example.digitalbankingapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
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
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.data.creditCardData
import com.example.digitalbankingapp.model.CreditCardModel
import com.example.digitalbankingapp.utils.AnimatedLowerCard
import com.example.digitalbankingapp.utils.AnimatedUpperCard
import com.example.digitalbankingapp.utils.CardNumberSplitter
import com.example.digitalbankingapp.utils.DECIMAL_FORMAT_PATTERN
import java.text.DecimalFormat

@Composable
fun CreditCard(
    modifier: Modifier = Modifier,
    model: CreditCardModel,
) {
    CreditCardContainer(
        backgroundColor = colorResource(id = model.backgroundColorId),
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
                    modifier = modifier
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
                modifier = modifier
                    .constrainAs(refCardNumber) {
                        top.linkTo(icCardEntity.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(horizontal = cardPadding),
                cardNumber = cardNumber
            )

            Text(
                modifier = modifier
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
                modifier = modifier
                    .constrainAs(refHolderName) {
                        start.linkTo(parent.start, margin = cardPadding)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                    },
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
                text = model.holderName.uppercase()
            )

            Text(
                modifier = modifier
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
                modifier = modifier
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
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .width(250.dp)
            .height(170.dp)//todo: or 165
            .padding(
                top = 16.dp,
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
fun CardNumberBlock(
    modifier: Modifier = Modifier,
    cardNumber: CardNumberSplitter,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        fontFamily = FontFamily(Font(R.font.plus_jakarta_sans_bold)),
        fontSize = 19.sp,
        color = Color.Black,
        text = "${cardNumber.first}  ${cardNumber.second}  ${cardNumber.third}  ${cardNumber.fourth}"
    )
}

@Composable
fun TwoCards(
    modifier: Modifier = Modifier,
) {
    var isGreenCardAtop by rememberSaveable { mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }
    val greenCreditCard = creditCardData()[0]
    val purpleCreditCard = creditCardData()[1]
    Column(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                isGreenCardAtop = !isGreenCardAtop
            }
            .fillMaxWidth()) {
        Box(
            modifier = modifier.fillMaxWidth(),
        ) {

            // LowerCards
            Box(modifier = modifier.align(Alignment.BottomEnd)) {
                AnimatedLowerCard(isVisible = !isGreenCardAtop)
                {
                    CreditCard(
                        modifier = modifier,
                        model = greenCreditCard,
                    )
                }
                AnimatedLowerCard(isVisible = isGreenCardAtop)
                {
                    CreditCard(
                        modifier = modifier,
                        model = purpleCreditCard,
                    )
                }
            }

            //UpperCards
            Box(modifier = modifier.align(Alignment.BottomStart)) {
                AnimatedUpperCard(isVisible = isGreenCardAtop)
                {
                    CreditCard(
                        modifier = modifier,
                        model = greenCreditCard,
                    )
                }
                AnimatedUpperCard(isVisible = !isGreenCardAtop)
                {
                    CreditCard(
                        modifier = modifier,
                        model = purpleCreditCard,
                    )
                }
            }

        }
        val topCard = if (isGreenCardAtop) {
            greenCreditCard
        } else purpleCreditCard
        Balance(
            modifier = modifier,
            amount = formattedBalance(topCard.balance)
        )

    }
}

@Composable
private fun Balance(
    modifier: Modifier = Modifier,
    amount: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp),

        shape = RoundedCornerShape(
            bottomStart = 10.dp,
            bottomEnd = 10.dp,
        ),
        backgroundColor = MaterialTheme.colors.onBackground,
    ) {
        Column(
            modifier = modifier.padding(start = 16.dp),
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

@Preview(showBackground = true, name = "Two Credit card simpler")
@Composable
fun CreditCardPreview() {
    TwoCards()
}

fun formattedBalance(balance: Double): String {
    val dec = DecimalFormat(DECIMAL_FORMAT_PATTERN)
    return dec.format(balance)
}
