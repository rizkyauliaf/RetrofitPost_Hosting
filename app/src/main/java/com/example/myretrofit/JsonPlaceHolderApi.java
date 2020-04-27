package com.example.myretrofit;

import android.widget.EditText;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("siswa")
    //penambahan parameter ini berfungsi untuk pemanggilan data sesuai dengan id siswa
    Call<List<Post>> getPost();

    //berfungsi sebagai pengiriman data siswa dg menggunakan method post
    @GET("mahasiswa")
    Call<List<Post>> getPost(@Query("id_siswa") String id_siswa);

    //disini trdpt post siswa yg digunakan untuk pengiriman data
    @FormUrlEncoded
    @POST("siswa")
    Call<ResponseBody> setPost(
            @Field("id_siswa") String id_siswa,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("no_telp") String no_telp
    );
}

//class JsonPlaceholderapi digunakan utk komunikasi data dg rest server
// @GET("siswa") disini berarti kita mengambil api dr data server restserver api siswa













