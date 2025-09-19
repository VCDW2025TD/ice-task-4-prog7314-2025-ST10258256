package com.example.memestreamapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memestreamapp.FeedAdapter
import com.example.memestreamapp.R
import com.example.memestreamapp.data.Meme

class FeedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FeedAdapter
    private val memes = mutableListOf<Meme>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_feed, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        adapter = FeedAdapter(memes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fetchMemes()
    }

    private fun fetchMemes() {
        // Use dummy data
        memes.add(
            Meme(
                localId = 0L, // Room will auto-generate this
                id = "1",
                userId = "user123",
                imageUrl = "https://media.giphy.com/media/ICOgUNjpvO0PC/giphy.gif",
                caption = "Funny meme",
                lat = null,
                lng = null,
                timestamp = System.currentTimeMillis(),
                isSynced = true
            )
        )
        adapter.notifyDataSetChanged()
    }
}
