package com.paypay.xChange.core.data.remote

import com.paypay.xChange.core.data.exception.ErrorCode
import com.paypay.xChange.core.data.exception.XchangeException
import com.paypay.xChange.core.model.Currency
import retrofit2.HttpException
import java.io.IOException

class RemoteDataRepository(private val service: ExchangeService) {
    suspend fun getCurrencies(): List<Currency> = try {
        val response = service.getCurrencies()
        if (response.isSuccessful) {
            response.body()?.let {
                    it.map { currency ->
                        Currency(
                            code = currency.key,
                            name = currency.value
                        )
                    }
                )
            } ?: throw XchangeException(ErrorCode.HTTP_EXCEPTION)
        } else {
            throw XchangeException(ErrorCode.HTTP_EXCEPTION)
        }
    } catch (ex: IOException) {
        throw XchangeException(ErrorCode.CONNECTION_TIME_OUT)
    } catch (ex: HttpException) {
        throw XchangeException(ErrorCode.HTTP_EXCEPTION)
    }

    suspend fun getCurrencyRates(): List<Currency> = try {
        val response = service.getCurrencyRate()
        if (response.isSuccessful) {
            response.body()?.let {
                    it.rates.map { currency ->
                        Currency(
                            code = currency.key,
                            rate = currency.value
                        )
                    }
            } ?: throw XchangeException(ErrorCode.HTTP_EXCEPTION)
        } else {
            throw XchangeException(ErrorCode.HTTP_EXCEPTION)
        }
    } catch (ex: IOException) {
        throw XchangeException(ErrorCode.CONNECTION_TIME_OUT)
    } catch (ex: HttpException) {
        throw XchangeException(ErrorCode.HTTP_EXCEPTION)
    }
}