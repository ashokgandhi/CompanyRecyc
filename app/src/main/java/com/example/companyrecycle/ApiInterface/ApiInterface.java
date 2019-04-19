package com.example.companyrecycle.ApiInterface;

import com.example.companyrecycle.ApiResponse.CyclerResponse;
import com.example.companyrecycle.ApiResponse.Form_register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

   @FormUrlEncoded
   @POST("recycler.php")
    Call<CyclerResponse>view(
            @Field("view1")String tag);

           @FormUrlEncoded
            @POST("form.php")
       Call<Form_register>form(
            @Field("form1")String tag,
            @Field("name")String name,
            @Field("phone_number")String phone_number,
            @Field("email_address")String email_address,
            @Field("college_name")String college_name,
            @Field("applied_for")String applied_for,
            @Field("qualification")String qualification



   );
}
