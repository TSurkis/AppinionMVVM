package com.tsurkis.appinionmvvm.screens.quotes

import androidx.recyclerview.widget.DiffUtil
import com.tsurkis.appinionmvvm.remoteapi.opinionatedquotes.responseobjects.Quote

class QuoteDataDifferenceChecker : DiffUtil.ItemCallback<Quote>() {
    override fun areItemsTheSame(oldItem: Quote, newItem: Quote) =
            oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote) =
            oldItem == newItem
}