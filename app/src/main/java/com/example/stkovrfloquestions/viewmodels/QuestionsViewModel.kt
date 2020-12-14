package com.example.stkovrfloquestions.viewmodels

import androidx.databinding.ObservableField
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
    val loadingVisible = ObservableField<Boolean>()
    val listVisible = ObservableField<Boolean>()
    val emptyCardVisible = ObservableField<Boolean>()

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

    fun showLoading() {
        loadingVisible.set(true)
        emptyCardVisible.set(false)
        listVisible.set(false)
    }

    fun showEmpty() {
        loadingVisible.set(false)
        emptyCardVisible.set(true)
        listVisible.set(false)
    }

    fun showList() {
        loadingVisible.set(false)
        emptyCardVisible.set(false)
        listVisible.set(true)
    }
}