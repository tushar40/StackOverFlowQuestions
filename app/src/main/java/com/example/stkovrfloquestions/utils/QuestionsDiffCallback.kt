package com.example.stkovrfloquestions.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.stkovrfloquestions.models.Item

class QuestionsDiffCallback: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(
        oldItem: Item,
        newItem: Item
    ): Boolean {
        val sameOwner = oldItem.owner.user_id == newItem.owner.user_id
        val sameQuestion = oldItem.question_id == newItem.question_id

        return sameOwner && sameQuestion
    }

    override fun areContentsTheSame(
        oldItem: Item,
        newItem: Item
    ): Boolean {
        val sameQuestion = oldItem.question_id == newItem.question_id
        val notEdited = oldItem.last_edit_date == newItem.last_edit_date

        return sameQuestion && notEdited
    }
}