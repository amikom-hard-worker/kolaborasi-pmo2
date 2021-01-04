package com.hardwoker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.text.Html.fromHtml;

public class LoginActivity extends AppCompatActivity {
   @Override
   protected void onCreate (Bundle savedInstanceState) {

       final EditText txtUsername,txtPassword;
       Button btnLogin;
       final DBhelper dbHelper;

       super.onCreate(savedInstanceState);
       setContentView(R.layout.login);

       txtUsername = (EditText) findViewById(R.id.txtUsername);
       txtPassword = (EditText) findViewById(R.id.txtPassword);
       btnLogin = (Button) findViewById(R.id.btnLogin);

       dbHelper = new DBhelper(this);

       TextView tvSignup = (TextView) findViewById(R.id.tvSignup);

       tvSignup.setText(fromHtml("Don't have account" + "</font> <font color='#f000000'> create one </form"));

       tvSignup.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
               startActivity(new Intent(LoginActivity.this, SignupActivity.class));
           }
       });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String username = txtUsername.getText().toString().trim();
               String password = txtPassword.getText().toString().trim();

               Boolean res = dbHelper.checkUser(username,password);
               if(res == true) {
                   Toast.makeText(LoginActivity.this, "Login berhasil !", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(LoginActivity.this, MainActivity.class));
               } else {
                   Toast.makeText(LoginActivity.this, "Login gagal !", Toast.LENGTH_SHORT).show();
               }
           }
       });
   }

   public static Spanned fromHtml(String html){
       Spanned result;
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
           result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
       } else {
           result = Html.fromHtml(html);
       }
       return result;
   }
}
