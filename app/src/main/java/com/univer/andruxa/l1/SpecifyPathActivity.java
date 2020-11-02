package com.univer.andruxa.l1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.univer.andruxa.l1.R;

public class SpecifyPathActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText townEditText;
    private EditText address1EditText;
    private EditText address2EditText;
    private Button submitPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specify_path);
        initViewComponents();
    }

    private void initViewComponents() {
        townEditText = findViewById(R.id.townEditText);
        address1EditText = findViewById(R.id.address1EditText);
        address2EditText = findViewById(R.id.address2EditText);
        submitPath = findViewById(R.id.submitPath);
        submitPath.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitPath:
                Intent backToCallTaxiIntent = new Intent();
                backToCallTaxiIntent.putExtra("Town", townEditText.getText().toString());
                backToCallTaxiIntent.putExtra("Address1", address1EditText.getText().toString());
                backToCallTaxiIntent.putExtra("Address2", address2EditText.getText().toString());
                setResult(RESULT_OK, backToCallTaxiIntent);
                finish();
        }
    }

}
