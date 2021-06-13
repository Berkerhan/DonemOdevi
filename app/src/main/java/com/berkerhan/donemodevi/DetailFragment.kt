package com.berkerhan.donemodevi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.berkerhan.donemodevi.data.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var userItem: User

    private lateinit var avatar: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var id: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userItem=args.userItemDetail
        if (userItem!=null) {
            avatar=userItem.avatar
            id=userItem.id
            name=userItem.name
            email=userItem.email
        }
        else {
            detail_data_is_null.visibility=View.VISIBLE
        }

        Glide.with(requireContext()).load(avatar).into(user_detail_image)
        user_detail_id.text=id
        user_detail_name.text=name
        user_detail_email.text=email
    }
}