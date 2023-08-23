package com.example.assessmentapphpl.repository;

import android.app.Application;

import com.example.assessmentapphpl.helper.Constants;
import com.example.assessmentapphpl.helper.Resource;
import com.example.assessmentapphpl.model.TaskDataResponseModel;
import com.example.assessmentapphpl.model.TaskEntryModel;
import com.example.assessmentapphpl.model.UnlinkRegistryModel;
import com.example.assessmentapphpl.model.UserRegistrationModel;
import com.example.assessmentapphpl.network.ApiInterface;
import com.example.assessmentapphpl.network.RetrofitClient;
import com.google.gson.JsonObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssessmentRepository {
    private final ApiInterface apiInterface;

    public AssessmentRepository(Application mApplication) {
        apiInterface = new RetrofitClient().callRetrofit(Constants.BASE_URL);
    }

    public MutableLiveData<Resource<String>> submitUserRegistrationData(UserRegistrationModel userRegistrationModel) {

        final MutableLiveData<Resource<String>> registrationResponseLiveData = new MutableLiveData<>();
        apiInterface
                .registration(userRegistrationModel)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        if (response.isSuccessful()) {
                            registrationResponseLiveData.postValue(Resource.success(response.body()));
                        } else if (response.code() == 400) {
                            registrationResponseLiveData.postValue(Resource.error("Exists", null));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        registrationResponseLiveData.postValue(Resource.error(t.getMessage(), null));
                    }
                });

        return registrationResponseLiveData;
    }

    public MutableLiveData<Resource<String>> unlinkUserRegistrationData(UnlinkRegistryModel unlinkRegistryModel) {

        final MutableLiveData<Resource<String>> unlinkRegistrationResponseLiveData = new MutableLiveData<>();
        apiInterface
                .unlinkRegistry(unlinkRegistryModel)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        if (response.isSuccessful()) {
                            unlinkRegistrationResponseLiveData.postValue(Resource.success(response.body()));
                        } else if (response.code() == 400) {
                            unlinkRegistrationResponseLiveData.postValue(Resource.error("Unlink error", response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        unlinkRegistrationResponseLiveData.postValue(Resource.error(t.getMessage(), null));
                    }
                });

        return unlinkRegistrationResponseLiveData;
    }

    public MutableLiveData<Resource<String>> saveTaskData(TaskEntryModel taskEntryModel) {

        final MutableLiveData<Resource<String>> saveTaskResponseLiveData = new MutableLiveData<>();
        apiInterface
                .saveTaskData(taskEntryModel)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        if (response.isSuccessful())
                            saveTaskResponseLiveData.postValue(Resource.success(response.body()));
                        else
                            saveTaskResponseLiveData.postValue(Resource.error(response.body(), null));

                    }
                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        saveTaskResponseLiveData.postValue(Resource.error(t.getMessage(), null));
                    }
                });

        return saveTaskResponseLiveData;
    }

    public MutableLiveData<Resource<List<TaskDataResponseModel>>> fetchTaskData(JsonObject phoneNumberObject) {

        final MutableLiveData<Resource<List<TaskDataResponseModel>>> fetchedTaskDataResponseLiveData = new MutableLiveData<>();
        apiInterface
                .fetchTaskData(phoneNumberObject)
                .enqueue(new Callback<List<TaskDataResponseModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<TaskDataResponseModel>> call, @NonNull Response<List<TaskDataResponseModel>> response) {
                        if (response.isSuccessful())
                            fetchedTaskDataResponseLiveData.postValue(Resource.success(response.body()));
                        else
                            fetchedTaskDataResponseLiveData.postValue(Resource.error(response.message(), null));
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<TaskDataResponseModel>> call, @NonNull Throwable t) {
                        fetchedTaskDataResponseLiveData.postValue(Resource.error(t.getMessage(), null));
                    }
                });

        return fetchedTaskDataResponseLiveData;
    }

}
