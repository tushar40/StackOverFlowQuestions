package com.example.stkovrfloquestions.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stkovrfloquestions.adapters.QuestionsAdapter
import com.example.stkovrfloquestions.databinding.FragmentQuestionsBinding
import com.example.stkovrfloquestions.models.Item
import com.example.stkovrfloquestions.viewmodels.QuestionsViewModel
import com.example.stkovrfloquestions.utils.FragmentListener
import kotlinx.android.synthetic.main.fragment_questions.*

class QuestionsFragment: Fragment() {

    /**
     * Member variables
     */

    private val questionsViewModel: QuestionsViewModel by activityViewModels()
    private val callback by lazy {
        activity as FragmentListener
    }
    private val tweetsAdapter by lazy {
        QuestionsAdapter()
    }

    /**
     * Lifecycle Methods
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        val view: View = binding.root
        binding.questionsViewModel = questionsViewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    /**
     * Custom Methods
     */

    private fun setUpUI() {
        recyclerView_questions.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tweetsAdapter
        }

        questionsViewModel.getQuestions()

        questionsViewModel.questionData.observe(viewLifecycleOwner, Observer { questions ->
            showQuestions(questions)
        })
    }

    private fun showQuestions(questions: List<Item>) {
        tweetsAdapter.submitList(questions)
    }

}