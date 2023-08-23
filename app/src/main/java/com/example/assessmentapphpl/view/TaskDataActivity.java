package com.example.assessmentapphpl.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.adapter.TaskDataItemAdapter;
import com.example.assessmentapphpl.databinding.ActivityTaskDataBinding;
import com.example.assessmentapphpl.helper.NetworkUtils;
import com.example.assessmentapphpl.helper.SharedPref;
import com.example.assessmentapphpl.helper.Utils;
import com.example.assessmentapphpl.model.TaskDataResponseModel;
import com.example.assessmentapphpl.viewmodel.AssessmentViewModel;
import com.google.gson.JsonObject;

import java.util.List;

public class TaskDataActivity extends AppCompatActivity {

    private ActivityTaskDataBinding binding;
    private AssessmentViewModel assessmentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_data);

        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        fetchAllTaskData(SharedPref.getInstance(this).getUserInfo().getUserPhoneNumber());
    }

    private void fetchAllTaskData(String userPhoneNumber) {
        if (!NetworkUtils.isConnected(this)) {
            Utils.toast(this, getString(R.string.network_error_msg));
            return;
        }

        JsonObject phoneNumberObject = new JsonObject();
        phoneNumberObject.addProperty("contact_number", userPhoneNumber);

        assessmentViewModel
                .fetchTaskData(phoneNumberObject)
                .observe(this, taskDataResponseModelResource -> {
                    switch (taskDataResponseModelResource.status) {
                        case SUCCESS:
                            if (taskDataResponseModelResource.data != null)
                                setTaskDataRecyclerView(taskDataResponseModelResource.data);
                            else
                                Utils.toast(this, taskDataResponseModelResource.message);
                            break;
                        case LOADING:
                            break;
                        case ERROR:
                            Utils.toast(this, taskDataResponseModelResource.message);
                            break;
                    }
                });
    }

    private void setTaskDataRecyclerView(List<TaskDataResponseModel> data) {
        TaskDataItemAdapter adapter = new TaskDataItemAdapter(data);
        binding.taskDataRV.setHasFixedSize(true);
        adapter.setHasStableIds(true);
        binding.taskDataRV.setAdapter(adapter);
    }
}