package com.givenm.fitlife;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataManager {
    private SQLiteDatabase db;

    public static final String Database_Name = "NutritionStorage";
    public static final int Database_Version = 1;
    public static final String Table_Name = "NutritionGoals";
    public static final String Table_Row_ID = "_ID";
    public static final String Table_Row_MealName = "MealName";
    public static final String Table_Row_mealNameDescription = "MealDescription";
    public static final String Table_Row_coloryIntake = "ColoryIntake";
    public static final String Table_Row_protainIntake = "ProtainIntake";
    public static final String Table_Row_fatIntake = "FatIntake";
    public static final String Table_Row_fiberIntake = "FiberIntake";
    public static final String Table_Row_vitaminIntake = "VitaminIntake";

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, Database_Name, null, Database_Version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String query = "CREATE TABLE IF NOT EXISTS " + Table_Name + " (" +
                    Table_Row_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Table_Row_MealName + " TEXT NOT NULL," +
                    Table_Row_mealNameDescription + " TEXT NOT NULL," +
                    Table_Row_coloryIntake + " REAL, " +
                    Table_Row_protainIntake + " REAL," +
                    Table_Row_fatIntake + " REAL," +
                    Table_Row_fiberIntake + " REAL," +
                    Table_Row_vitaminIntake + " TEXT" +
                    ");";

            sqLiteDatabase.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public DataManager(Context context) {
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);

        db = helper.getWritableDatabase();
    }

    public void insert(String MealName, String mealNameDescription, float coloryIntake, float protainIntake, float fatIntake, float fiberIntake, String vitaminIntake) {
        String query = "INSERT INTO " + Table_Name + " (" + Table_Row_MealName + ", " + Table_Row_mealNameDescription + ", " + Table_Row_coloryIntake + ", " + Table_Row_protainIntake + ", " + Table_Row_fatIntake + ", " + Table_Row_fiberIntake + ", " + Table_Row_vitaminIntake + ")" +
                "VALUES ('" + MealName + "'," +
                "'" + mealNameDescription + "'," +
                "'" + coloryIntake + "'," +
                "'" + protainIntake + "'," +
                "'" + fatIntake + "'," +
                "'" + fiberIntake + "'," +
                "'" + vitaminIntake + "');";

        db.execSQL(query);
    }

    public void delete(String mealName, String mealDescription) {
        String query = "DELETE FROM " + Table_Name + " WHERE " +
                Table_Row_MealName + " = '" + mealName + "' AND " + Table_Row_mealNameDescription + " = '" + mealDescription + "';";
        db.execSQL(query);

    }

    public Cursor view() {
        String query = "SELECT * FROM " + Table_Name + ";";
        Cursor c = db.rawQuery(query, null);

        return c;
    }
}
