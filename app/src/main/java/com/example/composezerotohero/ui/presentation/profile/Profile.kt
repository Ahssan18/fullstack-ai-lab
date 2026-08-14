package com.example.composezerotohero.ui.presentation.profile

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composezerotohero.R

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(navController: NavController,userId: String?,name: String?) {
    ProfileView(navController,userId,name)

}

@ExperimentalMaterial3Api
@Composable
fun ProfileView(navController: NavController? = null,userId: String?=null,name: String?=null) {
    Log.e("ProfileScreen","$userId $name")
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text("Profile")
        }, navigationIcon = {
            IconButton(onClick = {
//                navController?.navigate(Screens.Home.route)
                navController?.popBackStack()

            }) {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
            }
        })
    }, content = { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(28.dp))
            Image(painter = painterResource(R.drawable.profile),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.size(120.dp).clip(CircleShape)
                )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Alex",
                fontSize = 32.sp,
                color = Color(0xFF1C1B1F),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(10.dp))

            SkillTag()
            Spacer(modifier = Modifier.height(10.dp))
            ProfileInfoScreen()
        }

    })
}

@Composable
fun ProfileInfoScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            StatCard(
                modifier = Modifier.weight(1f),
                number = "124",
                title = "Commits"
            )

            StatCard(
                modifier = Modifier.weight(1f),
                number = "48",
                title = "Projects"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE8E8E8))
        ) {

            Column {

                InfoRow(
                    icon = Icons.Default.LocationOn,
                    title = "Location",
                    value = "San Francisco, CA"
                )

                HorizontalDivider()

                InfoRow(
                    icon = Icons.Default.DateRange,
                    title = "Joined",
                    value = "October 2022"
                )

                HorizontalDivider()

                InfoRow(
                    icon = Icons.Default.Link,
                    title = "Portfolio",
                    value = "alexj-dev.io"
                )
            }
        }
    }
}

@Composable
fun InfoRow(
    icon: ImageVector,
    title: String,
    value: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF3046C7),
            modifier = Modifier.size(28.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {

            Text(
                text = title,
                color = Color.Gray,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    number: String,
    title: String
) {
    Card(
        modifier = modifier.height(90.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F6FB)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = number,
                color = Color(0xFF3046C7),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = title,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun SkillTag() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF8FE8DE))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Code,
            contentDescription = null,
            tint = Color.DarkGray,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Software Engineer",
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium
        )
    }
}
@Preview
@ExperimentalMaterial3Api
@Composable
fun ProfilePreview() {
    ProfileView()
}