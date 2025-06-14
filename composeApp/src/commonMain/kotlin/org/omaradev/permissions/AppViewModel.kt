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

    private val _galleryPermissionState = MutableStateFlow(UIPermissionState.UNKNOWN)
    val galleryPermissionState: StateFlow<UIPermissionState> = _galleryPermissionState

    private val _contactPermissionState = MutableStateFlow(UIPermissionState.UNKNOWN)
    val contactPermissionState: StateFlow<UIPermissionState> = _contactPermissionState

    private val _recordAudioPermissionState = MutableStateFlow(UIPermissionState.UNKNOWN)
    val recordAudioPermissionState: StateFlow<UIPermissionState> = _recordAudioPermissionState

    init {
        checkCameraPermission()
        checkLocationPermission()
        checkGalleryPermission()
        checkContactPermission()
        checkRecordAudioPermission()
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
            }
        }
    }

    private fun checkGalleryPermission(){
        viewModelScope.launch {
            val status = permissionsController.isPermissionGranted(Permission.GALLERY)
            _galleryPermissionState.value = when (status) {
                true -> UIPermissionState.GRANTED
                false -> UIPermissionState.DENIED
            }
        }
    }

    fun requestGalleryPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.GALLERY)
                _galleryPermissionState.value = UIPermissionState.GRANTED
            } catch (e: DeniedException) {
                // User denied the permission once (can be asked again)
                _galleryPermissionState.value = UIPermissionState.DENIED
            } catch (e: DeniedAlwaysException) {
                _galleryPermissionState.value = UIPermissionState.DENIED_PERMANENTLY
                permissionsController.openAppSettings()
            } catch (e: Exception) {
                _galleryPermissionState.value = UIPermissionState.UNKNOWN
            }
        }
    }

    private fun checkContactPermission(){
        viewModelScope.launch {
            val status = permissionsController.isPermissionGranted(Permission.CONTACTS)
            _contactPermissionState.value = when (status) {
                true -> UIPermissionState.GRANTED
                false -> UIPermissionState.DENIED
            }
        }
    }

    fun requestContactPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.CONTACTS)
                _contactPermissionState.value = UIPermissionState.GRANTED
            } catch (e: DeniedException) {
                // User denied the permission once (can be asked again)
                _contactPermissionState.value = UIPermissionState.DENIED
            } catch (e: DeniedAlwaysException) {
                _contactPermissionState.value = UIPermissionState.DENIED_PERMANENTLY
                permissionsController.openAppSettings()
            } catch (e: Exception) {
                _contactPermissionState.value = UIPermissionState.UNKNOWN
            }
        }
    }

    private fun checkRecordAudioPermission(){
        viewModelScope.launch {
            val status = permissionsController.isPermissionGranted(Permission.RECORD_AUDIO)
            _recordAudioPermissionState.value = when (status) {
                true -> UIPermissionState.GRANTED
                false -> UIPermissionState.DENIED
            }
        }
    }

    fun requestRecordAudioPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.RECORD_AUDIO)
                _recordAudioPermissionState.value = UIPermissionState.GRANTED
            } catch (e: DeniedException) {
                // User denied the permission once (can be asked again)
                _recordAudioPermissionState.value = UIPermissionState.DENIED
            } catch (e: DeniedAlwaysException) {
                _recordAudioPermissionState.value = UIPermissionState.DENIED_PERMANENTLY
                permissionsController.openAppSettings()
            } catch (e: Exception) {
                _recordAudioPermissionState.value = UIPermissionState.UNKNOWN
            }
        }
    }
}