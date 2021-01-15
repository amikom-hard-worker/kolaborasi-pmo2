package com.hardwoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AllCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
    }

    public void pindah(View view) {
        Intent intent = new Intent(AllCategory.this, MainActivity.class);
        startActivity(intent);
    }

    public void map(View view) {
        Intent intent = new Intent(AllCategory.this, MapsActivity.class);
        startActivity(intent);
    }
}