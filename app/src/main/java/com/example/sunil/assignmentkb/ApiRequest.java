package com.example.sunil.assignmentkb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    String BASE_URL = "https://jsonplaceholder.typicode.com";
    @GET("/albums")
    Call<List<Album>> getAlbums();

    @GET("/photos")
    Call<List<AlbumPhoto>> getAlbumPhotos(@Query("albumId") int id);
}
