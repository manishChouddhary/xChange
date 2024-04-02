package com.paypay.xChange.core.data.exception


enum class ErrorCode{
    HTTP_EXCEPTION, CONNECTION_TIME_OUT, UNKNOWN_ERROR, BODY_NULL
}
class XchangeException(code: ErrorCode): Exception(){
    override val message = when(code){
        ErrorCode.HTTP_EXCEPTION -> "Request failed"
        ErrorCode.CONNECTION_TIME_OUT -> "Service not responding"
        ErrorCode.UNKNOWN_ERROR -> "Unknown error occurred"
        ErrorCode.BODY_NULL -> "Empty response"
    }
}