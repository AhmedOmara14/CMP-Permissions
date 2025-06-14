package org.omaradev.permissions.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cmppermissions.composeapp.generated.resources.Res
import cmppermissions.composeapp.generated.resources.camera_permission_status
import cmppermissions.composeapp.generated.resources.ic_camera
import cmppermissions.composeapp.generated.resources.ic_location
import cmppermissions.composeapp.generated.resources.location_permission_status
import org.jetbrains.compose.resources.stringResource
import org.omaradev.permissions.AppViewModel

@Composable
fun LocationPermissionCard(appViewModel: AppViewModel) {
    val locationPermissionState by appViewModel.locationPermissionState.collectAsState()
    PermissionItemCard(
        permissionTitle = stringResource(Res.string.location_permission_status),
        permissionVector = Res.drawable.ic_location,
        cameraPermissionState = locationPermissionState,
        onClick = { appViewModel.requestLocationPermission() }
    )
}