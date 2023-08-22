package com.example.assessmentapphpl.viewmodel;

import android.app.Application;

import com.example.assessmentapphpl.helper.Resource;
import com.example.assessmentapphpl.model.UnlinkRegistryModel;
import com.example.assessmentapphpl.model.UserRegistrationModel;
import com.example.assessmentapphpl.repository.AssessmentRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AssessmentViewModel extends AndroidViewModel {

    private final AssessmentRepository assessmentRepository;
    private final MutableLiveData<String> ekycComplete = new MutableLiveData<>();
    private final MutableLiveData<Integer> result = new MutableLiveData<>();
    private final MutableLiveData<Integer> closeFragment = new MutableLiveData<>();

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

}
