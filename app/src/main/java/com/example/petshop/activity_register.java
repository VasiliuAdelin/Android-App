package com.example.petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.widget.Toast.LENGTH_LONG;

public class activity_register extends AppCompatActivity {
    private static final String REGISTER_FILE_NAME = "register_data_file.txt";

    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonRegister = (Button) findViewById(R.id.registerButton);


    }

    public void getRegisterData(){
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText usernameRegister = findViewById(R.id.username);
                EditText passwordRegister = findViewById(R.id.password);
                EditText passwordRegisterRetype = findViewById(R.id.passwordRetype);
                EditText emailRegister = findViewById(R.id.email);
                Log.d("rpass", passwordRegisterRetype.getText().toString());
                Log.d("password", passwordRegister.getText().toString());
                if(verifyPasswordMaching(passwordRegister,passwordRegisterRetype)==1)
                {
                    saveUserInformationOnDisk(usernameRegister.getText().toString(),passwordRegister.getText().toString(),emailRegister.getText().toString());
                }
            }
        });
    }

    public void alertPasswordsDontMach(){
        AlertDialog alertDialog = new AlertDialog.Builder(activity_register.this).create();
        alertDialog.setTitle("Wurf hav a problem.");
        alertDialog.setMessage("Passwords don't mach!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Looks i am not such a good boi after all.",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void openDialog(View view) {
        getRegisterData();
    }


        public int verifyPasswordMaching(EditText pass, EditText rePass){
        if(pass.getText().toString().equals(rePass.getText().toString())){
            return 1;
        }
        else{
            alertPasswordsDontMach();
            return 0;
        }
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
            case R.id.login:
                openLoginPage();
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

    public void openLoginPage() {
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
    }

    public void saveUserInformationOnDisk(String name,String password, String email){
        String userDataString= "";
        userDataString += name;
        userDataString += "-";
        userDataString += password;
        userDataString += "-";
        userDataString += email;
        userDataString += ";";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(REGISTER_FILE_NAME, MODE_PRIVATE);
            fos.write(userDataString.getBytes());

            Toast.makeText(this , "Saved to" + getFilesDir() + "/" + REGISTER_FILE_NAME, LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
