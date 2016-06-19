package pl.rjemiolo.ytapp.history;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HistoryDbHelper extends SQLiteOpenHelper
{
    SQLiteDatabase db;

    public static final String DATABASE_NAME = "History.db";
    public static final String TABLE_NAME = "history";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_VIDEO = "video";

    public static final String QUERY_CREATE = "CREATE TABLE " + TABLE_NAME + " (\n" +
            "    " + COLUMN_NAME_ID + "    INTEGER PRIMARY KEY,\n" +
            "    " + COLUMN_NAME_DATE + "  DATE    DEFAULT (CURRENT_TIMESTAMP),\n" +
            "    " + COLUMN_NAME_VIDEO + " STRING  NOT NULL\n" +
            ")";
    public static final String QUERY_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String QUERY_GET_HISTORY = "SELECT id, date, video FROM " + TABLE_NAME;


    public HistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP);
        onCreate(db);
    }

    public ArrayList<HistoryEntry> getHistory(){
        String[] columns = { COLUMN_NAME_ID, COLUMN_NAME_DATE, COLUMN_NAME_VIDEO };

        Cursor c = db.rawQuery("select * from history", null);
        c.moveToLast();

        ArrayList<HistoryEntry> list = new ArrayList<>();
        while( c.isBeforeFirst() == false ) {
            HistoryEntry he = new HistoryEntry();
            he.setId( c.getString( c.getColumnIndex( COLUMN_NAME_ID) ) );
            he.setDate( c.getString( c.getColumnIndex( COLUMN_NAME_DATE ) ) );
            he.setVideo( c.getString( c.getColumnIndex( COLUMN_NAME_VIDEO) ) );
            list.add( he );
            c.moveToPrevious();
        }
        return list;
    }

    public long insertHistory(String id, String data, String video){
        ContentValues entry = new ContentValues();
        // entry.put( HistoryDbHelper.COLUMN_NAME_ID, id);
//        entry.put( COLUMN_NAME_DATE, data);
        entry.put( COLUMN_NAME_VIDEO, video);
        return db.insert( TABLE_NAME, null, entry );
    }


}

