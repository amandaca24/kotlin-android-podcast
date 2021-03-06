package br.ufpe.cin.android.podcast.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.ufpe.cin.android.podcast.EpisodeActivity
import br.ufpe.cin.android.podcast.data.Feed
import br.ufpe.cin.android.podcast.databinding.FeedRvBinding
import com.squareup.picasso.Picasso

class FeedAdapter(private val inflater: LayoutInflater) :
    ListAdapter<Feed, FeedAdapter.FeedViewHolder>(FeedDiffer) {

    class FeedViewHolder(private val binding: FeedRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Vai ligar os atibutos da entidade Feed aos componentes da view
        fun bindTo(feed: Feed) {
            binding.feedTitle.text = feed.titulo
            binding.feedDescription.text = feed.descricao
            binding.feedUrl.text = feed.linkSite

            if(feed.imagemURL != null){
                var img = Uri.parse(feed.imagemURL)
                Picasso.get().load(img).into(binding.feedImg)
            }

            //Ao clicar no card, vai abrir a lista de episódios do Feed
            binding.root.setOnClickListener {
                val context = binding.feedUrl.context
                val url = feed.urlFeed
                val intentFeed = Intent(context, EpisodeActivity::class.java)
                intentFeed.putExtra("url", url)
                context.startActivity(intentFeed)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = FeedRvBinding.inflate(inflater, parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bindTo(getItem(position))

    }

    private object FeedDiffer : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }

    }

}
