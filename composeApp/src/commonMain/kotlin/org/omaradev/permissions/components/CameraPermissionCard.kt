package org.omaradev.permissions.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cmppermissions.composeapp.generated.resources.Res
import cmppermissions.composeapp.generated.resources.camera_permission_status
import cmppermissions.composeapp.generated.resources.ic_camera
import org.jetbrains.compose.resources.stringResource
import org.omaradev.permissions.AppViewModel

@Composable
fun CameraPermissionCard(appViewModel: AppViewModel) {
    val cameraPermissionState by appViewModel.cameraPermissionState.collectAsState()
    PermissionItemCard(
        permissionTitle = stringResource(Res.string.camera_permission_status),
        permissionVector = Res.drawable.ic_camera,
        cameraPermissionState = cameraPermissionState,
        onClick = { appViewModel.requestCameraPermission() }
    )
}