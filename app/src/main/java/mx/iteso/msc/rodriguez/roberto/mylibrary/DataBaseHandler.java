package mx.iteso.msc.rodriguez.roberto.mylibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyLibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHandler dataBaseHandler;

    private DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context) {
        if(dataBaseHandler == null) {
            dataBaseHandler = new DataBaseHandler(context);
        }
        return dataBaseHandler;
    }

    // Nombres de las tablas que se crearan
    protected static final String TABLE_AUTHOR= "author";
    protected static final String TABLE_EDITORIAL = "editorial";
    protected static final String TABLE_BOOK = "book";

    // Columnas de la tabla de autores
    protected static final String KEY_AUTHOR_ID = "idAuthor";
    protected static final String KEY_AUTHOR_NAME = "name";
    protected static final String KEY_AUTHOR_COUNTRY = "country";
    protected static final String KEY_AUTHOR_EXTRA = "extra";

    // Columnas de la tabla de editoriales
    protected static final String KEY_EDITORIAL_ID= "idEditorial";
    protected static final String KEY_EDITORIAL_NAME= "name";

    // Columnas de la tabla de libros
    protected static final String KEY_BOOK_ID= "idBook";
    protected static final String KEY_BOOK_NAME= "name";
    protected static final String KEY_BOOK_IDAUTHOR= "idAuthor";
    protected static final String KEY_BOOK_IDEDITORIAL= "idEditorial";
    protected static final String KEY_BOOK_PUBLISHYEAR= "publishYear";
    protected static final String KEY_BOOK_COUNTRY= "country";
    protected static final String KEY_BOOK_LANGUAGE = "language";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla de autores
        String CREATE_AUTHOR_TABLE = "CREATE TABLE " + TABLE_AUTHOR + "(" +
                                     KEY_AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                     KEY_AUTHOR_NAME + " TEXT," +
                                     KEY_AUTHOR_COUNTRY + " TEXT," +
                                     KEY_AUTHOR_EXTRA + " TEXT)";
        db.execSQL(CREATE_AUTHOR_TABLE);
        // Crear la tabla de editoriales
        String CREATE_EDITORIAL_TABLE = "CREATE TABLE " + TABLE_EDITORIAL + "(" +
                                        KEY_EDITORIAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        KEY_EDITORIAL_NAME + " TEXT DEFAULT '')";
        db.execSQL(CREATE_EDITORIAL_TABLE);
        // Crear la tabla de libros
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + "(" +
                                   KEY_BOOK_ID + " INTEGER PRIMARY KEY, " +
                                   KEY_BOOK_NAME + " TEXT," +
                                   KEY_BOOK_IDAUTHOR + " INTEGER," +
                                   KEY_BOOK_IDEDITORIAL + " INTEGER," +
                                   KEY_BOOK_PUBLISHYEAR + " INTEGER," +
                                   KEY_BOOK_COUNTRY + " TEXT," +
                                   KEY_BOOK_LANGUAGE + " TEXT)";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
