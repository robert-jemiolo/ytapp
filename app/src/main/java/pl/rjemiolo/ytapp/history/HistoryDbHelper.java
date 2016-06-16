package pl.rjemiolo.ytapp.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            "    " + COLUMN_NAME_DATE + "  DATE    NOT NULL,\n" +
            "    " + COLUMN_NAME_VIDEO + " STRING  NOT NULL\n" +
            ")";
    public static final String QUERY_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String QUERY_GET_HISTORY = "SELECT id, date, video FROM " + TABLE_NAME;


    public HistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
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


}

