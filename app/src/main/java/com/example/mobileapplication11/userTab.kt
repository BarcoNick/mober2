package com.example.mobileapplication11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapplication11.adapters.MyAdapter
import com.google.firebase.database.*

class UserTabFragment : Fragment() {

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerview: RecyclerView
    private lateinit var userArrayList: ArrayList<User>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_tab , container, false)
        userRecyclerview = view.findViewById(R.id.userList)
        userRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerview.setHasFixedSize(true)
        userArrayList = arrayListOf<User>()
        return view
    }

    override fun onStart() {
        super.onStart()
        getUserData()
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Users")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    userArrayList.clear() // Clear existing data before adding new items

                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }

                    userRecyclerview.adapter = MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
            }
        })
    }

    companion object {
        fun newInstance(): Fragment {
            return UserTabFragment()
        }
    }
}