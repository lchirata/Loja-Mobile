package fatec.exemplo.sqlite.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BancoDadosGerenciador {

    private BancoDeDados dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public BancoDadosGerenciador(Context c) {
        context = c;
    }

    public BancoDadosGerenciador open() throws SQLException{
        dbHelper = new BancoDeDados(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public String insert(String nome, String endereco, String idade) {

        long resultado;
        ContentValues valores = new ContentValues();
        valores.put(BancoDeDados.NOME, nome);
        valores.put(BancoDeDados.ENDERECO, endereco);
        valores.put(BancoDeDados.IDADE, idade);

        resultado = database.insert(BancoDeDados.TABELA, null, valores);

        if (resultado == -1) {
            return "Erro ao inserir registro.";
        } else {
            return "Registro inserido com sucesso !";
        }
    }

    public Cursor fetch() {
        String[] columns = new String[] { BancoDeDados.ID, BancoDeDados.NOME, BancoDeDados.ENDERECO,
                BancoDeDados.IDADE };
        Cursor cursor = database.query(BancoDeDados.TABELA, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String nome, String endereco, String idade) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BancoDeDados.NOME, nome);
        contentValues.put(BancoDeDados.ENDERECO, endereco);
        contentValues.put(BancoDeDados.IDADE, idade);
        int i = database.update(BancoDeDados.TABELA, contentValues,
                BancoDeDados.ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(BancoDeDados.TABELA, BancoDeDados.ID + "=" + _id, null);
    }

}
