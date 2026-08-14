package com.example.composezerotohero.ui.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composezerotohero.R
import com.example.composezerotohero.ui.Navigation.Screens

@ExperimentalMaterial3Api

@Composable
fun HomeScreen(navController: NavController) {
    HomeScreenContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(navController: NavController?=null) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, null)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Person, null)
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Color(0xFF8B5E4A)
            ) {
                Icon(Icons.Default.Add, null)
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {

            item { HeaderImage() }

            item { Spacer(Modifier.height(24.dp)) }

            item { WelcomeSection() }

            item { Spacer(Modifier.height(24.dp)) }

            item { ProfileButton(navController) }

            item { Spacer(Modifier.height(32.dp)) }

            item { DashboardGrid() }
        }
    }
}

@Composable
fun HeaderImage() {

    Image(
        painter = painterResource(R.drawable.office),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(24.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun WelcomeSection() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Welcome back!",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(6.dp))

        Box(
            Modifier
                .size(6.dp)
                .background(Color.Red, CircleShape)
        )

        Spacer(Modifier.height(10.dp))

        Text(
            "It's good to see you again. Manage your\nprofessional projects and account settings\nfrom your dashboard.",
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

@Composable
fun ProfileButton(navController: NavController?=null) {

    Button(
        onClick = {
            navController?.navigate(Screens.Profile.createRoute("10","Ahssan"))
        },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4A56C8)
        ),
        contentPadding = PaddingValues(
            horizontal = 28.dp,
            vertical = 14.dp
        )
    ) {

        Icon(Icons.Default.Person, null)

        Spacer(Modifier.width(8.dp))

        Text("Go to Profile")
    }
}

@Composable
fun DashboardGrid() {

    Column {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            DashboardCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Inventory,
                title = "Products",
                color = Color(0xFFF2EEF3)
            )

            DashboardCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Default.ShowChart,
                title = "Analytics",
                color = Color(0xFF86F0E2)
            )
        }
    }
}

@Composable
fun DashboardCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    color: Color
) {

    Card(
        modifier = modifier
            .height(140.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF2E43C4)
            )

            Spacer(Modifier.weight(1f))

            Text(
                title,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@ExperimentalMaterial3Api
@Composable
fun HomeScreenPreview(){
    HomeScreenContent()
}