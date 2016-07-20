package ato.spordiklubi.lasteMV.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.Arrays;
import java.util.List;

import ato.spordiklubi.lasteMV.data.Participant;
import ato.spordiklubi.lasteMV.data.Result;


/**
 * Created by Olev on 26.07.2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NUMBER = "stardinumber";
    private static final String COLUMN_NAME = "nimi";
    private static final String COLUMN_SPRINT = "sprint";
    private static final String COLUMN_LONG_JUMP = "kaugushüpe";
    private static final String COLUMN_BALL = "pallivise";
    private static final String COLUMN_MEDICINE_BALL = "topispallijänn";
    private static final String COLUMN_RUN = "pikkjooks";
    private static final String COLUMN_FOOTBALL = "jalgpall";
    private static final String COLUMN_BASKETBALL = "korvpall";
    private static final String COLUMN_VOLLEYBALL = "võrkpall";
    private static final String COLUMN_BOXES = "kastironimine";
    private static final String COLUMN_BIKE = "jalgratas";
    private static final String COLUMN_REFEREE = "kohtunik";
    private static final String DATABASE_NAME = "LasteMV.db";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addRefereeNameToSelectedEvent (String refereeName , String tableName){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_REFEREE,refereeName);
        database.update(tableName,values,COLUMN_ID  + "=" + 1,null);
    }

    public void dropTable ( String tableName){
        SQLiteDatabase database = getWritableDatabase();
        String drop = "DROP TABLE IF EXISTS " + tableName;
        database.execSQL(drop);
    }
    public void createTable(String tableName) {
        SQLiteDatabase database = getWritableDatabase();
        String query = "CREATE  TABLE IF NOT EXISTS " + tableName + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NUMBER + " INTEGER, " + COLUMN_NAME + " TEXT, " + COLUMN_SPRINT + " DOUBLE, " + COLUMN_LONG_JUMP + " DOUBLE, "
                + COLUMN_BALL + " DOUBLE, " + COLUMN_MEDICINE_BALL + " DOUBLE, " + COLUMN_RUN + " DOUBLE, "
                + COLUMN_FOOTBALL + " DOUBLE, " + COLUMN_BASKETBALL + " DOUBLE, " + COLUMN_VOLLEYBALL + " DOUBLE, "
                + COLUMN_BOXES + " DOUBLE, " + COLUMN_BIKE + " DOUBLE, " + COLUMN_REFEREE + " TEXT " +  ")";
        database.execSQL(query);
    }

    public long getTableRowCount(String tableName) {
        SQLiteDatabase database = getWritableDatabase();
        String query = "SELECT COUNT( nimi ) FROM " + tableName;
        SQLiteStatement statement = database.compileStatement(query);
        long count = statement.simpleQueryForLong();
        return count;
    }

    public Participant getParticipantFromDb(int id, String tableName) {
        Participant participant = new Participant();
        SQLiteDatabase database = getWritableDatabase();
        String query = "SELECT * FROM " + tableName + " WHERE " + COLUMN_ID + "=" + id;
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NUMBER)) != null && c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                participant.setNumber(c.getInt(c.getColumnIndex(COLUMN_NUMBER)));
                participant.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                database.close();
                return participant;
            }
        }
        database.close();
        return participant;
    }

    public Result getResultFromDb(int id, String tableName, String dicipline) {
        Result result = new Result();
        SQLiteDatabase database = getWritableDatabase();
        String query = "SELECT * FROM " + tableName + " WHERE " + COLUMN_ID + " = " + id;
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        List<String> columnNames = Arrays.asList(c.getColumnNames());
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null && c.getString(c.getColumnIndex(COLUMN_NUMBER)) != null && columnNames.contains(dicipline.toLowerCase())) {
                result.setResult(c.getFloat(c.getColumnIndex(dicipline.toLowerCase())));
            }
            c.moveToNext();
        }
        database.close();
        return result;
    }

    public void addParticipant(Participant participant, String tableName) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER, participant.getNumber());
        values.put(COLUMN_NAME, participant.getName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableName, null, values);
        db.close();
    }

    public void addResult(int id, Result result, String tableName, String dicipline) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dicipline.toLowerCase(),result.getResult());
        database.update(tableName, values, COLUMN_ID + "=" + id, null);
    }
}
