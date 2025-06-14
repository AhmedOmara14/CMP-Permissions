package org.omaradev.permissions.utils

enum class UIPermissionState {
    UNKNOWN,
    GRANTED,
    DENIED,
    // User chose "Don't ask again" or denied multiple times
    DENIED_PERMANENTLY
}