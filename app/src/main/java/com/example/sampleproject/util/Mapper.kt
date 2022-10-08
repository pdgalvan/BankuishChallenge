package com.example.sampleproject.util

interface Mapper<T : Any, Model : Any> {
    fun toModel(value: T): Model
}