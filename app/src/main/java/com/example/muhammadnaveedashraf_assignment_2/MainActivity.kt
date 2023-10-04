package com.example.muhammadnaveedashraf_assignment_2

import android.content.Context
import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import androidx.constraintlayout.compose.Dimension
import com.example.muhammadnaveedashraf_assignment_2.ui.theme.MuhammadNaveedAshraf_Assignment_2Theme
import com.example.muhammadnaveedashraf_assignment_2.utils.randomSampleImageUrl
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuhammadNaveedAshraf_Assignment_2Theme {
                val state = rememberScrollState()
                Scaffold(
                    Modifier.background(MaterialTheme.colorScheme.onTertiary),
                    topBar = {
                        TopBar(title = stringResource(id = R.string.profile))
                    },
                    containerColor = MaterialTheme.colorScheme.onTertiary
                )
                { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .background(Color.Transparent)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.rectangle_26),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    ConstraintLayout(
                        modifier = Modifier
                            .verticalScroll(state)
                    ) {
                        val (background,profile,stats,friends,photos) = createRefs()
                        ProfileCard(
                            drawable = R.drawable.ronny,
                            name = stringResource(id = R.string.mark_worms),
                            location = stringResource(id = R.string.washington),
                            modifier = Modifier
                                .constrainAs(profile){
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .padding(top = 100.dp)
                        )
                        Stats(
                            following =224,
                            followers = 48600 ,
                            like = 3200000,
                            modifier = Modifier
                                .constrainAs(stats){
                                    top.linkTo(profile.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .padding(top = 10.dp)
                        )
                        Friends(
                            friendList,
                            modifier = Modifier
                                .constrainAs(friends){
                                    top.linkTo(stats.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .padding(top = 10.dp)
                        )
                        Photos(
                            randomSizedPhotos,
                            modifier = Modifier
                                .constrainAs(photos){
                                    top.linkTo(friends.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .padding(top = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuhammadNaveedAshraf_Assignment_2Theme {
        Greeting("Android")
    }
}

@Composable
fun FriendElement(
    image: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
        )
        Text(
            text = text,
            modifier = Modifier
                .paddingFromBaseline(top = 16.dp,)
                .width(54.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}
@Composable
fun Friends(friends: List<Friend>,
            modifier: Modifier = Modifier) {

    Box(modifier = modifier
        .padding(10.dp)
        .shadow(
            4.dp,
            shape = RoundedCornerShape(6.dp),
            ambientColor = Color.White,
            spotColor = Color.Black
        )
        .background(Color.White)
        .fillMaxWidth()
        .padding(vertical = 20.dp)

    ){
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (txtTitle,txtSeeAll,friendList) = createRefs()
            Text(text = stringResource(id = R.string.friends),
                style = MaterialTheme.typography.titleMedium.copy
                    (
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .constrainAs(txtTitle) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.titleMedium.copy(color = Color.LightGray),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .constrainAs(txtSeeAll) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            )
            FriendList(
                friends = friends,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .constrainAs(friendList) {
                        top.linkTo(txtTitle.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
        }
    }
}

@Composable
fun FriendList(friends:List<Friend>, modifier: Modifier = Modifier){
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)

    ){
        itemsIndexed(friends){index, friend ->
            FriendElement(
                image = friend.image,
                text =friend.name
            )
        }
    }
}
@Composable
fun Stats(following:Int, followers:Int,like:Int, modifier: Modifier = Modifier){

    Box(modifier = modifier
        .padding(10.dp)
        .shadow(
            4.dp,
            shape = RoundedCornerShape(6.dp),
            ambientColor = Color.White
        )
        .background(Color.White)
        .fillMaxWidth()
        .padding(20.dp)

    ){
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (followingValue, txtFollowing,divider1,
                followerValue, txtFollower,divider2,
                likeValue, txtLike) = createRefs()
            Text(
                text = formatNumber(following),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .constrainAs(followingValue){
                        start.linkTo(txtFollowing.start)
                        top.linkTo(parent.top)
                        end.linkTo(txtFollowing.end)
                        width = Dimension.fillToConstraints
                },
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.following),
                style = MaterialTheme.typography.titleMedium.copy(color = Color.LightGray),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .constrainAs(txtFollowing) {
                        start.linkTo(parent.start)
                        top.linkTo(followingValue.bottom)
                        end.linkTo(divider1.start)
                    }
            )
            Divider(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
                    .constrainAs(divider1) {
                        start.linkTo(txtFollowing.end)
                        top.linkTo(followingValue.top)
                        bottom.linkTo(txtFollowing.bottom)
                        end.linkTo(txtFollower.start)
                    },
            )
            Text(
                text = formatNumber(followers),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.constrainAs(followerValue){
                    start.linkTo(txtFollower.start)
                    top.linkTo(followingValue.top)
                    end.linkTo(txtFollower.end)
                    width = Dimension.fillToConstraints
                },
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.followers),
                style = MaterialTheme.typography.titleMedium.copy(color = Color.LightGray),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .constrainAs(txtFollower) {
                        start.linkTo(divider1.end)
                        top.linkTo(followerValue.bottom)
                        end.linkTo(divider2.start)
                    }
            )
            Divider(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
                    .constrainAs(divider2) {
                        start.linkTo(txtFollower.end)
                        top.linkTo(followerValue.top)
                        bottom.linkTo(txtFollower.bottom)
                        end.linkTo(txtLike.start)
                    },
            )
            Text(
                text = formatNumber(like),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.constrainAs(likeValue){
                    start.linkTo(divider2.end)
                    top.linkTo(followerValue.top)
                    end.linkTo(parent.end)
                }
            )
            Text(
                text = stringResource(id = R.string.like),
                style = MaterialTheme.typography.titleMedium.copy(color = Color.LightGray),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .constrainAs(txtLike) {
                        start.linkTo(likeValue.start)
                        top.linkTo(likeValue.bottom)
                        end.linkTo(likeValue.end)
                        width = Dimension.fillToConstraints
                    },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProfileCard(
    @DrawableRes drawable: Int,
    name: String,
    location:String,
    modifier: Modifier = Modifier){
    Box(
        modifier = modifier.background(color = Color.Transparent),
        contentAlignment = Alignment.TopCenter
    ) {


        Box(modifier = Modifier
            .padding(top = 30.dp)
            .padding(10.dp)
            .shadow(
                4.dp,
                shape = RoundedCornerShape(6.dp),
                ambientColor = Color.White,
                spotColor = Color.Black
            )
            .background(Color.White)
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
            ){

            ConstraintLayout() {
                val (txtName,icon,txtLocation,btnFollow) = createRefs()
                Text(
                    text = stringResource(id = R.string.mark_worms),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .constrainAs(txtName) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .height(15.dp)
                        .constrainAs(icon) {
                            start.linkTo(txtName.start)
                            top.linkTo(txtName.bottom)
                            end.linkTo(txtLocation.start)
                        }
                )
                Text(
                    text = stringResource(id = R.string.washington),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier.constrainAs(txtLocation){
                        top.linkTo(txtName.bottom)
                        linkTo(start = icon.end, end = txtName.end, bias = 0f)
                    }
                )
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(20.dp)
                        .shadow(
                            20.dp,
                            shape = MaterialTheme.shapes.extraLarge,
                            spotColor = Color.Black
                        )
                        .constrainAs(btnFollow) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(icon.bottom)
                        },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = stringResource(id = R.string.follow).uppercase(),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }

            }
        }

        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
        )
    }

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Photos(photos:List<String>,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(10.dp)
        .shadow(
            4.dp,
            shape = RoundedCornerShape(6.dp),
            ambientColor = Color.White,
            spotColor = Color.Black
        )
        .background(Color.White)
        .fillMaxWidth()
        .padding(20.dp)


    ){
        ConstraintLayout() {
            val (txtTitle, imageGrid) = createRefs()
            Text(text = stringResource(id = R.string.photos),
                style = MaterialTheme.typography.titleMedium
                    .copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                modifier = Modifier.constrainAs(txtTitle){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content =
                {
                    items(photos.size) { index ->
                        AsyncImage(
                            photos[index],
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.ic_launcher_foreground)

                        )
                    }
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(400.dp)
                    .constrainAs(imageGrid) {
                        top.linkTo(txtTitle.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}

@Composable
fun TopBar( title: String, modifier: Modifier = Modifier){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 20.dp)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFF00B524)
@Composable
fun TopBarPreview(){
    MuhammadNaveedAshraf_Assignment_2Theme(){
        TopBar(title = stringResource(id = R.string.profile))
    }
}

@Preview(showBackground = true,)
@Composable
fun PhotosPreview(){
    MuhammadNaveedAshraf_Assignment_2Theme {
        Photos(randomSizedPhotos)
    }
}

@Preview(showBackground = true,)
@Composable
fun FriendListPreview() {
    MuhammadNaveedAshraf_Assignment_2Theme {
        FriendList(friendList)
    }
}
@Preview(showBackground = true)
@Composable
fun FriendsPreview() {
    MuhammadNaveedAshraf_Assignment_2Theme {
        Friends(friendList)
    }
}

@Preview(showBackground = true)
@Composable
fun StatsPreview(){
    MuhammadNaveedAshraf_Assignment_2Theme {
        Stats(224, 48600, 3200000)
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileCardPreview(){
    MuhammadNaveedAshraf_Assignment_2Theme {
        ProfileCard(
            R.drawable.ronny,
            stringResource(id = R.string.mark_worms),
            stringResource(id = R.string.washington),
        )

    }

}

fun formatNumber(number: Int): String {
    val format = NumberFormat.getNumberInstance(Locale.US)
    return when {
        number >= 1_000_000_000 -> format.format(number.toDouble() / 1_000_000_000) + " B"
        number >= 1_000_000 -> format.format(number.toDouble() / 1_000_000) + " M"
        number >= 1_000 -> format.format(number.toDouble() / 1_000) + " K"
        else -> format.format(number)
    }
}
private val randomSizedPhotos = listOf(
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),)
private val names = listOf(
    "Ali",
    "Mark Wood",
    "Mark Worms",
    "Ronny",
    "Monty",
    "Jelly",
    "Mariya",
    "Bolt",
    "Faisal"
)
private val friendList = listOf(
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Ali"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Mark Wood"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Mark Worms"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Ronny"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Monty"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Jelly"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Mariya"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Bolt"),
    Friend(randomSampleImageUrl(width = 1600, height = 900),"Faisal")
)
