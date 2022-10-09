@file:OptIn(ExperimentalPagerApi::class)

package com.compose.cocktaildakk_compose.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.compose.cocktaildakk_compose.R
import com.compose.cocktaildakk_compose.SingletonObject.VISIBLE_SEARCH_STR
import com.compose.cocktaildakk_compose.domain.model.Cocktail
import com.compose.cocktaildakk_compose.ui.theme.Color_Cyan
import com.compose.cocktaildakk_compose.ui.theme.Color_Default_Backgounrd
import com.compose.cocktaildakk_compose.ui.theme.Color_Default_Backgounrd_70
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager


@Composable
fun KeywordRecScreen(
  navController: NavController,
  baseTagRecList: List<Cocktail> = emptyList(),
  randomBaseTag: String,
  keywordTagRecList: List<Cocktail> = emptyList(),
  randomKeywordTag: String,
  randomRecList: List<Cocktail> = emptyList(),
) {
  val scrollState = rememberScrollState()
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(state = scrollState)
  ) {
    Text(
      text = "랜덤 칵테일",
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(20.dp, 20.dp)
    )
    TodayRecTable(navController = navController, randomRecList = randomRecList)
    Text(
      text = "이런 칵테일 어때요?",
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(20.dp, 20.dp)
    )
    KeywordListTable(
      navController = navController,
      cocktailList = baseTagRecList,
      tagName = randomBaseTag
    )
    KeywordListTable(
      navController = navController,
      cocktailList = keywordTagRecList,
      tagName = randomKeywordTag
    )
  }
}


@Composable
fun TodayRecTable(navController: NavController, randomRecList: List<Cocktail>) {
  HorizontalPager(
    count = randomRecList.size,
    modifier = Modifier
      .fillMaxWidth()
      .height(230.dp),
  ) { item ->
    Box {
      Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.img_main_dummy),
        contentDescription = "Today Rec Img",
        contentScale = ContentScale.Crop
      )
      Spacer(
        modifier = Modifier
          .fillMaxSize()
          .background(
            brush = Brush.horizontalGradient(listOf(Color.Transparent, Color_Default_Backgounrd_70))
          ),
      )
      Column(
        modifier = Modifier
          .padding(30.dp)
          .align(Alignment.TopEnd),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.End
      ) {
        Text(text = randomRecList[item].krName, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(text = randomRecList[item].enName, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        randomRecList[item].keyword.split(',').take(4).map {
          Text(text = "#$it", fontSize = 13.sp)
        }
      }
      Surface(
        modifier = Modifier
          .padding(10.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(color = Color(0x30ffffff))
          .align(Alignment.BottomEnd),
        color = Color.Transparent
      ) {
        Text(
          text = "${item + 1} / 5",
          color = Color.White,
          fontSize = 12.sp,
          modifier = Modifier
            .padding(15.dp, 3.dp)
            .background(Color.Transparent)
        )
      }
    }
  }
}

@Composable
fun KeywordListTable(navController: NavController, cocktailList: List<Cocktail>, tagName: String) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 20.dp, end = 20.dp, bottom = 30.dp),
    color = Color.Transparent,
    shape = RoundedCornerShape(15.dp),
    border = BorderStroke(1.dp, Color_Cyan)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 20.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp, 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = "#${tagName} 태그가 들어간 칵테일", fontSize = 16.sp,
        )
        Row(modifier = Modifier.clickable {
          VISIBLE_SEARCH_STR.value = tagName
          navController.navigate("searchresult")
        }) {
          Text(text = "더보기", fontSize = 12.sp)
          Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_right_24),
            contentDescription = "More Info Btn",
            modifier = Modifier.size(12.dp)
          )
        }
      }
      LazyRow(
        modifier = Modifier
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
      ) {
        items(cocktailList) { item ->
          Column(
            modifier = Modifier
              .width(100.dp)
              .clickable {
                navController.navigate("detail/${item.idx}")
              },
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_list_dummy),
              contentDescription = "Img List Dummy",
              modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(100.dp)
                .padding(bottom = 10.dp)
            )
            Text(
              text = item.krName,
              fontSize = 15.sp,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              modifier = Modifier.padding(5.dp, 0.dp)
            )
            Text(
              text = item.enName,
              fontSize = 10.sp,
              color = Color(0xff5E5E5E),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }
        }
      }
      Spacer(modifier = Modifier.width(20.dp))

    }
  }
}