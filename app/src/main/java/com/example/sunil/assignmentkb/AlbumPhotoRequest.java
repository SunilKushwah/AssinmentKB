package com.example.sunil.assignmentkb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AlbumPhotoRequest {
    String BASE_URL = "https://jsonplaceholder.typicode.com/photos";

    @GET("?albumId={id}")
    Call<List<AlbumPhoto>> getAlbumPhotos(@Path("id") int id);
}
