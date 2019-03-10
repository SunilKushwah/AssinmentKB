package com.example.sunil.assignmentkb

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaceholderFragment : Fragment() {
    private lateinit var simpleGrid: GridView
    private var data:ArrayList<AlbumPhoto> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        simpleGrid = rootView.findViewById(R.id.simpleGridView) as GridView
        val customAdapter = CustomAdapter(activity!!, data)
        simpleGrid.adapter = customAdapter
        getAlbumPhotos(arguments!!.getInt(ARG_SECTION_NUMBER))
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
    private fun getAlbumPhotos(id:Int) {

        val retrofit = Retrofit.Builder()
                .baseUrl(AlbumPhotoRequest.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(AlbumPhotoRequest::class.java)

        val call = api.getAlbumPhotos(id)

        call.enqueue(object : Callback<List<AlbumPhoto>> {
            override fun onResponse(call: Call<List<AlbumPhoto>>, response: Response<List<AlbumPhoto>>) {
                Log.d("Response",response.toString())
                val albumPhotos = response.body()
                var data = ArrayList<AlbumPhoto>()
                for (i in albumPhotos!!.indices) {
                    data.add(albumPhotos[i])
                }

            }

            override fun onFailure(call: Call<List<AlbumPhoto>>, t: Throwable) {
                Toast.makeText(activity!!, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })


    }
}