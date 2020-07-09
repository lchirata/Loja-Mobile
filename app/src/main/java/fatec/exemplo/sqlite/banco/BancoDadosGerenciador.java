package fatec.exemplo.sqlite.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

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

    public Cursor fetchProduto() {
        String[] columns = new String[] { BancoDeDados.IDP, BancoDeDados.NOMEP, BancoDeDados.PRECO };
        Cursor cursor = database.query(BancoDeDados.PRODUTO, columns, null,
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

    public String insert (String nomep, String preco) {

        long resultado;
        ContentValues valores = new ContentValues();
        valores.put(BancoDeDados.NOMEP, nomep);
        valores.put(BancoDeDados.PRECO, preco);

        resultado = database.insert(BancoDeDados.PRODUTO, null, valores);

        if (resultado == -1) {
            return "Erro ao inserir registro.";
        } else {
            return "Registro inserido com sucesso !";
        }
    }

    public Cursor listaProdutos() {
        String[] columns = new String[] { BancoDeDados.IDP, BancoDeDados.NOMEP, BancoDeDados.PRECO };
        Cursor cursor = database.query(BancoDeDados.PRODUTO, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String comprarProduto(String idProduto, String idCliente){

        long resultado;
        ContentValues valores = new ContentValues();
        valores.put(BancoDeDados.IDCLINTE, idCliente);
        valores.put(BancoDeDados.IDPRODUTO, idProduto);

        resultado = database.insert(BancoDeDados.PRODUTO_CLIENTE, null, valores);

        if (resultado == -1) {
            return "Erro ao finalizar compra.";
        } else {
            return "Compra realizada com sucesso!";
        }
    }

    public Cursor historicoCompra(int clienteId){
        String historico[][] = new String[10][2];
        ArrayList<String> _cliente = new ArrayList<String>();
        ArrayList<String> _produto = new ArrayList<String>();


        Cursor cliente = this.fetch(); // cliente
        Cursor produto = this.listaProdutos(); // produtos

        String query = "select cliente.id, cliente.nome, produto.idp, produto.nomep from cliente, produto where cliente.id and produto.id";
        database.rawQuery(query, new String[] {});

        String[] columns = new String[] { BancoDeDados.IDCOMPRA, BancoDeDados.IDCLINTE, BancoDeDados.IDPRODUTO };
        Cursor cursor = database.query(BancoDeDados.PRODUTO_CLIENTE,  columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }

    public Cursor historicoCompra2(int clienteId){

        String selectCliente = BancoDeDados.IDCLINTE + " = " + clienteId;


        List<Integer> idsProdutos = new ArrayList<>();

        String[] columns = new String[] { BancoDeDados.IDCOMPRA, BancoDeDados.IDCLINTE, BancoDeDados.IDPRODUTO };
        Cursor cursor = database.query(BancoDeDados.PRODUTO_CLIENTE,  columns, selectCliente,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            do {
                int id = cursor.getInt(2);
                idsProdutos.add(id);
            } while (cursor.moveToNext());

            return this.fetchProdutoByIds(idsProdutos);


        }
        return cursor;
    }

    public Cursor fetchProdutoByIds(List idsProdutos) {

        String ids = "";

        for (int i = 0; i < idsProdutos.size(); i++) {
            ids = ids + "" + idsProdutos.get(i);

            if(i < idsProdutos.size() - 1) {
                ids = ids + ", ";
            }
        }

        String queryProduto = "" + BancoDeDados.IDP + " in" + "(" + ids + ")";

        String[] columns = new String[] { BancoDeDados.IDP, BancoDeDados.NOMEP, BancoDeDados.PRECO };
        Cursor cursor = database.query(BancoDeDados.PRODUTO, columns, queryProduto,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int login(String nome, String senha) {

        String selectLogin = BancoDeDados.NOME + " = '" + nome + "' AND " + BancoDeDados.IDADE + " = '" + senha + "'";

        String[] columns = new String[] { BancoDeDados.ID, BancoDeDados.NOME, BancoDeDados.ENDERECO,
                BancoDeDados.IDADE };
        Cursor cursor = database.query(BancoDeDados.TABELA, columns, selectLogin,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                String data = cursor.getString(cursor.getColumnIndex(BancoDeDados.ID));
                return Integer.valueOf(data);
            }
        }

        return 0;
    }


}
