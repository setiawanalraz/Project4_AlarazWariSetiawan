package vsga.mobile.project4_alarazwarisetiawan.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "digitaltalent.db";

    public static final String TABLE_SQLite = "sqlite";

    public static final String COLOUMN_ID = "id";
    public static final String COLOUMN_NAME = "name";
    public static final String COLOUMN_ADDRESS = "address";
    public static final String COLOUMN_CONTACT = "contact";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLOUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLOUMN_NAME + " TEXT NOT NULL, " +
                COLOUMN_ADDRESS + " TEXT NOT NULL, " +
                COLOUMN_CONTACT + " INTEGER NOT NULL" +
                " )";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLOUMN_ID, cursor.getString(0));
                map.put(COLOUMN_NAME, cursor.getString(1));
                map.put(COLOUMN_ADDRESS, cursor.getString(2));
                map.put(COLOUMN_CONTACT, cursor.getString(3));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite", "" + wordList);

        database.close();
        return wordList;
    }

    //cek ini ntar, contact pake string ato integer?
    public void insert(String name, String address, int contact) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (name, address, contact) " +
                "VALUES ('" + name + "', '" + address + "', '" + contact + "')";

        Log.e("isert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String name, String address, int contact) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLite + " SET "
                + COLOUMN_NAME + "='" + name + "', "
                + COLOUMN_ADDRESS + "='" + address + "', "
                + COLOUMN_CONTACT + "='" + contact + "'"
                + " WHERE " + COLOUMN_ID + "=" + "'" + id + "'";

        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLOUMN_ID + "=" + "'" + id + "'";

        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
