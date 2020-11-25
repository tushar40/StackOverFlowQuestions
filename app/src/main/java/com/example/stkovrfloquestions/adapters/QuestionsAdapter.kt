package com.example.stkovrfloquestions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stkovrfloquestions.R
import com.example.stkovrfloquestions.models.Item
import com.example.stkovrfloquestions.utils.QuestionsDiffCallback

class QuestionsAdapter: ListAdapter<Item, QuestionsAdapter.ViewHolder>(QuestionsDiffCallback()) {

    /**
     * RecyclerView Adapter Methods
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * RecyclerView.Viewholder Class
     */

    class ViewHolder(inflater: LayoutInflater, var parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.question_item, parent, false
        )
    ) {

        private var profileImageView: ImageView? = null
        private var nameTextView: TextView? = null
        private var handleTextView: TextView? = null
        private var retweetsTextView: TextView? = null
        private var favouritesTextView: TextView? = null
        private var tweetTextTextView: TextView? = null
        private var context: Context? = null

        init {
            nameTextView = itemView.findViewById(R.id.textView_name)
            handleTextView = itemView.findViewById(R.id.textView_handle)
            retweetsTextView = itemView.findViewById(R.id.textView_retweets)
            favouritesTextView = itemView.findViewById(R.id.textView_fav)
            tweetTextTextView = itemView.findViewById(R.id.textView_tweet)
            profileImageView = itemView.findViewById(R.id.imageView_profile_image)
            context = parent.context
        }

        fun bind(question: Item) {
            nameTextView?.text = question.owner.display_name
            handleTextView?.text = question.owner.user_type
            retweetsTextView?.text = question.answer_count.toString()
            favouritesTextView?.text = question.view_count.toString()
            tweetTextTextView?.text = question.title

            loadImage(question.owner.profile_image)
        }

        private fun loadImage(url: String) {
            Glide.with(context!!)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.ic_person_24dp)
                .into(profileImageView!!)
        }
    }
}