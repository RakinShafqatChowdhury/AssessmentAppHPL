package com.example.assessmentapphpl.viewmodel;

import android.app.Application;

import com.example.assessmentapphpl.helper.Resource;
import com.example.assessmentapphpl.model.TaskDataResponseModel;
import com.example.assessmentapphpl.model.TaskEntryModel;
import com.example.assessmentapphpl.model.UnlinkRegistryModel;
import com.example.assessmentapphpl.model.UserRegistrationModel;
import com.example.assessmentapphpl.repository.AssessmentRepository;
import com.google.gson.JsonObject;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AssessmentViewModel extends AndroidViewModel {

    private final AssessmentRepository assessmentRepository;
    private MutableLiveData<Resource<List<TaskDataResponseModel>>> taskDataLiveDataList;

    public AssessmentViewModel(Application mApplication) {
        super(mApplication);
        assessmentRepository = new AssessmentRepository(mApplication);
    }

    public LiveData<Resource<String>> submitUserRegData(UserRegistrationModel userRegistrationModel) {
        return assessmentRepository.submitUserRegistrationData(userRegistrationModel);
    }

    public LiveData<Resource<String>> unlinkUserRegData(UnlinkRegistryModel unlinkRegistryModel) {
        return assessmentRepository.unlinkUserRegistrationData(unlinkRegistryModel);
    }

    public LiveData<Resource<String>> saveTaskData(TaskEntryModel taskEntryModel) {
        return assessmentRepository.saveTaskData(taskEntryModel);
    }

    public LiveData<Resource<List<TaskDataResponseModel>>> fetchTaskData(JsonObject phoneNumberObject) {
        if (taskDataLiveDataList == null)
            taskDataLiveDataList = assessmentRepository.fetchTaskData(phoneNumberObject);

        return taskDataLiveDataList;
    }

}
