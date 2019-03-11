package com.example.sunil.assignmentkb

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.support.v4.os.HandlerCompat.postDelayed



class PlaceholderFragment : Fragment() {
    private lateinit var simpleGrid: GridView
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var customAdapter: CustomAdapter
    private var data:ArrayList<AlbumPhoto> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        initViews(rootView)
        customAdapter = CustomAdapter(activity!!, data)
        simpleGrid.adapter = customAdapter
        checkIfGridEmpty()
        getAlbumPhotos(arguments!!.getInt(ALBUM_ID))
        return rootView
    }

    private fun initViews(rootView: View) {
        simpleGrid = rootView.findViewById(R.id.simpleGridView) as GridView
        loadingProgressBar = rootView.findViewById(R.id.loading_pb) as ProgressBar
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ALBUM_ID = "album_id"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(albumId: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putInt(ALBUM_ID, albumId)
            fragment.arguments = args
            return fragment
        }
    }
    private fun getAlbumPhotos(id:Int) {
        val retrofit = Retrofit.Builder()
                .baseUrl(ApiRequest.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(ApiRequest::class.java)
        val call = api.getAlbumPhotos(id)
        call.enqueue(object : Callback<List<AlbumPhoto>> {
            override fun onResponse(call: Call<List<AlbumPhoto>>, response: Response<List<AlbumPhoto>>) {
                Log.d("Response",response.toString())
                parseResponse(response)
            }

            override fun onFailure(call: Call<List<AlbumPhoto>>, t: Throwable) {
                Toast.makeText(activity!!, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })


    }

    private fun checkIfGridEmpty() {
        if (data.isEmpty()) {
            simpleGrid.visibility = View.GONE
            loadingProgressBar.visibility = View.VISIBLE
        } else {
            simpleGrid.visibility = View.VISIBLE
            loadingProgressBar.visibility = View.GONE
        }
    }

    private fun parseResponse(response: Response<List<AlbumPhoto>>) {
        val albumPhotos = response.body()
        data = ArrayList()
        for (i in albumPhotos!!.indices) {
            data.add(albumPhotos[i])
        }
        customAdapter.setData(data)
        val handler = Handler()
        handler.postDelayed({
            checkIfGridEmpty()
        }, 2000)

    }
}