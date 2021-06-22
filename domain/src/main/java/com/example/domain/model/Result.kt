package com.example.domain.model

import java.lang.Exception

sealed class Result<out T> {

data class Success<out T>(val successData : T) : Result<T>()
data class Failure(val exception: Exception, val message: String? = exception.localizedMessage)
    : Result<Nothing>()
}

data class ApiError(val message: String) {
    constructor() : this("")
}