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


    public static final String PRODUTO = "produto";

    // Table columns
    public static final String IDP = "_id";
    public static final String NOMEP = "nome";
    public static final String PRECO = "preco";

    public static final String PRODUTO_CLIENTE  = "cliente";

    // Table columns
    public static final String IDCOMPRA = "_id";
    public static final String IDCLINTE = "id_compra";
    public static final String IDPRODUTO = "id_produto";

    // Database Information
    static final String DB_NAME = "exemplo_cliente.db";

    // database version
    static final int DB_VERSION = 2;


    public BancoDeDados(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS "+TABELA +
                "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT, " +
                ENDERECO + " TEXT, " +
                IDADE + " TEXT ) ";
        db.execSQL(sql);


        String _sql = "CREATE TABLE IF NOT EXISTS "+PRODUTO +
                "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMEP + " TEXT, " +
                PRECO + " TEXT ) ";
        db.execSQL(_sql);

        String sql_ = "CREATE TABLE IF NOT EXISTS "+PRODUTO_CLIENTE +
                "( " + IDCOMPRA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 IDCLINTE + " TEXT, " +
                IDPRODUTO + " TEXT ) " +
                "";
        db.execSQL(sql_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUTO_CLIENTE );
        onCreate(db);
    }
}
