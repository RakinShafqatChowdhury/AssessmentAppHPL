package com.example.assessmentapphpl.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.assessmentapphpl.R;
import com.example.assessmentapphpl.databinding.ActivityRegistrationBinding;
import com.example.assessmentapphpl.helper.Constants;
import com.example.assessmentapphpl.helper.ProgressDialogUtil;
import com.example.assessmentapphpl.helper.Utils;
import com.example.assessmentapphpl.helper.ValidationUtil;
import com.example.assessmentapphpl.model.UnlinkRegistryModel;
import com.example.assessmentapphpl.model.UserRegistrationModel;
import com.example.assessmentapphpl.viewmodel.AssessmentViewModel;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    String userPhoneNumber = "";

    private AssessmentViewModel assessmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);

        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        if (getIntent().getExtras() != null) {
            userPhoneNumber = getIntent().getStringExtra(Constants.PASS_USER_PHONE_NUMBER);
            binding.contactNumberET.setText(userPhoneNumber);
            binding.contactNumberET.setClickable(false);
            binding.contactNumberET.setEnabled(false);
        }

        binding.submitBtn.setOnClickListener(view -> validateFields());
    }

    private void validateFields() {
        if (!ValidationUtil.isValidField(binding.userNameET)) {
            Utils.alert(this, "Warning!", "Please enter valid name");
            return;
        }

        if (!ValidationUtil.isValidEmail(binding.userEmailET.getText().toString().trim())) {
            Utils.alert(this, "Warning!", "Please enter valid email");
            return;
        }

        submitRegistrationData();
    }

    private void submitRegistrationData() {
        ProgressDialogUtil.showProgressDialog(this, "Submitting info...");
        UserRegistrationModel userRegistrationModel = new UserRegistrationModel();

        userRegistrationModel.setUserName(binding.userNameET.getText().toString().trim());
        userRegistrationModel.setUserEmail(binding.userEmailET.getText().toString().trim());
        userRegistrationModel.setUserPhoneNumber(binding.contactNumberET.getText().toString().trim());

        assessmentViewModel
                .submitUserRegData(userRegistrationModel)
                .observe(this, stringResource -> {
                    ProgressDialogUtil.dismissProgressDialog();
                    switch (stringResource.status) {
                        case SUCCESS:
                            if (stringResource.data != null)
                                showSuccessAlert("Congratulations", stringResource.data);
                            else
                                Utils.toast(this, stringResource.message);
                            break;
                        case LOADING:
                            break;
                        case ERROR:
                            if (stringResource.message != null && stringResource.message.equals("Exists")) {
                                openUnlinkRegistryDialog();
                                break;
                            }
                            Utils.toast(this, stringResource.message);
                            break;
                    }
                });
    }

    private void showSuccessAlert(String title, String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(data)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    gotoHomePage();
                })
                .setCancelable(false)
                .show();
    }

    private void gotoHomePage() {

    }

    private void openUnlinkRegistryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage("Data already exists!\nDo you want to unlink registry associated with " + userPhoneNumber + "?")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    unlinkRegistration(userPhoneNumber);
                })
                .setNegativeButton("CANCEL", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setCancelable(false)
                .show();
    }

    private void unlinkRegistration(String userPhoneNumber) {
        ProgressDialogUtil.showProgressDialog(this, "Please wait...");
        UnlinkRegistryModel unlinkRegistryModel = new UnlinkRegistryModel();
        unlinkRegistryModel.setUserPhoneNumber(userPhoneNumber);

        assessmentViewModel
                .unlinkUserRegData(unlinkRegistryModel)
                .observe(this, stringResource -> {
                    ProgressDialogUtil.dismissProgressDialog();
                    switch (stringResource.status) {
                        case SUCCESS:
                            if (stringResource.data != null)
                                showSuccessAlert("Unlinked", stringResource.data);
                            else
                                Utils.toast(this, stringResource.message);
                            break;
                        case LOADING:
                            break;
                        case ERROR:
                            if (stringResource.message != null && stringResource.message.equals("Unlink error")) {
                                showSuccessAlert("Failed", stringResource.data);
                                break;
                            }
                            Utils.toast(this, stringResource.message);
                            break;
                    }
                });
    }
}