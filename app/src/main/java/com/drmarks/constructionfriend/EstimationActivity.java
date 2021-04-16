package com.drmarks.constructionfriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimation);
        Button btncalc;
        Spinner sp1,sp2;
        TextView moreD1,moreD2;
        moreD1 =findViewById(R.id.txtmored);
        moreD2 =findViewById(R.id.txtmorede);
        btncalc =findViewById(R.id.btncalc);
        EditText edtarea,edtprice;
        edtarea =findViewById(R.id.edtarea);
        edtprice =findViewById(R.id.edtprice);
        TextView txtresult,txtceme,txtaggr,txtsandd,txtsteell,txtfini,txtfitt,transcolor,cementbag,sandton,aggrton,steelkg,paintlt,brickscs,flooringft;
        txtresult =findViewById(R.id.txtresult);
        txtceme = findViewById(R.id.txtceme);
        txtaggr =findViewById(R.id.txtaggr);
        txtsandd =findViewById(R.id.txtsandd);
        txtsteell =findViewById(R.id.txtsteell);
        txtfitt =findViewById(R.id.txtfitt);
        txtfini =findViewById(R.id.txtfini);
        transcolor =findViewById(R.id.transcolor);
        cementbag =findViewById(R.id.cementbag);
        sandton =findViewById(R.id.sandton);
        aggrton =findViewById(R.id.aggrton);
        steelkg =findViewById(R.id.steelkg);
        paintlt =findViewById(R.id.paintlt);
        brickscs =findViewById(R.id.brickscs);
        flooringft=findViewById(R.id.flooringft);

        final String City[]={"વડોદરા"};
        final String Area[]={"મકરપુરા"};
        sp1=findViewById(R.id.spinnner);
        sp2=findViewById(R.id.spinnner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,City);
        sp1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,Area);
        sp2.setAdapter(adapter2);

        moreD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moredetails();
            }
        });


        moreD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moredetails();
            }
        });

        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer are = Integer.valueOf(edtarea.getText().toString());
                Integer price = Integer.valueOf(edtprice.getText().toString());
                Integer total = new Integer((int) (price*are));
                Integer cement = new Integer((int)(total * (16.4/100)));
                Integer sand= new Integer((int)(total * (12.3/100)));
                Integer aggre =new Integer((int) (total * (7.4/100)));
                Integer steel  =new Integer((int) (total * (24.6/100)));
                Integer finish = new Integer((int)(total * (16.5/100)));
                Integer fitting =new Integer((int) (total * (22.8/100)));
                Integer transport  = new Integer((int)(total *(.85/100)));
                total += transport;
                int bag = (int) (are*.4);
                int dton= (int) (are*0.816);
                int rton = (int) (are*0.608);
                int kg= are*4;
                int lts= (int) (are*0.18);
                int pcs  = are*8;
                int ft= (int) (are*1.3);
                txtresult.setText(String.valueOf(total));
                txtceme.setText(String.valueOf(cement));
                txtaggr.setText(String.valueOf(aggre));
                txtsandd.setText(String.valueOf(sand));
                txtsteell.setText(String.valueOf(steel));
                txtfini.setText(String.valueOf(finish));
                txtfitt.setText(String.valueOf(fitting));
                transcolor.setText(String.valueOf(transport));

                cementbag.setText(String.valueOf(bag));
                sandton.setText(String.valueOf(dton));
                aggrton.setText(String.valueOf(rton));
                steelkg.setText(String.valueOf(kg));
                paintlt.setText(String.valueOf(lts));
                brickscs.setText(String.valueOf(pcs));
                flooringft.setText(String.valueOf(ft));

            }
        });
    }

    private void moredetails(){
        Intent intent =new Intent(EstimationActivity.this,MoreDetailsActivity.class);
        startActivity(intent);
    }


}