package com.ranggacikal.challengechapter5.model

data class BattleHistoryResponse(
    val data: List<Data>,
    val success: Boolean
) {
    data class Data(
        val _id: String,
        val createdAt: String,
        val message: String,
        val mode: String,
        val result: String,
        val updatedAt: String
    )
}