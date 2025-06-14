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

    private val _locationPermissionState = MutableStateFlow(UIPermissionState.UNKNOWN)
    val locationPermissionState: StateFlow<UIPermissionState> = _locationPermissionState

    init {
        checkCameraPermission()
        checkLocationPermission()
    }
    private fun checkCameraPermission(){
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

    private fun checkLocationPermission(){
        viewModelScope.launch {
            val status = permissionsController.isPermissionGranted(Permission.COARSE_LOCATION)
            _locationPermissionState.value = when (status) {
                true -> UIPermissionState.GRANTED
                false -> UIPermissionState.DENIED
            }
        }
    }

    fun requestLocationPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.COARSE_LOCATION)
                _locationPermissionState.value = UIPermissionState.GRANTED
            } catch (e: DeniedException) {
                // User denied the permission once (can be asked again)
                _locationPermissionState.value = UIPermissionState.DENIED
            } catch (e: DeniedAlwaysException) {
                _locationPermissionState.value = UIPermissionState.DENIED_PERMANENTLY
                permissionsController.openAppSettings()
            } catch (e: Exception) {
                _locationPermissionState.value = UIPermissionState.UNKNOWN
                println("Error requesting camera permission: ${e.message}")
            }
        }
    }
}