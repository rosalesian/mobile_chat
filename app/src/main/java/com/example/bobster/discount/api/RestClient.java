package com.example.bobster.discount.api;

import com.example.bobster.discount.config.Config;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by bobster on 1/14/2016.
 */
public class RestClient
{
    private static Api REST_CLIENT;

    static
    {
        setupRestClient();
    }

    private static void setupRestClient()
    {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Config.END_POINT)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(Api.class);
    }

    private RestClient()
    {

    }

    public static Api get()
    {
        return  REST_CLIENT;
    }

}
