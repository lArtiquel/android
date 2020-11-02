package com.univer.andruxa.l1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.univer.andruxa.l1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText phoneEdit;
    private EditText nameEdit;
    private EditText surnameEdit;
    private Button submitPersonalInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewComponents();
    }

    private void initViewComponents() {
        phoneEdit = findViewById(R.id.phoneEdit);
        nameEdit = findViewById(R.id.nameEdit);
        surnameEdit = findViewById(R.id.surnameEdit);
        submitPersonalInfoButton = findViewById(R.id.submitPersonalInfo);
        submitPersonalInfoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitPersonalInfo:
                Intent callTaxiIntent = new Intent(this, CallTaxiActivity.class);
                callTaxiIntent.putExtra("Phone", phoneEdit.getText().toString());
                callTaxiIntent.putExtra("Name", nameEdit.getText().toString());
                callTaxiIntent.putExtra("Surname", surnameEdit.getText().toString());
                startActivity(callTaxiIntent);
                break;
        }
    }

}