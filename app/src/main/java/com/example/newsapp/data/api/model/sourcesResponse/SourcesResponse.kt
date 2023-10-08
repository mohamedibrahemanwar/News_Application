package com.example.newsapp.data.api.model.sourcesResponse

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.newsapp.data.api.model.sourcesResponse.Sources
import com.google.gson.annotations.SerializedName

@Parcelize
data class SourcesResponse(

    @field:SerializedName("sources")
	val sources: List<Sources?>? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
	val message: String? = null,
    @field:SerializedName("code")
	val code: String? = null
) : Parcelable