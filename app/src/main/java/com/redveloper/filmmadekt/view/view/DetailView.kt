package com.redveloper.filmmadekt.view.view

import android.content.Context
import com.redveloper.filmmadekt.model.movie.ResponMovie
import com.redveloper.filmmadekt.model.tvshow.ResponTvshow

interface DetailView{
    interface ViewMovie{
        fun getData()
        fun showData(
            image : String,
            title : String,
            releaseDate : String,
            rating : String,
            popularity : String,
            description : String
        )
    }
    interface PresenterMovie{
        fun extractData(
            context: Context,
            data: ResponMovie.ResultMovie
        )
        fun insertFavorite(
            context: Context
        )
    }

    interface ViewTvshow{
        fun getData()
        fun showData(
            image : String?,
            title : String?,
            rating : String?,
            popularity : String?,
            description : String?
        )
    }

    interface PresenterTvshow{
        fun extractData(
            context: Context,
            data : ResponTvshow.ResultTvShow
        )
    }
}