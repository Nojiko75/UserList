package com.example.data.api.util

import com.example.domain.model.Result
import retrofit2.Response

fun <T : Any> handleFailure(resp: Response<T>): Result.Failure {
    val error = ApiErrorUtils.parseError(resp)
    return Result.Failure(Exception(error.message))
}