package com.example.sampleproject.util

import android.view.Display

interface ListMapper<T : Any, Model : Any> : Mapper<List<T>, List<Model>> {

}