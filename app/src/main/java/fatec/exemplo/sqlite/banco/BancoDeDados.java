package fatec.exemplo.sqlite.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {

    // Table Name
    public static final String TABELA = "cliente";

    // Table columns
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String ENDERECO = "endereco";
    public static final String IDADE = "idade";

    // Database Information
    static final String DB_NAME = "exemplo_cliente.db";

    // database version
    static final int DB_VERSION = 1;


    public BancoDeDados(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABELA +
                "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT, " +
                ENDERECO + " TEXT, " +
                IDADE + " TEXT ) ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
