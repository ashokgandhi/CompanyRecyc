package com.example.companyrecycle;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.companyrecycle.ApiInterface.ApiInterface;
import com.example.companyrecycle.ApiManager.ApiClient;
import com.example.companyrecycle.ApiResponse.Form_register;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Information_Form extends AppCompatActivity implements Validator.ValidationListener {

    /** ButterKnife Code **/

    @BindView(R.id.Input_Name)
    android.support.design.widget.TextInputLayout InputName;
    @NotEmpty
    @BindView(R.id.Name)
    EditText Name;

    @BindView(R.id.Input_Number)
    android.support.design.widget.TextInputLayout InputNumber;

    @Length(max = 10 , min = 10,message = "10 numbers required")
    @BindView(R.id.Number)
    EditText Number;

    @BindView(R.id.Input_Mail)
    android.support.design.widget.TextInputLayout InputMail;

    @Email
    @BindView(R.id.Mail)
    EditText Mail;

    @BindView(R.id.Input_College)
    android.support.design.widget.TextInputLayout InputCollege;
    @NotEmpty
    @BindView(R.id.College)
    EditText College;

    @BindView(R.id.Input_Applied)
    android.support.design.widget.TextInputLayout InputApplied;
    @NotEmpty
    @BindView(R.id.Applied)
    EditText Applied;
    @NotEmpty
    @BindView(R.id.Qualification)
    EditText Qualification;
    @BindView(R.id.register)
    Button register;
    /** ButterKnife Code **/

    Validator validator;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_form);

        ButterKnife.bind(this);
        validator =new Validator(this);
        validator.setValidationListener(this);

    }


        @OnClick(R.id.register)
            void register(){
                   validator.validate();
        }

    @Override
    public void onValidationSucceeded() {

        applied();

    }

    private void applied() {
        apiInterface=ApiClient.createService(ApiInterface.class,"");
        Call<Form_register>registerCall =apiInterface.form("form1",Name.getText().toString(),
                Number.getText().toString(),
                Mail.getText().toString(),
                College.getText().toString(),
                Applied.getText().toString(),
                Qualification.getText().toString());

        registerCall.enqueue(new Callback<Form_register>() {
            @Override
            public void onResponse(Call<Form_register> call, Response<Form_register> response) {

                if (response.body().getStatus()==1){
                    Toast.makeText(Information_Form.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    final Dialog dialog = new Dialog( Information_Form.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                    dialog.setContentView(R.layout.customdialogbox);
                    dialog.setCancelable(true);

                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

                    lp.copyFrom(dialog.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                    ((AppCompatButton) dialog.findViewById(R.id.Submit)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),  "Applied Successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Intent intent=new Intent(Information_Form.this,MainActivity.class);
                            startActivity(intent);

                        }
                    });

                    dialog.show();
                    dialog.getWindow().setAttributes(lp);

                }else
                {
                    Toast.makeText(Information_Form.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Form_register> call, Throwable t) {
                Log.e("## Message", t.getMessage().toString());
                Toast.makeText(Information_Form.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError error:errors){
            View view =error.getView();
            String message =error.getCollatedErrorMessage(this);


            if (view instanceof EditText){
                ((EditText)view).setError(message);
            }else{
                Toast.makeText(this, "please fill this field", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
