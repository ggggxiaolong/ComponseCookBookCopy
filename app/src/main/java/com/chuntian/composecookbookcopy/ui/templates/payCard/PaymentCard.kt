package com.chuntian.composecookbookcopy.ui.templates.payCard

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chuntian.theme.R

@Composable
fun PaymentCard(
    name: String,
    number: String,
    expiryDate: String,
    cvc: String,
    focusType: FocusType
) {
    var showBack by remember { mutableStateOf(false) }
    var cardType by remember { mutableStateOf(CardType.None) }
    val length = number.length.coerceAtMost(16)
    val initial = remember { "*****************" }.replaceRange(0..length, number.take(16))
    showBack = focusType == FocusType.Cvc
    cardType = if (number.length >= 8) CardType.Visa else CardType.None
    val animatedColor = animateColorAsState(
        targetValue = if (cardType == CardType.Visa) Color(0xff1c4788) else Color(0xff424242)
    )
    Box(modifier = Modifier.padding(top = 56.dp)) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer(
                    rotationY = animateFloatAsState(targetValue = if (showBack) 180f else 0f).value,
                ),
            shape = RoundedCornerShape(25.dp),
            color = animatedColor.value,
            shadowElevation = 18.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(visible = !showBack) {
                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (symbol, logo, cardName, cardNameLabel, number, expiry, expiryLabel) = createRefs()
                        Image(
                            painter = painterResource(id = com.chuntian.data.R.drawable.card_symbol),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(20.dp)
                                .constrainAs(symbol) {
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                }
                        )
                        AnimatedVisibility(
                            visible = cardType != CardType.None,
                            modifier = Modifier
                                .padding(20.dp)
                                .constrainAs(logo) {
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                }
                        ) {
                            Image(
                                painter = painterResource(id = cardType.image),
                                contentDescription = "logo"
                            )
                        }

                        Text(
                            text = initial.chunked(4).joinToString(" "),
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(spring())
                                .padding(vertical = 16.dp, horizontal = 16.dp)
                                .constrainAs(number) {
                                    linkTo(start = parent.start, end = parent.end)
                                    linkTo(top = parent.top, bottom = parent.bottom)
                                }
                        )
                        Text(
                            text = "CARD HOLDER",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .constrainAs(cardNameLabel) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(cardName.top)
                                }
                        )
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(TweenSpec(300))
                                .padding(start = 16.dp, bottom = 16.dp)
                                .constrainAs(cardName) {
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                        Text(
                            text = "Expiry",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .constrainAs(expiryLabel) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(expiry.top)
                                }
                        )
                        Text(
                            text = expiryDate.take(4).chunked(2).joinToString("/"),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier
                                .animateContentSize(TweenSpec(300))
                                .padding(end = 16.dp, bottom = 16.dp)
                                .constrainAs(expiry) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }

                AnimatedVisibility(visible = showBack) {
                    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                        val (backScanner) = createRefs()
                        Spacer(
                            modifier = Modifier
                                .height(50.dp)
                                .background(Color.Black)
                                .fillMaxWidth()
                                .constrainAs(backScanner) {
                                    linkTo(top = parent.top, bottom = parent.bottom)
                                }
                        )
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = showBack, modifier = Modifier
                .padding(end = 50.dp, bottom = 50.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minWidth = 60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = cvc,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

enum class FocusType {
    Name, Number, Expiry, Cvc
}

enum class CardType(val title: String, @DrawableRes val image: Int) {
    None("", R.drawable.ic_visa_logo),
    Visa("visa", R.drawable.ic_visa_logo),
}

@Preview
@Composable
fun PreviewPaymentCard() {
    PaymentCard(
        name = "Tan Xiao Long",
        number = "*****************",
        expiryDate = "2210",
        cvc = "123",
        focusType = FocusType.Name
    )
}

@Preview
@Composable
fun PreviewPaymentCardBack() {
    PaymentCard(
        name = "Tan Xiao Long",
        number = "*****************",
        expiryDate = "2210",
        cvc = "123",
        focusType = FocusType.Cvc
    )
}