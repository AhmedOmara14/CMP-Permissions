package org.omaradev.permissions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cmppermissions.composeapp.generated.resources.Res
import cmppermissions.composeapp.generated.resources.denied_permanently_permission_status
import cmppermissions.composeapp.generated.resources.denied_permission_status
import cmppermissions.composeapp.generated.resources.granted_permission_status
import cmppermissions.composeapp.generated.resources.request_permission_status
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.omaradev.permissions.ui.themes.ActiveColor
import org.omaradev.permissions.ui.themes.PendingColor
import org.omaradev.permissions.utils.UIPermissionState

@Composable
fun PermissionItemCard(
    permissionTitle: String,
    permissionVector: DrawableResource,
    cameraPermissionState: UIPermissionState,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (cameraPermissionState == UIPermissionState.GRANTED) ActiveColor else PendingColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp).clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(permissionVector),
                contentDescription = "@null",
                modifier = Modifier.size(30.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = permissionTitle,
                    style = MaterialTheme.typography.titleMedium
                )


                Text(
                    text = when (cameraPermissionState) {
                        UIPermissionState.DENIED_PERMANENTLY -> stringResource(Res.string.denied_permanently_permission_status)
                        UIPermissionState.DENIED -> stringResource(Res.string.denied_permission_status)
                        UIPermissionState.GRANTED -> stringResource(Res.string.granted_permission_status)
                        else -> stringResource(Res.string.request_permission_status)
                    },
                    style = MaterialTheme.typography.bodySmall
                )

            }
        }
    }
}