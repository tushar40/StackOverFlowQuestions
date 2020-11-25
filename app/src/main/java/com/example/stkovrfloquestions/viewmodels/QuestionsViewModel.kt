package com.example.stkovrfloquestions.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stkovrfloquestions.QuestionRepository
import kotlinx.coroutines.launch

class QuestionsViewModel: ViewModel() {

    /**
     * Member variables
     */

    val questionData = QuestionRepository.questionsData
    private val minAnswers = 1

    /**
     * Methods
     */

    fun getQuestions() {
        viewModelScope.launch {
            QuestionRepository.getQuestions(minAnswers)
        }
    }
}