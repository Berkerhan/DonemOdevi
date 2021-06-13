package com.berkerhan.donemodevi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berkerhan.donemodevi.R
import com.berkerhan.donemodevi.adapter.UserAdapter
import com.berkerhan.donemodevi.MainActivity
import com.berkerhan.donemodevi.MainViewModel
import com.berkerhan.donemodevi.utils.Resource
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var userAdapter: UserAdapter
    private lateinit var rvUsers: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).mainViewModel


        rvUsers=view.findViewById(R.id.user_list)
        setupRecyclerView()

        navController=Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)

        rvUsers=view.findViewById(R.id.user_list)

        viewModel.userRes.observe(viewLifecycleOwner, Observer { response->
            when (response) {
                is Resource.Loading-> {
                    (requireActivity() as MainActivity).showLoading()
                }
                is Resource.Error -> {
                    (requireActivity() as MainActivity).hideLoading()
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Success -> {
                    (requireActivity() as MainActivity).hideLoading()
                    response.data?.let { userResponse ->
                        if (userResponse!=null) {
                            val userList=userResponse.toList()
                            userAdapter.differ.submitList(userList)
                        }
                        else {
                            list_data_is_null.visibility=View.VISIBLE
                        }
                    }
                }
            }
            response.data=null
            response.message=null
        })

        userAdapter.setUserItemClickListener {
            val bundle=Bundle().apply {
                Log.i("DetailItem", it.toString())
                putSerializable("userItemDetail", it)
            }

            navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }
    }

    private fun setupRecyclerView() {
        userAdapter= UserAdapter()
        rvUsers.apply {
            adapter=userAdapter
            layoutManager=LinearLayoutManager(activity)
            viewModel.getUser()
        }
    }
}