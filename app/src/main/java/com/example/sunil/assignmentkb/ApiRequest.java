package com.example.sunil.assignmentkb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("albums")
    Call<List<Album>> getAlbums();
}
