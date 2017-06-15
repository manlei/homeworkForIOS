package dsca.cs.nju.mytabmenudemo.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.model.User;
import dsca.cs.nju.mytabmenudemo.service.ApiClient;
import dsca.cs.nju.mytabmenudemo.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_email)
    EditText emailText;

    @BindView(R.id.btn_getVertificationCode)
    Button getVertificationCode;

    @BindView(R.id.input_vertificationCode)
    EditText vertificationCode;

    @BindView(R.id.input_password)
    EditText passwordText;

    @BindView(R.id.input_reEnterPassword)
    EditText reEnterPasswordText;

    @BindView(R.id.btn_signup)
    Button signupButton;

    @BindView(R.id.link_login)
    TextView loginLink;

    //vertification
    private int vertificationCodeNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        ButterKnife.bind(this);

        //show the random vertificationCodeNum
        getVertificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vertificationCodeNum = 0;
                for (int i = 0; i < 6; i++) {
                    vertificationCodeNum = vertificationCodeNum * 10 + (int)(Math.random()*10)%10;
                }
                Toast.makeText(SignupActivity.this,vertificationCodeNum+"",Toast.LENGTH_SHORT).show();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                //Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }

    public void signup() {

        if (!validate()) {
            Toast.makeText(SignupActivity.this,"输入无效",Toast.LENGTH_SHORT).show();
            onSignupFailed();
            return;
        }

        //因为需要等待，所以设置button无效
        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String account = emailText.getText().toString();
        String password = passwordText.getText().toString();

        User user = new User();
        user.setLogin(false);
        user.setName("manley");
        user.setAccount(account);
        user.setPassword(password);

        UserService userService = ApiClient.getUserService();
        userService.signIn(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                switch (response.code()) {
                    case 200: {
                        Toast.makeText(SignupActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        onSignupSuccess();
                    }
                    break;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignupActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                onSignupFailed();
            }
        });
    }

    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivity(intent);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "注册失败", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();

        int inputVertificationCodeNum;
        try {
            inputVertificationCodeNum = Integer.parseInt(this.vertificationCode.getText().toString());
        }
        catch (Exception e) {
            return false;
        }

        String password = passwordText.getText().toString();
        String reEnterPassword = reEnterPasswordText.getText().toString();

        if (email.isEmpty() && (android.util.Patterns.PHONE.matcher(email).matches() || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            emailText.setError("enter a valid email/phone address");
            Toast.makeText(getBaseContext(), "账户", Toast.LENGTH_LONG).show();
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (inputVertificationCodeNum != vertificationCodeNum) {
            vertificationCode.setError("验证码错误");
            Toast.makeText(getBaseContext(), "验证码", Toast.LENGTH_LONG).show();
            valid = false;
        }
        else {
            vertificationCode.setError(null);
        }

        if (password.isEmpty() || password.length() < 3 || password.length() > 10) {
            Toast.makeText(getBaseContext(), "密码", Toast.LENGTH_LONG).show();
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 3 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            Toast.makeText(getBaseContext(), "验证密码", Toast.LENGTH_LONG).show();
            reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            reEnterPasswordText.setError(null);
        }

        return valid;
    }
}