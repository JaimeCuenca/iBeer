package com.example.ibeer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DetailsActivityViewModel : ViewModel(){
    suspend fun getSingleItem(userChoice : String) : List<Cerveza>? {
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                DownloadManager.downloadApiSingleResult(userChoice)
            }
            resultado.await()
        }
    }
}
