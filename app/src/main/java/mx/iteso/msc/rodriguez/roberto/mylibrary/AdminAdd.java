package mx.iteso.msc.rodriguez.roberto.mylibrary;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class AdminAdd extends Fragment implements View.OnClickListener {
    private DataBaseHandler dh;
    private EditText name;
    private EditText year;
    private EditText country;
    private EditText language;
    private AutoCompleteTextView author;
    private AutoCompleteTextView editorial;
    private Button insert;
    private List<String> authors = new ArrayList<>();
    private List<String> editorials = new ArrayList<>();

    public static Fragment newInstance() {
        return new AdminAdd();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addBook: {
                // Insertar el libro en la BD
                Book book = new Book();

                // Primero, revisar si el autor ya esta definido
                book.setAuthor(SearchAuthor(author.getEditableText().toString()));
                if (book.getAuthor() == -1) {
                    // Insertar el autor en la BD
                    book.setAuthor(InsertAuthor(author.getEditableText().toString()));
                }

                // Después, revisar si la editorial esta definida
                book.setEditorial(SearchEditorial(editorial.getEditableText().toString()));
                if (book.getEditorial() == -1) {
                    // Insertar la editorial en la BD
                    book.setEditorial(InsertEditorial(editorial.getEditableText().toString()));
                }

                // Finalmente, insertar el nuevo libro en la BD
                book.setName(name.getText().toString());
                book.setPublishYear(Integer.parseInt(year.getText().toString()));
                book.setCountry(country.getText().toString());
                book.setLanguage(language.getText().toString());
                book.setIdBook(InsertBook(book));

                // Verificar que el libro se agrego a la BD
                String text;
                if (book.getIdBook() != -1) {
                    text = getResources().getString(R.string.book_message_ok);
                }
                else {
                    text = getResources().getString(R.string.book_message_error);
                }

                Toast toast = Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                toast.show();
            }
                break;
        }
    }

    private int SearchAuthor(String nameAuthor) {
        int id = -1;
        SQLiteDatabase db = dh.getReadableDatabase();
        // Revisar si esta el autor en la BD
        String selectAuthor = "SELECT " + DataBaseHandler.KEY_AUTHOR_ID + " FROM " + DataBaseHandler.TABLE_AUTHOR +
                              " WHERE " + DataBaseHandler.KEY_AUTHOR_NAME + " = '" + nameAuthor + "'";
        Cursor cursor = db.rawQuery(selectAuthor, null);
        if(cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        return id;
    }

    private int InsertAuthor(String nameAuthor) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_AUTHOR_NAME, nameAuthor);
        values.put(DataBaseHandler.KEY_AUTHOR_COUNTRY, "");
        values.put(DataBaseHandler.KEY_AUTHOR_EXTRA, "");
        // Insertar a fila
        long inserted = db.insert(DataBaseHandler.TABLE_AUTHOR, null, values);
        // Cerrar la conexión a la BD
        db.close();
        return (int)inserted;
    }

    private int SearchEditorial(String nameEditorial) {
        int id = -1;
        SQLiteDatabase db = dh.getReadableDatabase();
        // Revisar si la editorial esta BD
        String selectEditorial = "SELECT " + DataBaseHandler.KEY_EDITORIAL_ID + " FROM " + DataBaseHandler.TABLE_EDITORIAL +
                                 " WHERE " + DataBaseHandler.KEY_EDITORIAL_NAME + " = '" + nameEditorial + "'";
        Cursor cursor = db.rawQuery(selectEditorial, null);
        if(cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        return id;
    }

    private int InsertEditorial(String nameEditorial) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_EDITORIAL_NAME, nameEditorial);
        // Insertar la fila
        long inserted = db.insert(DataBaseHandler.TABLE_EDITORIAL, null, values);
        // Cerrar la conexión a la BD
        db.close();
        return (int)inserted;
    }

    private int InsertBook(Book book) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_BOOK_NAME, book.getName());
        values.put(DataBaseHandler.KEY_BOOK_IDAUTHOR, book.getAuthor());
        values.put(DataBaseHandler.KEY_BOOK_IDEDITORIAL, book.getEditorial());
        values.put(DataBaseHandler.KEY_BOOK_PUBLISHYEAR, book.getPublishYear());
        values.put(DataBaseHandler.KEY_BOOK_COUNTRY, book.getCountry());
        values.put(DataBaseHandler.KEY_BOOK_LANGUAGE, book.getLanguage());
        // Insertar la fila
        long inserted = db.insert(DataBaseHandler.TABLE_BOOK, null, values);
        // Cerrar la conexión a la BD
        db.close();
        return (int)inserted;
    }

    private void Clean() {
        name.getText().clear();
        author.getEditableText().clear();
        editorial.getEditableText().clear();
        year.getText().clear();
        country.getText().clear();
        language.getText().clear();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_admin, container, false);
        // Acceder al manejador de la BD
        dh = DataBaseHandler.getInstance(getActivity());

        // Buscar los textview del fragmento
        name = (EditText)view.findViewById(R.id.addName);
        year = (EditText)view.findViewById(R.id.addYear);
        country = (EditText)view.findViewById(R.id.addCountry);
        language = (EditText)view.findViewById(R.id.addLanguage);

        // Buscar los elementos de auto completado del fragmento
        author = (AutoCompleteTextView)view.findViewById(R.id.addAuthor);
        editorial = (AutoCompleteTextView)view.findViewById(R.id.addEditorial);

        // Buscar el botón y agregarle el listener para el click
        insert = (Button)view.findViewById(R.id.addBook);
        insert.setOnClickListener(this);

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor;
        // Revisar si hay algún autor definido para que este disponible en el campo de auto completado
        String selectAuthor= "SELECT " + DataBaseHandler.KEY_AUTHOR_NAME + " FROM " + DataBaseHandler.TABLE_AUTHOR + " ORDER BY " + DataBaseHandler.KEY_AUTHOR_NAME;
        cursor = db.rawQuery(selectAuthor, null);
        if(cursor.moveToFirst()) {
            do {
                authors.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> authorsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item);
        authorsAdapter.addAll(authors);
        author.setAdapter(authorsAdapter);

        // Revisar si hay alguna editorial definido para que este disponible en el campo de auto completado
        String selectEditorial = "SELECT " + DataBaseHandler.KEY_EDITORIAL_NAME + " FROM " + DataBaseHandler.TABLE_EDITORIAL + " ORDER BY " + DataBaseHandler.KEY_EDITORIAL_NAME;
        cursor = db.rawQuery(selectEditorial, null);
        if(cursor.moveToFirst()) {
            do {
                editorials.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        ArrayAdapter<String> editorialsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item);
        editorialsAdapter.addAll(editorials);
        editorial.setAdapter(editorialsAdapter);

        return view;
    }
}
