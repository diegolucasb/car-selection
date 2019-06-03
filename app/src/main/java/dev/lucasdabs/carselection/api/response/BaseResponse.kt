package dev.lucasdabs.carselection.api.response

import com.google.gson.annotations.SerializedName

class BaseResponse(val page: Int? = null,
                   val pageSize: Int? = null,
                   val totalPageCount: Int? = null,
                   @SerializedName("wkda") val data: Map<String, String> = mapOf())