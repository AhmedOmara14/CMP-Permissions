package org.omaradev.permissions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect

class MainActivity : ComponentActivity() {
    private lateinit var permissionsController: PermissionsController
    private lateinit var appViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionsController = PermissionsController(applicationContext = applicationContext)
        appViewModel = AppViewModel(permissionsController)

        setContent {
            BindEffect(permissionsController)
            App(appViewModel = appViewModel)
        }
    }

}
