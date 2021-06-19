package com.example.domain.model

import java.lang.Exception

sealed class Result<out T> {

data class Success<out T>(val successData : T) : Result<T>()
data class Failure(private val apiError: ApiError)
    : Result<Nothing>()
}

class ApiError(private val exception: Exception, val message: String? = exception.localizedMessage)