package com.ranggacikal.challengechapter5.model

data class LoginResponse(
    var `data`: Data? = null,
    var success: Boolean? = null
) {
    data class Data(
        var _id: String?,
        var email: String?,
        var token: String?,
        var username: String?
    )
}