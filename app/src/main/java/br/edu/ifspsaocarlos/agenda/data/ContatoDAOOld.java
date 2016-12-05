package br.edu.ifspsaocarlos.agenda.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifspsaocarlos.agenda.model.Contato;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContatoDAOOld {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ContatoDAOOld(Context context) {
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<Contato> buscaContato(String campo) {
        database = dbHelper.getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        Cursor cursor;

        String[] cols = new String[]{SQLiteHelper.KEY_ID, SQLiteHelper.KEY_NAME,
                SQLiteHelper.KEY_FONE, SQLiteHelper.KEY_EMAIL, SQLiteHelper.KEY_FONE_ADICIONAL,
                    SQLiteHelper.KEY_DATA_ANIVERSARIO};
        String where = null;
        String[] argWhere = null;

        if (campo != null && isEmail(campo)) {
            where = SQLiteHelper.KEY_EMAIL + " like ?";
            argWhere = new String[]{campo + "%"};
            cursor = buscaPorCampo(cols, where, argWhere, SQLiteHelper.KEY_EMAIL);
        } else if (campo != null) {
            where = SQLiteHelper.KEY_NAME + " like ?";
            argWhere = new String[]{campo + "%"};
            cursor = buscaPorCampo(cols, where, argWhere, SQLiteHelper.KEY_NAME);
        } else {
            cursor = buscaPorCampo(cols, where, argWhere, SQLiteHelper.KEY_NAME);
        }

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Contato contato = new Contato();
                contato.setId(cursor.getInt(0));
                contato.setNome(cursor.getString(1));
                contato.setFone(cursor.getString(2));
                contato.setEmail(cursor.getString(3));
                contato.setFoneAdicional(cursor.getString(4));
                contato.setDataAniversario(cursor.getString(5));
                contatos.add(contato);
                cursor.moveToNext();
            }
            cursor.close();
        }
        database.close();
        return contatos;
    }

    public void atualizaContato(Contato c) {
        database = dbHelper.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(SQLiteHelper.KEY_NAME, c.getNome());
        updateValues.put(SQLiteHelper.KEY_FONE, c.getFone());
        updateValues.put(SQLiteHelper.KEY_EMAIL, c.getEmail());
        updateValues.put(SQLiteHelper.KEY_FONE_ADICIONAL, c.getFoneAdicional());
        updateValues.put(SQLiteHelper.KEY_DATA_ANIVERSARIO, c.getDataAniversario());
        database.update(SQLiteHelper.DATABASE_TABLE, updateValues, SQLiteHelper.KEY_ID + "="
                + c.getId(), null);
        database.close();
    }


    public void insereContato(Contato c) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NAME, c.getNome());
        values.put(SQLiteHelper.KEY_FONE, c.getFone());
        values.put(SQLiteHelper.KEY_EMAIL, c.getEmail());
        values.put(SQLiteHelper.KEY_FONE_ADICIONAL, c.getFoneAdicional());
        values.put(SQLiteHelper.KEY_DATA_ANIVERSARIO, c.getDataAniversario());
        database.insert(SQLiteHelper.DATABASE_TABLE, null, values);
        database.close();
    }

    public void apagaContato(Contato c) {
        database = dbHelper.getWritableDatabase();
        database.delete(SQLiteHelper.DATABASE_TABLE, SQLiteHelper.KEY_ID + "="
                + c.getId(), null);
        database.close();
    }

    private Cursor buscaPorCampo(String[] cols, String where, String[] argWhere, String nomeCampo) {
        return database.query(SQLiteHelper.DATABASE_TABLE, cols, where, argWhere,
                null, null, nomeCampo);
    }

    private Boolean isEmail(String campo) {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(campo);
        return matcher.matches();
    }
}