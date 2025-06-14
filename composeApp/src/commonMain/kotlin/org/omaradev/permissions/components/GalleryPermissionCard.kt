package org.omaradev.permissions.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cmppermissions.composeapp.generated.resources.Res
import cmppermissions.composeapp.generated.resources.gallery_permission_status
import cmppermissions.composeapp.generated.resources.ic_gallery
import org.jetbrains.compose.resources.stringResource
import org.omaradev.permissions.AppViewModel

@Composable
fun GalleryPermissionCard(appViewModel: AppViewModel) {
    val galleryPermissionState by appViewModel.galleryPermissionState.collectAsState()
    PermissionItemCard(
        permissionTitle = stringResource(Res.string.gallery_permission_status),
        permissionVector = Res.drawable.ic_gallery,
        cameraPermissionState = galleryPermissionState,
        onClick = { appViewModel.requestGalleryPermission() }
    )
}