package com.hardwoker;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.hardwoker.LoginActivity.fromHtml;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIntanceState) {
        final EditText txtUsername, txtPassword, txtRepassword;
        final Button btnSignup;
        final DBhelper dbHelper;

        super.onCreate(savedIntanceState);
        setContentView(R.layout.signup);

        dbHelper = new DBhelper(this);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRepassword = (EditText) findViewById(R.id.txtRepasswoerd);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        TextView tvLogin = (TextView)findViewById(R.id.tvSignup);

        tvLogin.setText(fromHtml("Back to" + "</font> <font color='#f000000'>Login</font>" ));

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String Repassword = txtRepassword.getText().toString().trim();

                ContentValues values = new ContentValues();

                if (!password.equals(Repassword)){
                    Toast.makeText(SignupActivity.this, "Password salah !", Toast.LENGTH_SHORT).show();
                }else if (password.equals(Repassword)){
                    Toast.makeText(SignupActivity.this, "Username dan Password harus diisi !", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(DBhelper.row_username, username);
                    values.put(DBhelper.row_password, password);
                    dbHelper.insertData(values);
                }
            }
        });
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    };
}
