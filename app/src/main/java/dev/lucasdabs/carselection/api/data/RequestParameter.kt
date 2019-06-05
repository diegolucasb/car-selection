package dev.lucasdabs.carselection.api.data

import android.os.Parcelable
import dev.lucasdabs.carselection.BuildConfig
import dev.lucasdabs.carselection.util.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestParameter(
    var page: Pair<String, Int> = Pair("page", Constants.Pagination.INITIAL_PAGE),
    var pageSize: Pair<String, Int> = Pair("pageSize", Constants.Pagination.PAGE_SIZE),
    var key: Pair<String, String> = Pair("wa_key", BuildConfig.WA_KEY),
    var manufacturerId: Pair<String, BaseData>? = null,
    var modelId: Pair<String, BaseData>? = null) : Parcelable