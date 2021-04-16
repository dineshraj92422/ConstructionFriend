package com.drmarks.constructionfriend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class IndustrialSectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industrial_sector);


        @SuppressLint("WrongViewCast") CardView bill = findViewById(R.id.cdbilling);
        @SuppressLint("WrongViewCast") CardView tendor = findViewById(R.id.Tendor);
        @SuppressLint("WrongViewCast") CardView estima = findViewById(R.id.Estimation);
        @SuppressLint("WrongViewCast") CardView invent = findViewById(R.id.inventory);

           estima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndustrialSectorActivity.this,EstimationActivity.class);
                startActivity(intent);
            }
        });


        tendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndustrialSectorActivity.this,TendorActivity.class);
                startActivity(intent);
            }
        });
//        btnReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(IndustrialSectorActivity.this,ReportActivity.class);
//                startActivity(intent);
//            }
//        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndustrialSectorActivity.this,BillingActivity.class);
                startActivity(intent);
            }
        });
        invent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndustrialSectorActivity.this,InventoryActivity.class);
                startActivity(intent);
            }
        });
    }
}