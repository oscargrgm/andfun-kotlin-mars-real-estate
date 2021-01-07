/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.network.MarsApi
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response


    // Not necessary anymore.
    // TODO (04) Create a coroutine Job and a CoroutineScope using the Main Dispatcher

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun getMarsRealEstateProperties() {
        // TODO (05) Call viewModelScope.launch and place the rest of the code in it
        viewModelScope.launch {
            // TODO (06) Call MarsApi.retrofitService.getProperties()
            // TODO (07) Surround the Retrofit code with a try/catch, and set _response.value appropriately
            try {
                val listResult = MarsApi.retrofitService.getProperties()
                _response.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (ex: Throwable) {
                _response.value = "Failure: ${ex.message}"
            }
        }
    }

    // Not necessary anymore.
    // TODO (08) Cancel the Coroutine Job when the ViewModel is finished in onCleared
}
