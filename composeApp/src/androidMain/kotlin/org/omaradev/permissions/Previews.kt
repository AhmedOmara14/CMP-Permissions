package org.omaradev.permissions

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cmppermissions.composeapp.generated.resources.Res
import cmppermissions.composeapp.generated.resources.ic_camera
import org.omaradev.permissions.components.PermissionItemCard
import org.omaradev.permissions.utils.UIPermissionState

@Preview
@Composable
private fun PermissionItemCardPreview() {
    PermissionItemCard(
        permissionTitle = "Camera Permission",
        permissionVector = Res.drawable.ic_camera,
        cameraPermissionState = UIPermissionState.GRANTED
    )
}