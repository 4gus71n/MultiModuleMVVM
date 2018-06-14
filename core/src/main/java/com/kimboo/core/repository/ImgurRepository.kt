package com.kimboo.core.repository

import com.kimboo.core.db.ImgurDAO
import com.kimboo.core.retrofit.api.ImgurAPI

class ImgurRepository(val imgurAPI: ImgurAPI, val imgurDAO: ImgurDAO) {

    fun test(): String = "Sarassa"

}
