package com.redveloper.filmmadekt.view.ui.activity.tvshow

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.redveloper.filmmadekt.R
import com.redveloper.filmmadekt.presenter.tvshow.DetailTvshowPresenter
import com.redveloper.filmmadekt.utils.Constant
import com.redveloper.filmmadekt.view.ui.activity.AdapterPict
import com.redveloper.filmmadekt.view.view.DetailView
import kotlinx.android.synthetic.main.activity_tvshow_detail.*
import kotlinx.android.synthetic.main.content_detail_appar.*
import kotlinx.android.synthetic.main.content_tvshow_detail.*

class TvshowDetailActivity : AppCompatActivity(), DetailView.ViewTvshow {

    private lateinit var presenter: DetailTvshowPresenter
    private lateinit var adapter : AdapterPict
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        presenter = DetailTvshowPresenter(this)
        getData()

        favoriteState()
    }

    override fun getData() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            presenter.extractData(
                applicationContext,
                bundle.getParcelable(Constant.dataTvShow)
            )
        }
    }

    override fun showData(
        image: String?,
        title: String?,
        releaseDate: String?,
        rating: String?,
        popularity: String?,
        description: String?,
        vote : String?,
        year : String?,
        backdrop : String?) {
        //image
        val images = listOf<String?>(
            image,
            backdrop
        )
        setAdapter(images)

        //appbar
        Glide.with(applicationContext)
            .load(backdrop)
            .into(imageview_appbar_tvshow_detail)

        textview_title_appbar__detail.setText(title)
        textview_year__detail.setText(year)
        textview_rating_appbar__detail.setText(rating)
        textview_vote_appbar__detail.setText(vote)

        var ratingLong = rating?.toDouble()
        if (ratingLong != null) {
            if(ratingLong > 5.0){
                ratingLong = ratingLong - 5.0
            }
        }

        if (ratingLong != null) {
            ratingbar_appbar__detail.rating = ratingLong.toFloat()
        }

        //content
        textview_storyline_tvshow_detail.setText(description)
        textview_original_title_tvshow_detail.setText(title)
        textview_release_date_tvshow_detail.setText(releaseDate)
    }

    private fun setAdapter(data: List<String?>){
        recyclerview_pict.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
        adapter = AdapterPict(data as List<String>)
        recyclerview_pict.adapter = adapter
    }
    
    override fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_star_fill)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_star_no_fill)
        }
    }

    override fun addToFavoriteMovie() {
        presenter.insertFavorite(applicationContext)
    }

    override fun removeToFavoriteMovie() {
        presenter.removeFavorite(applicationContext)
    }

    override fun favoriteState() {
        if (presenter.checkFavorite(applicationContext)){
            isFavorite = true
        }
    }
}
