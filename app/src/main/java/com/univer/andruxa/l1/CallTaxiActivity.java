package com.univer.andruxa.l1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CallTaxiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView personalInfoTextView;
    private TextView pathTextView;
    private Button setPathButton;
    private Button callTaxiButton;
    private AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_taxi);
        initViewComponents();
        setValues();
    }

    private void initViewComponents() {
        personalInfoTextView = findViewById(R.id.personalInfoTextView);
        pathTextView = findViewById(R.id.pathTextView);
        setPathButton = findViewById(R.id.setPathButton);
        callTaxiButton = findViewById(R.id.callTaxiButton);
        dialogBuilder = new AlertDialog.Builder(this);
    }

    private void setValues() {
        setPathButton.setOnClickListener(this);
        callTaxiButton.setEnabled(false);
        callTaxiButton.setOnClickListener(this);
        Intent intent = getIntent();
        String phone = intent.getStringExtra("Phone");
        String name = intent.getStringExtra("Name");
        String surname = intent.getStringExtra("Surname");
        personalInfoTextView.setText(
                "Phone: " + phone + "\n" +
                        "Name: " + name + "\n" +
                        "Surname: " + surname + "\n"
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setPathButton:
                Intent setPathIntent = new Intent("android.intent.action.SpecifyPathActivity");
                startActivityForResult(setPathIntent, 1);
                callTaxiButton.setEnabled(true);
                break;
            case R.id.callTaxiButton:
                AlertDialog alertDialog = buildSuccessfulTaxiOrderDialog();
                alertDialog.show();
                break;
        }
    }

    private AlertDialog buildSuccessfulTaxiOrderDialog() {
        dialogBuilder
                .setTitle(R.string.dialog__successful_taxi_order__title)
                .setMessage(R.string.dialog__successful_taxi_order__message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Toast.makeText(getApplicationContext(),
                                R.string.toast__successful_taxi_order__message,
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),
                                R.string.toast__decline_taxi_order__message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog = dialogBuilder.create();
        return alertDialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (data == null) {
            return;
        }
        String town = data.getStringExtra("Town");
        String address1 = data.getStringExtra("Address1");
        String address2 = data.getStringExtra("Address2");
        pathTextView.setText("Your destination:" + "\n" +
                                town + "\n" +
                                address1 + "\n" +
                                address2 + "\n"
        );
    }

}
