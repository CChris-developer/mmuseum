package com.example.metropolitanmuseum.mvvm

import com.google.gson.annotations.SerializedName

data class ArtObject(
    @SerializedName("objectID") val objectID: Int,
    @SerializedName("primaryImageSmall") val primaryImageSmall: String,
    @SerializedName("department") val department: String,
    @SerializedName("title") val title: String,
    @SerializedName("artistDisplayName") val artistDisplayName: String,
    @SerializedName("artistDisplayBio") val artistDisplayBio: String,
    @SerializedName("objectBeginDate") val objectBeginDate: Int,
    @SerializedName("objectEndDate") val objectEndDate: Int,
    @SerializedName("artistRole") val artistRole: String,
    @SerializedName("culture") val culture: String,
    @SerializedName("period") val period: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("dimensions") val dimensions: String
)
