package com.example.stkovrfloquestions.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stkovrfloquestions.models.Item
import com.example.stkovrfloquestions.repositories.QuestionRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuestionsViewModel: ViewModel() {

    /**
     * Member variables
     */

    private val minAnswers = 1
    val questionData = MutableLiveData<List<Item>?>()

    /**
     * Methods
     */

    fun getQuestions() {
        viewModelScope.launch {
            QuestionRepository.getQuestions(minAnswers).collect { items ->
                questionData.postValue(items)
            }
        }
    }
}