package com.example.assessmentapphpl.repository;

import android.app.Application;

import com.example.assessmentapphpl.helper.Constants;
import com.example.assessmentapphpl.network.ApiInterface;
import com.example.assessmentapphpl.network.RetrofitClient;

public class AssessmentRepository {
    private final ApiInterface apiInterface;

    public AssessmentRepository(Application mApplication) {
        apiInterface = new RetrofitClient().callRetrofit(Constants.BASE_URL);
    }


}
