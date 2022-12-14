package com.compose.cocktaildakk_compose.ui.mypage.modify

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.compose.cocktaildakk_compose.*
import com.compose.cocktaildakk_compose.R
import com.compose.cocktaildakk_compose.ui.components.ImageWithBackground
import com.compose.cocktaildakk_compose.ui.mypage.MypageViewModel
import com.compose.cocktaildakk_compose.ui.theme.Color_Cyan

@Composable
fun ModifyCocktailWeightScreen(
    navController: NavController = rememberNavController(),
    mypageViewModel: MypageViewModel = hiltViewModel(),
) {
    val levelSliderPosition = remember { mutableStateOf(2f) }
    val baseSliderPosition = remember { mutableStateOf(2f) }
    val keywordsSliderPosition = remember { mutableStateOf(2f) }

    LaunchedEffect(mypageViewModel.userCocktailWeight.value) {
        levelSliderPosition.value = (mypageViewModel.userCocktailWeight.value.level).toFloat()
        baseSliderPosition.value = (mypageViewModel.userCocktailWeight.value.base).toFloat()
        keywordsSliderPosition.value =
            (mypageViewModel.userCocktailWeight.value.keyword).toFloat()
    }

    ImageWithBackground(
        modifier = Modifier
            .fillMaxSize()
            .blur(20.dp),
        backgroundDrawableResId = R.drawable.img_onboard_back,
        contentDescription = "Img Onboard Back",
        alpha = 0.2f,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_close_24),
            contentDescription = "Icon Close",
            tint = Color.White,
            modifier = Modifier
                .padding(30.dp)
                .size(24.dp)
                .clickable {
                    navController.popBackStack()
                },
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Column(
                modifier = Modifier
                    .padding(40.dp, 0.dp),
            ) {
                Text(
                    text = SELECT_WEIGHT_TEXT,
                    fontSize = 36.sp,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = INFO_WEIGHT_TEXT, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column(
                    modifier = Modifier
                        .padding(40.dp, 0.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(text = LEVEL_IMPORTANCE_TEXT, fontSize = 20.sp)
                    Slider(
                        value = levelSliderPosition.value,
                        onValueChange = { levelSliderPosition.value = it },
                        valueRange = 0f..4f,
                        onValueChangeFinished = {
                            // viewModel.updateSelectedSliderValue(sliderPosition)
                        },
                        steps = 3,
                        colors = SliderDefaults.colors(
                            thumbColor = Color_Cyan,
                            activeTrackColor = Color_Cyan,
                        ),
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = when (levelSliderPosition.value.toInt()) {
                            0 -> NO_MATTER
                            1 -> WEIGHT_NOT_IMPORTANT
                            2 -> WEIGHT_NORMAL
                            3 -> WEIGHT_IMPORTANT
                            4 -> WEIGHT_HIGHLY_IMPORTANT
                            else -> WEIGHT_NORMAL
                        },
                        fontSize = 17.sp,
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(40.dp, 0.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(text = BASE_IMPORTANCE_TEXT, fontSize = 20.sp)
                    Slider(
                        value = baseSliderPosition.value,
                        onValueChange = { baseSliderPosition.value = it },
                        valueRange = 0f..4f,
                        onValueChangeFinished = {
                            // viewModel.updateSelectedSliderValue(sliderPosition)
                        },
                        steps = 3,
                        colors = SliderDefaults.colors(
                            thumbColor = Color_Cyan,
                            activeTrackColor = Color_Cyan,
                        ),
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = when (baseSliderPosition.value.toInt()) {
                            0 -> NO_MATTER
                            1 -> WEIGHT_NOT_IMPORTANT
                            2 -> WEIGHT_NORMAL
                            3 -> WEIGHT_IMPORTANT
                            4 -> WEIGHT_HIGHLY_IMPORTANT
                            else -> WEIGHT_NORMAL
                        },
                        fontSize = 17.sp,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(40.dp, 0.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(text = KEYWORD_IMPORTANCE_TEXT, fontSize = 20.sp)
                    Slider(
                        value = keywordsSliderPosition.value,
                        onValueChange = { keywordsSliderPosition.value = it },
                        valueRange = 0f..4f,
                        onValueChangeFinished = {
                            // viewModel.updateSelectedSliderValue(sliderPosition)
                        },
                        steps = 3,
                        colors = SliderDefaults.colors(
                            thumbColor = Color_Cyan,
                            activeTrackColor = Color_Cyan,
                        ),
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = when (keywordsSliderPosition.value.toInt()) {
                            0 -> NO_MATTER
                            1 -> WEIGHT_NOT_IMPORTANT
                            2 -> WEIGHT_NORMAL
                            3 -> WEIGHT_IMPORTANT
                            4 -> WEIGHT_HIGHLY_IMPORTANT
                            else -> WEIGHT_NORMAL
                        },
                        fontSize = 17.sp,
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Surface(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(color = Color.Transparent)
                        .clickable {
                            mypageViewModel.updateWeight(
                                levelWeight = levelSliderPosition.value.toInt(),
                                baseWeight = baseSliderPosition.value.toInt(),
                                keywordWeight = keywordsSliderPosition.value.toInt(),
                            )
                            navController.popBackStack()
                        },
                    color = Color.Transparent,
                ) {
                    Text(
                        text = CONFIRM_TEXT,
                        modifier = Modifier
                            .border(
                                brush = Brush.horizontalGradient(listOf(Color.Green, Color.Blue)),
                                width = 1.dp,
                                shape = CircleShape,
                            )
                            .padding(20.dp, 10.dp),
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}
