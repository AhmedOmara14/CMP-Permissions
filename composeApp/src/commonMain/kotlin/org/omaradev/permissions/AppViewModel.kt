package org.omaradev.permissions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.omaradev.permissions.utils.UIPermissionState

class AppViewModel(
    private val permissionsController: PermissionsController
) : ViewModel() {

    private val _cameraPermissionState = MutableStateFlow(UIPermissionState.UNKNOWN)
    val cameraPermissionState: StateFlow<UIPermissionState> = _cameraPermissionState

    init {
        checkCameraPermission()
    }
    fun checkCameraPermission(){
        viewModelScope.launch {
            val status = permissionsController.isPermissionGranted(Permission.CAMERA)
            _cameraPermissionState.value = when (status) {
                true -> UIPermissionState.GRANTED
                false -> UIPermissionState.DENIED
            }
        }
    }
    fun requestCameraPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.CAMERA)
                _cameraPermissionState.value = UIPermissionState.GRANTED
            } catch (e: DeniedException) {
                // User denied the permission once (can be asked again)
                _cameraPermissionState.value = UIPermissionState.DENIED
            } catch (e: DeniedAlwaysException) {
                _cameraPermissionState.value = UIPermissionState.DENIED_PERMANENTLY
                permissionsController.openAppSettings()
            } catch (e: Exception) {
                _cameraPermissionState.value = UIPermissionState.UNKNOWN
                println("Error requesting camera permission: ${e.message}")
            }
        }
    }
}