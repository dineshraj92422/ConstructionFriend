package com.drmarks.constructionfriend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BillingActivity extends AppCompatActivity {

    private ArrayList<String> data =new ArrayList<String>();
    private ArrayList<String> data1 =new ArrayList<String>();
    private ArrayList<String> data2 =new ArrayList<String>();
    private ArrayList<String> data3 =new ArrayList<String>();

    private TableLayout table;

    EditText mName,mPrice,mQty,msub,minvoice,mnumber,mcust;
    Button mAdd;
    Button btncreate;
    Bitmap bmp,scaledbmp;
    int pagewidth=1200;
    Date dateObj;
    DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        btncreate = findViewById(R.id.btncreate);
        minvoice =findViewById(R.id.edtno);
        mnumber =findViewById(R.id.edtphone);
        mcust =findViewById(R.id.edtCustName);
        mName=findViewById(R.id.edtmName);
        mPrice=findViewById(R.id.edtmPrice);
        mQty=findViewById(R.id.edtmQty);
        msub=findViewById(R.id.edtmSubTotal);
        mAdd=findViewById(R.id.btnadd);
        String customer = mcust.getText().toString();
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.architect);
        scaledbmp= Bitmap.createScaledBitmap(bmp,200,200,false);
        mAdd.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

            btncreate.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {

                    dateObj =new Date();


                    if (data.size() == 0 || data1.size() == 0 || data2.size() == 0 || data3.size() == 0) {
                        Toast.makeText(BillingActivity.this, "Please Enter The Data", Toast.LENGTH_SHORT).show();
                    } else {

                        PdfDocument myPdfDocument = new PdfDocument();
                        Paint myPaint = new Paint();
                        Paint titlePaint =new Paint();

                        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
                        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo);
                        Canvas canvas = myPage1.getCanvas();

                        canvas.drawBitmap(scaledbmp,0,0,myPaint);
                        titlePaint.setTextAlign(Paint.Align.CENTER);
                        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                        titlePaint.setTextSize(70f);
                        canvas.drawText("Construction Friend",pagewidth/2,170,titlePaint);

                        myPaint.setColor(Color.BLUE);
                        myPaint.setTextSize(30f);
                        myPaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText("Call Us-7990077294 ",1160,40,myPaint);

                        titlePaint.setTextAlign(Paint.Align.CENTER);
                        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
                        titlePaint.setTextSize(70f);
                        canvas.drawText("Invoice",pagewidth/2,300,titlePaint);

                        myPaint.setTextAlign(Paint.Align.LEFT);
                         myPaint.setTextSize(35f);
                         myPaint.setColor(Color.BLACK);
                         canvas.drawText("Customer Name: "+mcust.getText().toString(),20,390,myPaint);
                         canvas.drawText("Contact No.: "+mnumber.getText().toString(),20,440,myPaint);

                         myPaint.setTextAlign(Paint.Align.RIGHT);
                         canvas.drawText("Invoice No.: "+minvoice.getText().toString(),pagewidth-20,390,myPaint);

                         dateFormat =new SimpleDateFormat("dd/MM/YY");
                         canvas.drawText("Date: "+dateFormat.format(dateObj),pagewidth-20,440,myPaint);

                         dateFormat =new SimpleDateFormat("HH:mm:ss");
                         canvas.drawText("Time: "+dateFormat.format(dateObj),pagewidth-20,490,myPaint);

                         myPaint.setStyle(Paint.Style.STROKE);
                         myPaint.setStrokeWidth(2);
                         canvas.drawRect(20,500,pagewidth-20,600,myPaint);

                         myPaint.setTextAlign(Paint.Align.LEFT);
                         myPaint.setStyle(Paint.Style.FILL);
                         canvas.drawText("Sr. No.",40,570,myPaint);
                         canvas.drawText("Item Description",200,570,myPaint);
                         canvas.drawText("Price",700,570,myPaint);
                         canvas.drawText("Qty.",900,570,myPaint);
                         canvas.drawText("Total",1050,570,myPaint);

                         canvas.drawLine(180,500,180,600,myPaint);
                         canvas.drawLine(680,500,680,600,myPaint);
                         canvas.drawLine(880,500,880,600,myPaint);
                         canvas.drawLine(1030,500,1030,600,myPaint);

                         int startyPosition = 690;
                         int sum=0;
                         for(int i=0;i<data.size();i++){
                             canvas.drawText(""+(i+1),40,startyPosition,myPaint);
                             canvas.drawText(data.get(i),200,startyPosition,myPaint);
                             canvas.drawText(data1.get(i),700,startyPosition,myPaint);
                             canvas.drawText(data2.get(i),900,startyPosition,myPaint);
                             canvas.drawText(data3.get(i),1050,startyPosition,myPaint);
                             startyPosition+=40;
                             sum+=Integer.parseInt(data3.get(i));
                         }

                         canvas.drawLine(700,startyPosition,pagewidth-20,startyPosition,myPaint);
                         canvas.drawText("Sub-Total",700,startyPosition+=40,myPaint);
                         canvas.drawText(""+sum,1050,startyPosition,myPaint);
                         canvas.drawText(":",900,startyPosition,myPaint);
                        int Tax= (int) (sum*.18);
                        canvas.drawText("Tax(18%)",700,startyPosition+=40,myPaint);
                        canvas.drawText(""+Tax,1050,startyPosition,myPaint);
                        canvas.drawText(":",900,startyPosition,myPaint);

                        myPaint.setColor(Color.rgb(247,147,30));
                        canvas.drawRect(690,startyPosition+10,pagewidth-20,startyPosition+100,myPaint);

                        myPaint.setColor(Color.BLACK);
                        myPaint.setTextSize(50f);
                        myPaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText("Total",700,startyPosition+=60,myPaint);
                        canvas.drawText(""+(sum+Tax),1050,startyPosition,myPaint);

                         myPdfDocument.finishPage(myPage1);


                        String fileName = (mcust.getText()+".pdf");
                        String input = "Hello World";
                        String path = Environment.getExternalStorageState();
                        File file = null;
                        if (Environment.MEDIA_MOUNTED.equals(path)) {
                            try {
                                byte[] attachment = input.getBytes();

                                File folder = new File(Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOWNLOADS) + "/CF/");
                                folder.mkdirs();


                                file = new File(folder, fileName);
                                //Automatically creates the new empty file specified by the name,   if it is not exist.
                                file.createNewFile();
                                Log.i("CF", "saveFile: Dir created");
                                Toast.makeText(BillingActivity.this, "File saved to "+folder+" "+mcust.getText()+".pdf", Toast.LENGTH_LONG).show();
                                FileOutputStream out = new FileOutputStream(file);
                                out.write(attachment);

                                out.close();


                            } catch (IOException e) {
                                Log.e("CF", "saveFile: File not saved", e);
                                ;
                            }
                        }

//                    File folder = new File(Environment.getExternalStoragePublicDirectory(
//                            Environment.DIRECTORY_DOWNLOADS)+"/Construction_Friend/");
//                    File file = new File(Environment.getExternalStorageState(), "/FIRSTPDF.pdf");

                        try {
                            myPdfDocument.writeTo(new FileOutputStream(file));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myPdfDocument.close();

                    }
                }
            });

    }
    public void Add(){
        int tot;
        String invoice = minvoice.getText().toString();
        String custname= mcust.getText().toString();
        String phone =mnumber.getText().toString();
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
        mcust.setText(custname);
        mnumber.setText(phone);
        minvoice.setText(invoice);
        mName.requestFocus();

    }
}