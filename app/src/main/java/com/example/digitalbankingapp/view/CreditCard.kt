package com.example.digitalbankingapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.digitalbankingapp.R
import com.example.digitalbankingapp.model.CreditCardModel
import com.example.digitalbankingapp.utils.CardNumberSplitter

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
                refExpiration,
                refExpirationDate,
                refHolderName,
                icCardEntity,
            ) = createRefs()

            val cardNumber = CardNumberSplitter(number = model.number)

            val cardPadding = dimensionResource(id = R.dimen.credit_card_start_padding)

            model.logoCardIssuer?.let { issuer ->
                Image(
                    modifier = Modifier
                        .constrainAs(icCardEntity) {
                            top.linkTo(parent.top, margin = cardPadding)
                            start.linkTo(parent.start, margin = cardPadding)
                        }
                        .width(60.dp),
                    painter = painterResource(id = issuer),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }

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
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
        backgroundColor = backgroundColor,
        content = content
    )
}

@Composable
fun CardNumberBlock(cardNumber: CardNumberSplitter, modifier: Modifier) {
    Text(
        modifier = modifier,
        fontWeight = FontWeight.Light,
        fontFamily = FontFamily.Monospace,
        fontSize = 25.sp,
        color = Color.White,
        text = "${cardNumber.first} ${cardNumber.second} ${cardNumber.third} ${cardNumber.fourth}")
}

@Preview(name = "Credit card")
@Composable
fun CreditCardPreview() {
    Column(
        modifier = Modifier.width(500.dp)
    ) {
        CreditCard(
            model = CreditCardModel(
                number = "4234567891234567",
                expiration = "1227",
                holderName = "John Smith",
                cardEntity = "VISA"
            ),
            backgroundColor = Color.Yellow
        )
    }
}