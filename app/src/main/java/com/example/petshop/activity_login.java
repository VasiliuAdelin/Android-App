package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class activity_login extends AppCompatActivity {
    private Button butonToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        butonToRegister = (Button) findViewById(R.id.goToRegisterButton);

        butonToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                openHomePage();
                return true;
            case R.id.settings:
                openSettingsPage();
                return true;
            case R.id.register:
                openRegisterPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSettingsPage() {
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
    }

    public void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openRegisterPage() {
        Intent intent = new Intent(this, activity_register.class);
        startActivity(intent);
    }
}
