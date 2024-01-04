package com.android.akesosdkapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.chintansoni.android.repositorypattern.R
import com.chintansoni.android.repositorypattern.databinding.ListFragmentBinding
import com.android.akesosdkapp.model.Resource
import com.android.akesosdkapp.util.setInfiniteScroll
import com.android.akesosdkapp.view.adapter.UserRecyclerAdapter
import com.android.akesosdkapp.viewmodel.ListViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    lateinit var mFragmentBinding: ListFragmentBinding

    companion object {
        fun newInstance() = ListFragment()
    }

    private val viewModel by viewModel<ListViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        return mFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        listenToViewModel()
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModel.getUsers()
    }

    private fun listenToViewModel() {
        viewModel.userLiveData.observe(this, Observer { resource ->
            mFragmentBinding.srlList.isRefreshing = false
            resource?.let {
                when (it) {
                    is Resource.Success -> {
                        userRecyclerAdapter.removeLoader()
                        userRecyclerAdapter.setList(ArrayList(it.data))
                    }
                    is Resource.Error ->
                        userRecyclerAdapter.removeLoader()
                    is Resource.Loading ->
                        userRecyclerAdapter.addLoader()
                }
            }
        })
    }

    private lateinit var userRecyclerAdapter: UserRecyclerAdapter

    private fun initViews() {
        userRecyclerAdapter = UserRecyclerAdapter(requireContext())
        mFragmentBinding.rvUsers.adapter = userRecyclerAdapter

        val animator = mFragmentBinding.rvUsers.itemAnimator
        if (animator is androidx.recyclerview.widget.SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }

        mFragmentBinding.rvUsers.setInfiniteScroll {
            if (!userRecyclerAdapter.isLoading()) {
                post {
                    userRecyclerAdapter.addLoader()
                }
                viewModel.getNextPageUsers()
            }
        }

        mFragmentBinding.srlList.setOnRefreshListener {
            viewModel.refreshUsers()
        }
    }
}
