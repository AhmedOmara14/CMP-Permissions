package org.omaradev.permissions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.omaradev.permissions.components.CameraPermissionCard
import org.omaradev.permissions.components.LocationPermissionCard

@Composable
fun App(appViewModel: AppViewModel) {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 24.dp)
        ) {
            CameraPermissionCard(appViewModel)
            LocationPermissionCard(appViewModel)
        }
    }
}