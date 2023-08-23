package com.example.assessmentapphpl.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.databinding.ActivityTaskEntryBinding;
import com.example.assessmentapphpl.helper.NetworkUtils;
import com.example.assessmentapphpl.helper.ProgressDialogUtil;
import com.example.assessmentapphpl.helper.SharedPref;
import com.example.assessmentapphpl.helper.Utils;
import com.example.assessmentapphpl.helper.ValidationUtil;
import com.example.assessmentapphpl.model.TaskEntryModel;
import com.example.assessmentapphpl.viewmodel.AssessmentViewModel;

public class TaskEntryActivity extends AppCompatActivity {

    private ActivityTaskEntryBinding binding;
    private AssessmentViewModel assessmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_entry);

        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        binding.saveBtn.setOnClickListener(view -> validateFields());
    }

    private void validateFields() {
        if (!ValidationUtil.isValidField(binding.taskTitleET)) {
            Utils.toast(this, "Title can not be empty");
            return;
        }

        if (!ValidationUtil.isValidField(binding.taskDetailsET)) {
            Utils.toast(this, "Details can not be empty");
            return;
        }

        if (!NetworkUtils.isConnected(this)) {
            Utils.toast(this, getString(R.string.network_error_msg));
            return;
        }

        saveTaskData(binding.taskTitleET.getText().toString().trim(), binding.taskDetailsET.getText().toString().trim());
    }

    private void saveTaskData(String title, String details) {
        ProgressDialogUtil.showProgressDialog(this, "Saving Task...");

        TaskEntryModel taskEntryModel = new TaskEntryModel();
        taskEntryModel.setTaskTitle(title);
        taskEntryModel.setTaskDetails(details);
        taskEntryModel.setUserPhoneNumber(SharedPref.getInstance(this).getUserInfo().getUserPhoneNumber());

        assessmentViewModel
                .saveTaskData(taskEntryModel)
                .observe(this, stringResource -> {
                    ProgressDialogUtil.dismissProgressDialog();
                    switch (stringResource.status) {
                        case SUCCESS:
                            if (stringResource.data != null) {
                                Utils.alert(this, "Saved", stringResource.data);
                                binding.taskTitleET.setText("");
                                binding.taskDetailsET.setText("");
                            }
                            break;
                        case LOADING:
                            break;
                        case ERROR:
                            Utils.toast(this, stringResource.message);
                            break;
                    }
                });
    }
}