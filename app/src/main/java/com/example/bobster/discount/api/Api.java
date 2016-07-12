package com.example.bobster.discount.api;

import com.example.bobster.discount.model.Item;
import com.example.bobster.discount.model.Response;
import com.example.bobster.discount.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by bobster on 1/14/2016.
 */
public interface Api {

   /* @Headers({
            "Authorization: Basic Z3NvODQxMkBnbWFpbC5jb206cm9zYWxlcw==",
            "Content-Type: application/json"
    })
    @GET("/items")
    void getItem(Callback<List<Item>> callback);


    @Headers({
            "Authorization: Basic Z3NvODQxMkBnbWFpbC5jb206cm9zYWxlcw==",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("username") String username, @Field("password") String password, Callback<User> callback);

    @Headers({
            "Authorization: Basic Z3NvODQxMkBnbWFpbC5jb206cm9zYWxlcw==",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/items/add")
    void addItem(@Field("name") String name, @Field("reference") String reference, Callback<Response> callback);

    @Headers({
            "Authorization: Basic Z3NvODQxMkBnbWFpbC5jb206cm9zYWxlcw==",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/items/delete")
    void deleteItem(@Field("id") String id, Callback<Response> callback);*/

    @Headers({
            "Authorization: Basic Z3NvODQxMkBnbWFpbC5jb206ZGlzY291bnQ=",
            "Content-Type: application/json"
    })
    @GET("/items")
    void getItem(Callback<List<Item>> callback);


    @Headers({
            "Authorization: Basic Z3NvODQxMkBnbWFpbC5jb206ZGlzY291bnQ=",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("username") String username, @Field("password") String password, Callback<User> callback);

    @Headers({
            "Authorization: Basic Basic Z3NvODQxMkBnbWFpbC5jb206ZGlzY291bnQ=",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/items/add")
    void addItem(@Field("name") String name, @Field("reference") String reference, Callback<Response> callback);

    @Headers({
            "Authorization: Basic Basic Z3NvODQxMkBnbWFpbC5jb206ZGlzY291bnQ=",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/items/delete")
    void deleteItem(@Field("id") String id, Callback<Response> callback);

}
