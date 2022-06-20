package com.ranggacikal.challengechapter5.model

data class AuthResponse(
    var `data`: Data? = null,
    var errors: String? = null,
    var success: Boolean? = null
) {
    data class Data(
        var _id: String?,
        var email: String?,
        var username: String?
    )
}