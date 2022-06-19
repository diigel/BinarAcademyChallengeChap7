package com.ranggacikal.challengechapter5.model

data class RegisterResponse(
    val success: Boolean? = null,
    val errors : String? = "",
    val `data`: Data? = null
) {
    data class Data(
        val id: String?,
        val email: String?,
        val username: String?
    )
}
