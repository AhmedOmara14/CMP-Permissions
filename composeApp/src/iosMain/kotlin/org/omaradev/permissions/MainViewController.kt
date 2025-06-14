package org.omaradev.permissions

import androidx.compose.ui.window.ComposeUIViewController
import dev.icerock.moko.permissions.ios.PermissionsController

fun MainViewController() = ComposeUIViewController {
    val permissionsController = PermissionsController()
    val appViewModel = AppViewModel(permissionsController)
    App(appViewModel)
}