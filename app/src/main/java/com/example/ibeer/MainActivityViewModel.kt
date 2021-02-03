package com.example.ibeer


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    suspend fun getApiResults() : List<Cerveza>{
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                DownloadManager.downloadApiResults()
            }
            resultado.await()
        }
    }
}
