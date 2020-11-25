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

class QuestionsAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<Item, QuestionsAdapter.ViewHolder>(QuestionsDiffCallback()) {

    interface OnItemClickListener {
        fun onItemClicked(url: String)
    }

    /**
     * RecyclerView Adapter Methods
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
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
        private var userTypeTextView: TextView? = null
        private var answersCountTextView: TextView? = null
        private var viewCountTextView: TextView? = null
        private var titleTextView: TextView? = null
        private var tagsTextView: TextView? = null
        private var context: Context? = null

        init {
            nameTextView = itemView.findViewById(R.id.textView_name)
            userTypeTextView = itemView.findViewById(R.id.textView_user_type)
            answersCountTextView = itemView.findViewById(R.id.textView_answers_count)
            viewCountTextView = itemView.findViewById(R.id.textView_view_count)
            titleTextView = itemView.findViewById(R.id.textView_title)
            tagsTextView = itemView.findViewById(R.id.textView_tags)
            profileImageView = itemView.findViewById(R.id.imageView_profile_image)
            context = parent.context
        }

        fun bind(question: Item, clickListener: OnItemClickListener) {
            nameTextView?.text = question.owner.display_name
            userTypeTextView?.text = question.owner.user_type
            answersCountTextView?.text = question.answer_count.toString()
            viewCountTextView?.text = question.view_count.toString()
            titleTextView?.text = question.title
            tagsTextView?.text = context?.getString(R.string.tags, question.tags.reduce {a, b -> a + ", "+ b})
            loadImage(question.owner.profile_image)

            itemView.setOnClickListener {
                clickListener.onItemClicked(question.link)
            }
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