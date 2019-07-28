package com.redveloper.filmmadekt.view.ui.fragment.movie.nowplaying


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener

import com.redveloper.filmmadekt.R
import com.redveloper.filmmadekt.model.movie.ResponNowPlayingMovie
import com.redveloper.filmmadekt.presenter.movie.NowPlayingMoviePresenter
import com.redveloper.filmmadekt.view.view.MovieView
import kotlinx.android.synthetic.main.fragment_movie_now_playing.view.*

class MovieNowPlayingFragment : Fragment(), MovieView.NowPlaying {

    private lateinit var presenter : NowPlayingMoviePresenter
    private lateinit var adapter : AdapterMovieNowPlaying

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = NowPlayingMoviePresenter(this)
        callMovie()
    }

    override fun callMovie() {
        context?.resources?.getString(R.string.API_KEY)?.let { presenter.getNowPlayingMovie(it, "en-US", 1) }
    }

    override fun showData(data: List<ResponNowPlayingMovie.Result>?) {
        val layoutManager: CarouselLayoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true)
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
        view?.recyclerview_movie_nowplaying?.layoutManager = layoutManager
        view?.recyclerview_movie_nowplaying?.setHasFixedSize(true)
        adapter = AdapterMovieNowPlaying(data)
        view?.recyclerview_movie_nowplaying?.adapter = adapter
        view?.recyclerview_movie_nowplaying?.addOnScrollListener(CenterScrollListener())
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}
