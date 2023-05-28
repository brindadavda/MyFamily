package com.application.myfamily

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMembers = listOf<MemberModel>(
            MemberModel(
                "Tina",
                "9th floor , 2nd bulding , 9th floor , 2nd bulding , radhe park , rajkot , gujarat",
                "80%",
                "223M"
            ),
            MemberModel(
                "Rajvi",
                "9th floor , 2nd bulding , 9th floor , 2nd bulding , radhe park , rajkot , gujarat",
                "10%",
                "23M"
            ),
            MemberModel(
                "Priya",
                "9th floor , 2nd bulding , 9th floor , 2nd bulding , radhe park , rajkot , gujarat",
                "50%",
                "200M"
            ),
            MemberModel(
                "Manali",
                "9th floor , 2nd bulding , 9th floor , 2nd bulding , radhe park , rajkot , gujarat",
                "40%",
                "100M"
            ),
        )

        val adapter = MemberAdapter(listMembers)
        val adapterContact = ContactAdapter(fetchContacts())

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.rv)
        val rvContact = requireView().findViewById<RecyclerView>(R.id.rvContact)


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        rvContact.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvContact.adapter = adapterContact

    }

    @SuppressLint("Range", "Recycle")
    private fun fetchContacts(): ArrayList<ContactModel> {

        val cr = requireActivity().contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        val listContacts: ArrayList<ContactModel> = ArrayList()

        if (cursor != null && cursor.count > 0) {
            while (cursor != null && cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hashPhoneNumber =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (hashPhoneNumber > 0.toString()) {
                    val pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        ""
                    )

                    if (pCur != null && pCur.count > 0) {
                        while (pCur != null && pCur.moveToNext()) {

                            val phoneNum =
                                pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            listContacts.add(ContactModel(name,phoneNum))
                        }
                        pCur.close()
                    }
                }
            }

            if(cursor != null){
                cursor.close()
            }
        }

        return listContacts
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()

    }
}