package com.ranggacikal.challengechapter5.model

data class SetBattleResultResponse(
    val data: Data,
    val success: Boolean
) {
    data class Data(
        val _id: String,
        val createdAt: String,
        val mode: String,
        val result: String,
        val updatedAt: String
    )
}