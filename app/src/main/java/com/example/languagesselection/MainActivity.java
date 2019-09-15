package com.example.languagesselection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView languageText;

    public void showAlert() {
        languageText = findViewById(R.id.languageText);
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.languagesselection", Context.MODE_PRIVATE);
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Which Language Do You Want?")
                .setMessage("Do You Want Spanish or English?")
                .setPositiveButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().putString("language", "Spanish").apply();
                        languageText.setText("Spanish");
                    }
                })
                .setNegativeButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPreferences.edit().putString("language", "English").apply();
                        languageText.setText("English");
                    }
                }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        languageText = findViewById(R.id.languageText);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.languagesselection", Context.MODE_PRIVATE);
        String choosenLanguage = sharedPreferences.getString("languages", "");

        if (choosenLanguage == "") {
            showAlert();
        } else {
            languageText.setText(choosenLanguage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.language) {
            showAlert();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
