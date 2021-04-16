package com.drmarks.constructionfriend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASEVERSION = 1;

    public static final String DATABASENAME = "InventoryManager.db";

    // Inventory Table Name
    private static final String TABLE_INVENT = "invent";

    //Invent Table Column Names
    private static final String COLUMN_INVENT_PRODUCT_NAME = "invent_product_name";
    private static final String COLUMN_INVENT_PRICE = "invent_price";
    private static final String COLUMN_INVENT_QUANTITY = "invent_quantity";
    private static final String COLUMN_INVENT_TOTAL = "invent_total";

    //create table sql query
    private String CREATE_INVENT_TABLE = "CREATE TABLE " + TABLE_INVENT + "("
            + COLUMN_INVENT_PRODUCT_NAME + "TEXT,"
            + COLUMN_INVENT_PRICE + " TEXT,"
            + COLUMN_INVENT_QUANTITY + " TEXT,"
            + COLUMN_INVENT_QUANTITY + " TEXT"
            + ")";

    //drop table sql query
    private String DROP_INVENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_INVENT;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_INVENT_TABLE);

        onCreate(db);
    }

    /**
     * This method is to create invent record
     *
     * @param invent
     */
    public boolean addinvent(Invent invent) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_INVENT_PRODUCT_NAME, invent.getProduct_name());
            values.put(COLUMN_INVENT_PRICE, invent.getPrice());
            values.put(COLUMN_INVENT_QUANTITY, invent.getQty());
            values.put(COLUMN_INVENT_TOTAL, invent.getTotal());
            db.insert(TABLE_INVENT, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            Log.e("addInvent", e + "");
        }
        return false;
    }

    /**
     * This method is to fetch all invent and return the list of invent records
     *
     * @return list
     */
    public List<Invent> getAllData() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_INVENT_PRODUCT_NAME,
                COLUMN_INVENT_PRICE,
                COLUMN_INVENT_QUANTITY,
                COLUMN_INVENT_TOTAL
        };
        String sortOrder =
                COLUMN_INVENT_PRODUCT_NAME + " ASC";
        List<Invent> inventList = new ArrayList<Invent>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the invent table
        /**
         * Here query function is used to fetch records from invent table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT invent_id,invent_name,invent_email,invent_password FROM invent ORDER BY invent_name;
         */
        Cursor cursor = db.query( TABLE_INVENT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Invent invent = new Invent();
                invent.setproduct_name( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_PRODUCT_NAME ) ) );
                invent.setPrice( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_PRICE ) ) );
                invent.setQty( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_QUANTITY ) ) );
                invent.setTotal( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_TOTAL ) ) );

                // Adding invent record to list
                inventList.add(  invent );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return invent list
        return inventList;
    }
    /**
     * This method to update invent record
     *
     * @param invent
     */

    public boolean updateInvent(Invent invent) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put( COLUMN_INVENT_PRODUCT_NAME, invent.getProduct_name() );
            values.put( COLUMN_INVENT_PRICE, invent.getPrice() );
            values.put( COLUMN_INVENT_QUANTITY, invent.getQty() );
            values.put( COLUMN_INVENT_TOTAL, invent.getTotal() );



            // updating row
            db.update( TABLE_INVENT, values, COLUMN_INVENT_PRODUCT_NAME + " = ?",
                    new String[]{String.valueOf( invent.getProduct_name() )} );
            db.close();
            return true;
        } catch (Exception e) {
            Log.e( "updateinvent", e + "" );
        }
        return false;
    }

    public List<Invent> getcurrentinvent(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_INVENT_PRODUCT_NAME,
                COLUMN_INVENT_PRICE,
                COLUMN_INVENT_QUANTITY,
                COLUMN_INVENT_TOTAL
        };
        // sorting orders
        String sortOrder = COLUMN_INVENT_PRODUCT_NAME + " ASC";
        List<Invent> inventList = new ArrayList<Invent>();

        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_INVENT_PRODUCT_NAME + " = ? ";

        // selection argument
        String[] selectionArgs = {email};
        // query the invent table
        /**
         * Here query function is used to fetch records from invent table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT invent_id,invent_name,invent_email,invent_password FROM invent ORDER BY invent_name;
         */
        Cursor cursor = db.query( TABLE_INVENT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Invent invent = new Invent();
                invent.setproduct_name( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_PRODUCT_NAME ) ) );
                invent.setPrice( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_PRICE ) ) );
                invent.setQty( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_QUANTITY ) ) );
                invent.setTotal( cursor.getString( cursor.getColumnIndex( COLUMN_INVENT_TOTAL ) ) );
                // Adding invent record to list
                inventList.add( invent );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return invent list
        return inventList;
    }

    /**
     * This method is to delete user record
     *
     * @param
     */
    public void deletedata(String p_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete( TABLE_INVENT, COLUMN_INVENT_PRODUCT_NAME + " = ?",
                new String[]{p_name} );
        db.close();
    }




}