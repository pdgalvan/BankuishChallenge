package com.example.sampleproject.util

interface ListMapper<T : Any, Model : Any> : Mapper<List<T>, List<Model>>