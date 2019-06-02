package dev.lucasdabs.carselection.api.response

import com.google.gson.annotations.SerializedName

class BaseResponse(@Transient open val page: Long? = null,
                   @Transient open val pageSize: Long? = null,
                   @Transient open val totalPageCount: Long? = null,
                   @SerializedName("wkda") val data: Map<String, String> = mapOf())