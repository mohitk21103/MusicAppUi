package mk.project.musicappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mk.project.musicappui.MainViewModel
import mk.project.musicappui.Screen
import mk.project.musicappui.screenInBottom
import mk.project.musicappui.screenInDrawer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(){

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    // Allow us to navigate on which view we are
    val controller : NavController =  rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val dialogOpen = remember{
        mutableStateOf(false)
    }

    //View Model Related Stuff
    val viewModel: MainViewModel = viewModel()
    val currentScreen = remember{
        viewModel.currentscreen.value
    }

    val title = remember{
        //  change that to currentScreen.title
        mutableStateOf(currentScreen.title)
    }

    val modalSheetState = androidx.compose.material.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded}
    )

    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp



    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home){
            BottomNavigation(modifier = Modifier.wrapContentSize()) {
                screenInBottom.forEach{
                    item ->
                    val isSelected = currentRoute == item.bRoute
                    val tint = if(isSelected) Color.White else Color.Black
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = { controller.navigate(item.bRoute)
                                  title.value = item.btitle},
                        icon = {
                            Icon(tint= tint,
                                painter = painterResource(id = item.icon),
                                contentDescription = item.btitle
                            )
                        },
                        label = { Text(text = item.btitle, color = tint)},
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }


    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = { MoreBottomSheet(modifier=modifier) },
    ) {
        Scaffold(
            bottomBar = bottomBar,

            topBar = {
                TopAppBar(title = {
                    Text(text = title.value)
                },
                    actions = {
                              IconButton(onClick = {
                                  scope.launch {
                                      if(modalSheetState.isVisible)
                                          modalSheetState.hide()
                                      else{
                                          modalSheetState.show()
                                      }
                                  }
                              }, ) {
                                  Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more options icon also called as three dots icon")
                              }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            // Open Drawer
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }

                        }) {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")

                        }
                    }
                )
            },
            scaffoldState= scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screenInDrawer){
                            item->
                        DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if(item.dRoute == "add_account"){
                                //open dialog
                                dialogOpen.value = true

                            }else{
                                controller.navigate(item.dRoute)
                                title.value = item.dtitle
                            }
                        }

                    }
                }

            }

        ) {
            navigation(navController = controller, viewModel = viewModel, pd = it)

            AccountDialog(dialogOpen = dialogOpen)
        }

    }



}

@Composable
fun MoreBottomSheet(modifier: Modifier){
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                androidx.compose.material.MaterialTheme.colors.primarySurface
            )
    ){
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Setting Icon", modifier = Modifier.padding(end=8.dp))
                Text(text = "Settings", fontSize = 20.sp, color = Color.White)
            }

            Row(modifier.padding(16.dp)
                ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Share Icon", modifier = Modifier.padding(end=8.dp))
                Text(text = "Share", fontSize = 20.sp, color = Color.White)

                }

            Row(modifier.padding(16.dp)
                ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "about Icon", modifier = Modifier.padding(end=8.dp))
                Text(text = "About", fontSize = 20.sp, color = Color.White)
                }


        }
    }
}



@Composable
fun DrawerItem(
    selected : Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked : () -> Unit
){
    val background  = if(selected) Color.Gray else Color.White
    Row (
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 24.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }
    ){
       Icon(
           painter = painterResource(id = item.icon),
           contentDescription =item.dtitle,
           Modifier.padding(end = 8.dp, top = 4.dp)
       )
        Text(
            text = item.dtitle,
            style = MaterialTheme.typography.headlineSmall
        )


    }

}


@Composable
fun navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues){
    
    NavHost(navController = navController as NavHostController,
        startDestination =Screen.DrawerScreen.Account.route, modifier = Modifier.padding(pd)){

        composable(Screen.BottomScreen.Home.bRoute){
            // Place the home screen here.
            Home()
        }

        composable(Screen.BottomScreen.Library.bRoute){
            // place the library screen
            Library()
        }
        composable(Screen.BottomScreen.Browse.bRoute){
            //place the brows screen here3
            Browse()
        }

        composable(Screen.DrawerScreen.Account.route){
            AccountScreen()
        }

        composable(Screen.DrawerScreen.Subscription.route){
            subscriptionScreen()
        }
    }
}