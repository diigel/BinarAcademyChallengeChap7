data class RegisterResponse(
    val `data`: Data?,
    val success: Boolean?
) {
    data class Data(
        val id: String?,
        val email: String?,
        val username: String?
    )
}