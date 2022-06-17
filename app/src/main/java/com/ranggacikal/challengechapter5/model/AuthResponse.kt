package com.ranggacikal.challengechapter5.model

data class AuthResponse(
    var `data`: Data?,
    var errors: String?,
    var success: Boolean
) {
    data class Data(
        var _id: String?,
        var email: String?,
        var username: String?
    )
}