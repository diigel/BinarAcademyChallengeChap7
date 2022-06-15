package com.ranggacikal.challengechapter5.model

import com.google.gson.annotations.SerializedName

data class ResponseDataUsers(

	@field:SerializedName("ResponseDataUsers")
	val responseDataUsers: List<ResponseDataUsersItem?>? = null
)

data class ResponseDataUsersItem(

	@field:SerializedName("kode_team")
	val kodeTeam: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama_lengkap")
	val namaLengkap: String? = null,

	@field:SerializedName("no_handphone")
	val noHandphone: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
