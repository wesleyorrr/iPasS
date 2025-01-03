package com.freelanceror.ipaas.model

import java.io.Serializable

data class NasaPicture(
    val title: String,
    val url: String,
    val explanation: String,
    val hdurl: String,
    val date: String
) : Serializable