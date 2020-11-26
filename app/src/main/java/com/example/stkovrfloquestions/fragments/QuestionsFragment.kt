package com.example.stkovrfloquestions.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stkovrfloquestions.R
import com.example.stkovrfloquestions.adapters.QuestionsAdapter
import com.example.stkovrfloquestions.databinding.FragmentQuestionsBinding
import com.example.stkovrfloquestions.models.Item
import com.example.stkovrfloquestions.viewmodels.QuestionsViewModel
import com.example.stkovrfloquestions.utils.FragmentListener
import kotlinx.android.synthetic.main.fragment_questions.*

class QuestionsFragment: Fragment(), QuestionsAdapter.OnItemClickListener {

    /**
     * Member variables
     */

    private val questionsViewModel: QuestionsViewModel by activityViewModels()
    private val callback by lazy {
        activity as FragmentListener
    }
    private val tweetsAdapter by lazy {
        QuestionsAdapter(this)
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

    override fun onStart() {
        super.onStart()

        questionsViewModel.questionData.observe(viewLifecycleOwner, Observer { questions ->
            showQuestions(questions)
        })
    }

    override fun onStop() {
        super.onStop()

        questionsViewModel.questionData.removeObservers(viewLifecycleOwner)
    }

    /**
     * QuestionsAdapter.OnItemClickListener Method
     */

    override fun onItemClicked(url: String) {
        val args = WebViewFragmentArgs.Builder(url).build().toBundle()
        callback.navigateFragment(R.id.action_questionsFragment_to_webViewFragment, null, args)
    }

    /**
     * Custom Methods
     */

    private fun setUpUI() {
        recyclerView_questions.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = tweetsAdapter
            setHasFixedSize(true)
        }

        swipeRefreshLayout.setOnRefreshListener {
            reload()
            swipeRefreshLayout.isRefreshing = false
        }

        reload()

        button_reload.setOnClickListener {
            reload()
        }
    }

    private fun reload() {
        questionsViewModel.getQuestions()
        showLoading()
    }

    private fun showQuestions(questions: List<Item>?) {
        if (questions == null) {
            showEmpty()
            callback.makeToast("Couldn't fetch the details")
        } else {
            showList()
        }

        tweetsAdapter.submitList(questions)
    }

    private fun showLoading() {
        loadingView.visibility = View.VISIBLE
        cardView_empty.visibility = View.GONE
        recyclerView_questions.visibility = View.GONE
    }

    private fun showEmpty() {
        loadingView.visibility = View.GONE
        cardView_empty.visibility = View.VISIBLE
        recyclerView_questions.visibility = View.GONE
    }

    private fun showList() {
        loadingView.visibility = View.GONE
        cardView_empty.visibility = View.GONE
        recyclerView_questions.visibility = View.VISIBLE
    }
}