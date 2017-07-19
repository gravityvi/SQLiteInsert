package com.example.ravi.insertsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLitedatabaseAdapter {

     SQlitehelper sqlitehelper;

    public SQLitedatabaseAdapter(Context context){
         sqlitehelper=new SQlitehelper(context);

     }

    public long insertData(String name, String password){
        SQLiteDatabase db=sqlitehelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SQlitehelper.NAME,name);
        contentValues.put(SQlitehelper.PASSWORD,password);
        long id=db.insert(SQlitehelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public String getAllData()
    {
        SQLiteDatabase db=sqlitehelper.getWritableDatabase();

        //select id name password from UsersTable
        String[] SelectedColumns={SQlitehelper.UID,SQlitehelper.NAME,SQlitehelper.PASSWORD};
        Cursor cursor =db.query(SQlitehelper.TABLE_NAME,SelectedColumns,null,null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while (cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(SQlitehelper.UID);
            int index2=cursor.getColumnIndex(SQlitehelper.NAME);
            int index3=cursor.getColumnIndex(SQlitehelper.PASSWORD);

            int cid=cursor.getInt(0);
            String name=cursor.getString(1);
            String password=cursor.getString(2);
            buffer.append(cid+" "+name+" "+password+"\n");

        }

        return buffer.toString();
    }

    public String getData(String name)
    {
        SQLiteDatabase db=sqlitehelper.getWritableDatabase();

        //select id name password from UsersTable
        String[] SelectedColumns={SQlitehelper.UID,SQlitehelper.NAME,SQlitehelper.PASSWORD};
       // Cursor cursor =db.query(SQlitehelper.TABLE_NAME,SelectedColumns,SQlitehelper.NAME+"='"+name+"'",null,null,null,null); this is directly using selection query
        String[] Selectionargs={name};
        Cursor cursor =db.query(SQlitehelper.TABLE_NAME,SelectedColumns,SQlitehelper.NAME+"=?",Selectionargs,null,null,null);//this is using selection and selection arguements.
        StringBuffer buffer=new StringBuffer();
        while (cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(SQlitehelper.UID);
            int index2=cursor.getColumnIndex(SQlitehelper.NAME);
            int index3=cursor.getColumnIndex(SQlitehelper.PASSWORD);


            int cid=cursor.getInt(index1);
            String password=cursor.getString(index3);
            buffer.append(cid+" "+name+" "+password+"\n");}
        return buffer.toString();
    }

    public int UpdateData(String oldname,String name)
    {
        SQLiteDatabase db=sqlitehelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SQlitehelper.NAME,name);
        int id=db.update(SQlitehelper.TABLE_NAME,contentValues,SQlitehelper.NAME+"=?",new String[]{oldname});
        return id;
    }

    public int DeleteData(String name)
    {
        SQLiteDatabase db=sqlitehelper.getWritableDatabase();
        int id=db.delete(SQlitehelper.TABLE_NAME,SQlitehelper.NAME+"=?",new String[]{name});
        return id;
    }

   static class SQlitehelper extends SQLiteOpenHelper{


        private static final String DATABASE_NAME="UsersDatabase";
        private static final String TABLE_NAME="UsersTable";
        private static final int DATABASE_VERSION=1;
        private static final String UID="_id";
        private static final String NAME="Name";
        private static final String PASSWORD="Password";
        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ( "+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+ " VARCHAR(255),"+PASSWORD+" VARCHAR(255));";
        private static final String DROP_TABLE=" DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;
        public SQlitehelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
            Message.message(this.context,"constructor called");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE);
                Message.message(context,"onCreate called");}
            catch (SQLException e){
                Message.message(context," "+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(DROP_TABLE);
                Message.message(context,"onUPgrade called");
                onCreate(db);}
            catch (SQLException e){
                Message.message(context," "+e);
            }
        }

    }
}
