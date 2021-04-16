package com.drmarks.constructionfriend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private final  AppCompatActivity activity = InventoryActivity.this;
    private DatabaseHelper databaseHelper;
    private ArrayList<String> data =new ArrayList<String>();
    private ArrayList<String> data1 =new ArrayList<String>();
    private ArrayList<String> data2 =new ArrayList<String>();
    private ArrayList<String> data3 =new ArrayList<String>();

    private TableLayout table;

    EditText mName,mPrice,mQty,msub,mpay,mbalance;
    Button mAdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        mName=findViewById(R.id.edtmName);
        mPrice=findViewById(R.id.edtmPrice);
        mQty=findViewById(R.id.edtmQty);
        msub=findViewById(R.id.edtmSubTotal);
//        mpay=findViewById(R.id.edtmPayment);
        mAdd=findViewById(R.id.btnadd);
      //  mbalance=findViewById(R.id.edtmBalance);
        mAdd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mName.getText().toString().length()==0||mQty.getText().toString().length()==0||mPrice.getText().toString().length()==0) {
                    Toast.makeText(InventoryActivity.this,"Please Enter the Data",Toast.LENGTH_LONG);
                }else{
                    Add();
            }}
        });

    }

    public void Add(){
        int tot;

        String prodname =mName.getText().toString();
        String sprice =mPrice.getText().toString();
        String sqty = mQty.getText().toString();
        int price = Integer.parseInt(sprice);
        int qty=Integer.parseInt(sqty);
        tot =price*qty;
        data.add(prodname);
        data1.add(String.valueOf(price));
        data2.add(String.valueOf(qty));
        data3.add(String.valueOf(tot));

        TableLayout table = (TableLayout) findViewById(R.id.table1);

        TableRow row = new TableRow(this);
        TextView t1 =new TextView(this);
        TextView t2 =new TextView(this);
        TextView t3 =new TextView(this);
        TextView t4 =new TextView(this);

        String total;
         int Sum =0;
         for(int i=0;i<data.size();i++){
             String pname = data.get(i);
             String pprice = data1.get(i);
             String pqty = data2.get(i);
             total= data3.get(i);
             t1.setText(pname);
             t2.setText(pprice);
             t3.setText(pqty);
             t4.setText(total);

             Sum+=Integer.parseInt(data3.get(i).toString());

         }

         row.addView(t1);
         row.addView(t2);
         row.addView(t3);
         row.addView(t4);
         table.addView(row);

         msub.setText(String.valueOf(Sum));
         mName.setText("");
         mPrice.setText("");
         mQty.setText("");
         mName.requestFocus();

    }
}