package org.omaradev.permissions.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cmppermissions.composeapp.generated.resources.Res
import cmppermissions.composeapp.generated.resources.contact_permission_status
import cmppermissions.composeapp.generated.resources.gallery_permission_status
import cmppermissions.composeapp.generated.resources.ic_contact_phone
import cmppermissions.composeapp.generated.resources.ic_gallery
import org.jetbrains.compose.resources.stringResource
import org.omaradev.permissions.AppViewModel

@Composable
fun ContactPermissionCard(appViewModel: AppViewModel) {
    val contactPermissionState by appViewModel.contactPermissionState.collectAsState()
    PermissionItemCard(
        permissionTitle = stringResource(Res.string.contact_permission_status),
        permissionVector = Res.drawable.ic_contact_phone,
        cameraPermissionState = contactPermissionState,
        onClick = { appViewModel.requestContactPermission() }
    )
}