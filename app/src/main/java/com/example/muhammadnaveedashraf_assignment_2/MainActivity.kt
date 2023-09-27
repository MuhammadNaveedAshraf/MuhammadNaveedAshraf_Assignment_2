package com.example.muhammadnaveedashraf_assignment_2

import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.muhammadnaveedashraf_assignment_2.ui.theme.MuhammadNaveedAshraf_Assignment_2Theme
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
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                    topBar = {
                        TopBar(title = stringResource(id = R.string.profile))
                    },
                    containerColor = MaterialTheme.colorScheme.primary

                )
                { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxHeight()
                            .verticalScroll(state),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ProfileCard(
                            drawable = R.drawable.ronny,
                            name = stringResource(id = R.string.mark_worms),
                            location = stringResource(id = R.string.washington),
                        )
                        Stats(following =224, followers = 48600 , like = 3200000)
                        Friends()
                        Photos()
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
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 16.dp,),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
@Composable
fun Friends(
    modifier: Modifier = Modifier) {

    Box(modifier = Modifier
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
        Column(modifier = modifier) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = stringResource(id = R.string.friends),
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold),
                )
                Text(text = stringResource(id = R.string.see_all),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                )
                
            }
            Row(
                modifier = modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                FriendElement(drawable = R.drawable.ronny, text = R.string.ronny)
                FriendElement(drawable = R.drawable.mark, text = R.string.mark)
                FriendElement(drawable = R.drawable.monty, text = R.string.monty)
                FriendElement(drawable = R.drawable.jelly, text = R.string.jelly)
                FriendElement(drawable = R.drawable.mariya, text = R.string.mariya)

            }

        }
    }
}
@Composable
fun Stats(following:Int, followers:Int,like:Int, modifier: Modifier = Modifier){

    Box(modifier = Modifier
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
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formatNumber(following),
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Text( modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.following),
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                )
            }
            Divider(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .height(50.dp)
                    .width(1.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formatNumber(followers),
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Text( modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.followers),
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                )
            }

            Divider(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .height(50.dp)
                    .width(1.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Column(modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formatNumber(like),
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Text( modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.like),
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                )
            }
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
        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(top = 30.dp)
//            .wrapContentHeight()
            .background(color = Color.Transparent),
        contentAlignment = Alignment.TopCenter
    ) {

        Box(modifier = Modifier
//            .graphicsLayer { clip = false }
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
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Text(
                    text = stringResource(id = R.string.mark_worms),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                    )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.height(15.dp)
                    )
                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.washington),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(20.dp)
                        .shadow(
                            20.dp,
                            shape = MaterialTheme.shapes.extraLarge,
                            spotColor = Color.Black
                        ),
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
//               .offset(y = (15).dp)
                .size(88.dp)
                .clip(CircleShape),
        )
    }

}
@Composable
fun Photos(
    modifier: Modifier = Modifier) {

    Box(modifier = Modifier
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
        Column(modifier = modifier) {

                Text(text = stringResource(id = R.string.photos),
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold),
                )
            Row(
                modifier = modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(unbounded = false),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ronny),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(150.dp)
//                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                )
                Image(
                    painter = painterResource(R.drawable.mariya),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

            }

        }
    }
}

@Composable
fun TopBar( title: String, modifier: Modifier = Modifier){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, tint = MaterialTheme.colorScheme.onPrimary,contentDescription = null )
        Text(text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Icon(imageVector = Icons.Default.MoreVert ,tint = MaterialTheme.colorScheme.onPrimary, contentDescription = null )
    }

}

@Preview(showBackground = true,)
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
        Photos()
    }
}

@Preview(showBackground = true,)
@Composable
fun FriendPreview() {
    MuhammadNaveedAshraf_Assignment_2Theme {
        FriendElement(
            text = R.string.mark_worms,
            drawable = R.drawable.ronny,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FriendsPreview() {
    MuhammadNaveedAshraf_Assignment_2Theme {
        Friends()
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