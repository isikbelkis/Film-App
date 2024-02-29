package com.example.FilmApp.Aktiviteler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trend_cati.R;

public class LoginActivity extends AppCompatActivity {
private EditText isimEdit,sifreEdit;
private Button girisBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         
        initView();
        
    }

    private void initView() {
        isimEdit=findViewById(R.id.editTextText);
        sifreEdit=findViewById(R.id.editTextPassword);
        girisBtn=findViewById(R.id.girisBtn);

        girisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //burada şifrenin doğruluğunu kontrol ediyoruz şu an şifre ve kullanıcı adı test eğer şifre doğruysa login maine geçiyoruz
                if (isimEdit.getText().toString().isEmpty() || sifreEdit.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this,"Lütfen kullanıcı adınızı ve şifrenizi girin",Toast.LENGTH_SHORT).show();
                }else if ((isimEdit.getText().toString().equals("test") && sifreEdit.getText().toString().equals("test"))){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this,"Kullanıcı adın ve şifren doğru değil",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}