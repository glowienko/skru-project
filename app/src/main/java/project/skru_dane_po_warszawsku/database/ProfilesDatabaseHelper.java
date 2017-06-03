package project.skru_dane_po_warszawsku.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import project.skru_dane_po_warszawsku.models.UserProfile;

public class ProfilesDatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Profiles.db";
    public static final String TABLE_NAME = "user_profiles";

    //columns
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String LAST_NAME = "last_name";
    public static final String PHONE_NUMBER = "phone_number";

    public ProfilesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists " + TABLE_NAME +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, last_name TEXT, " +
                        "email TEXT, " +
                        "phone_number INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addProfile(UserProfile newUserProfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, newUserProfile.getName());
        contentValues.put(LAST_NAME, newUserProfile.getLastName());
        contentValues.put(EMAIL, newUserProfile.getEmail());
        contentValues.put(PHONE_NUMBER, newUserProfile.getPhoneNumber());

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Integer deleteProfile(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { String.valueOf(id) });
    }

    //get all just for convenience, may be changed :o we need only one user profile
    public UserProfile getAllProfiles() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            UserProfile newUserProfile = new UserProfile();

            newUserProfile.setName(res.getString(res.getColumnIndex(NAME)));
            newUserProfile.setLastName(res.getString(res.getColumnIndex(LAST_NAME)));
            newUserProfile.setEmail(res.getString(res.getColumnIndex(EMAIL)));
            newUserProfile.setPhoneNumber(res.getInt(res.getColumnIndex(PHONE_NUMBER)));
            newUserProfile.setId(res.getInt(res.getColumnIndex("id")));

            return newUserProfile;
//            res.moveToNext();
        }
        return null;
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
