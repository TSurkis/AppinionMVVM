package com.tsurkis.appinionmvvm.screens.quotes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.tsurkis.appinionmvvm.R
import com.tsurkis.appinionmvvm.application.ViewModelFactory
import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.responseobjects.Quote
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_quotes.*
import javax.inject.Inject

class QuotesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: QuotesViewModel

    lateinit var quotesAdapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(QuotesViewModel::class.java)

        initializeScreenStateObservation()

        initializeQuoteListDataObservation()

        initializeRecyclerView()

        refreshButton.setOnClickListener {
            viewModel.requestRandomQuotes()
        }

        viewModel.onViewInitialized()
    }

    private fun initializeScreenStateObservation() {
        viewModel.getObservedScreenState().observe(
                this,
                Observer<ScreenState> { screenState -> onScreenStateChanged(screenState) }
        )
    }

    private fun initializeQuoteListDataObservation(): QuotesAdapter {
        quotesAdapter = QuotesAdapter(QuoteDataDifferenceChecker())
        viewModel.getObservedQuotesDataCollection().observe(
                this,
                Observer<List<Quote>> { quotes ->
                    quotesAdapter.submitList(quotes)
                }
        )
        return quotesAdapter
    }

    private fun initializeRecyclerView() {
        quotesRecyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = layoutManager
            this.adapter = quotesAdapter
        }
    }

    private fun onScreenStateChanged(screenState: ScreenState?) {
        screenState?.let {
            showProgressBar(it.showProgressBar)

            showQuotesList(it.showListOfQuotes)

            showTimeFrameError(it.showTimeFrameError, it.timeLeftToMakeNextRequest)
        }
    }

    private fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) VISIBLE else GONE
    }

    private fun showQuotesList(show: Boolean) {
        quotesRecyclerView.visibility = if (show) VISIBLE else GONE
    }

    private fun showTimeFrameError(show: Boolean, millisecondsLeftUntilNextRequest: Long?) {
        if (show) {
            millisecondsLeftUntilNextRequest?. let {
                val text = getString(R.string.quotes_screen_time_frame_error_text, it)
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
