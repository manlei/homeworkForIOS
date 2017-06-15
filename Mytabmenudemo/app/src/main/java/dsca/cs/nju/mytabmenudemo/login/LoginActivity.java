//package cycletest.example.com.materialtest.login;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class LoginActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//    }
//}
//

package dsca.cs.nju.mytabmenudemo.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dsca.cs.nju.mytabmenudemo.R;
import dsca.cs.nju.mytabmenudemo.activity.MainActivity;
import dsca.cs.nju.mytabmenudemo.model.User;
import dsca.cs.nju.mytabmenudemo.service.ApiClient;
import dsca.cs.nju.mytabmenudemo.service.UserService;
import dsca.cs.nju.mytabmenudemo.util.AppContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText emailText;

    @BindView(R.id.input_password)
    EditText passwordText;

    @BindView(R.id.btn_login)
    Button loginButton;

    @BindView(R.id.link_signup)
    TextView signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //启动页面
                Intent intent = new Intent(getBaseContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        User user = new User();
        user.setName("manley");
        user.setAccount(email);
        user.setPassword(password);
        user.setLogin(true);

        UserService userService = ApiClient.getUserService();
        userService.signIn(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                switch (response.code()) {
                    case 200: {
                        AppContext.signIn(response.body());
                        Toast.makeText(LoginActivity.this, "登录成功"+response.body().getAccount(), Toast.LENGTH_SHORT).show();
                        onLoginSuccess();
                    }
                    break;
                    case 400: {
                        loginButton.setEnabled(true);
                        Toast.makeText(LoginActivity.this,"登录失败，400",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                onLoginFailed();
                Toast.makeText(LoginActivity.this, "登录失败"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        Log.e("success","success");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //LoginActivity.this.setResult(1,intent);
        startActivity(intent);
    }

    public void onLoginFailed() {
        //Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (!email.isEmpty() && (android.util.Patterns.PHONE.matcher(email).matches() || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            emailText.setError(null);
        }
        else {
            emailText.setError("enter a valid email/phone");
            valid = false;
        }

        if (password.isEmpty() || password.length() < 3 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
