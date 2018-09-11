package com.tsurkis.appinionmvvm.screens.quotes

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.tsurkis.appinionmvvm.R
import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.responseobjects.Quote

class QuoteViewHolder(
        rootView: View
) : RecyclerView.ViewHolder(rootView) {

    private val quoteTV: TextView = rootView.findViewById(R.id.quoteTextView)
    private val authorTV: TextView = rootView.findViewById(R.id.authorTextView)

    fun bind(quote: Quote) {
        quoteTV.text = quote.quote
        authorTV.text = quote.author
    }
}