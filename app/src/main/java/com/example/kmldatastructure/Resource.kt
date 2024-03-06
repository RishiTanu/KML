package com.example.kmldatastructure

sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error(val message: String) : Resource<Nothing>()
    class Loading : Resource<Nothing>()
}
