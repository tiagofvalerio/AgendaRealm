package br.edu.ifspsaocarlos.agenda.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "agenda.db";
    public static final String DATABASE_TABLE = "contatos";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "nome";
    public static final String KEY_FONE_ADICIONAL = "fone";
    public static final String KEY_FONE = "fone_adicional";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DATA_ANIVERSARIO = "data_aniversario";
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_CREATE = "CREATE TABLE " + DATABASE_TABLE + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_FONE + " TEXT, " +
            KEY_EMAIL + " TEXT, " +
            KEY_FONE_ADICIONAL + " TEXT, " +
            KEY_DATA_ANIVERSARIO + " TEXT);";

    public static final String DATABASE_UPDATE_1 = "ALTER TABLE " + DATABASE_TABLE + " ADD COLUMN " + KEY_FONE_ADICIONAL + " TEXT";

    public static final String DATABASE_UPDATE_2 = "ALTER TABLE " + DATABASE_TABLE + " ADD COLUMN " + KEY_DATA_ANIVERSARIO + " TEXT";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1: {
                database.execSQL(DATABASE_UPDATE_1);
                database.execSQL(DATABASE_UPDATE_2);
                break;
            }
            case 2: {
                database.execSQL(DATABASE_UPDATE_2);
                break;
            }
            default: {
                break;
            }
        }
    }
}

