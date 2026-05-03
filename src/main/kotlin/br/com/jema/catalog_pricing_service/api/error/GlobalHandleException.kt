package br.com.jema.catalog_pricing_service.api.error

import br.com.jema.catalog_pricing_service.shared.exception.ProductNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalHandleException {

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFound(
        exception: ProductNotFoundException,
        request: HttpServletRequest
    ) : ResponseEntity<ApiResponseError>{
        val status = HttpStatus.NOT_FOUND
        return ResponseEntity.status(status).body(
            ApiResponseError(
                status = status.value(),
                error = status.reasonPhrase,
                message = exception.message ?: "Resource not found",
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ) : ResponseEntity<ApiResponseError>{
        val status = HttpStatus.BAD_REQUEST
        val details = exception.bindingResult.fieldErrors.map {
            "${it.field}: ${it.defaultMessage}"
        }
        return ResponseEntity.status(status).body(
            ApiResponseError(
                status = status.value(),
                error = status.reasonPhrase,
                message = "Validation failed",
                path = request.requestURI,
                details = details
            )
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(
        exception: IllegalArgumentException,
        request: HttpServletRequest
    ) : ResponseEntity<ApiResponseError>{
        val status = HttpStatus.BAD_REQUEST
        return ResponseEntity.status(status).body(
            ApiResponseError(
                status = status.value(),
                error = status.reasonPhrase,
                message = exception.message ?: "Invalid request",
                path = request.requestURI
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        exception: Exception,
        request: HttpServletRequest
    ) : ResponseEntity<ApiResponseError>{
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(status).body(
            ApiResponseError(
                status = status.value(),
                error = status.reasonPhrase,
                message = exception.message ?: "Unexpected internal server error",
                path = request.requestURI
            )
        )
    }
}