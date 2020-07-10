package fatec.exemplo.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import fatec.exemplo.sqlite.banco.BancoDadosGerenciador;
import fatec.exemplo.sqlite.banco.BancoDeDados;
import fatec.exemplo.sqlite.banco.Singleton;

public class ListarComprasActivity extends Activity {

    private BancoDadosGerenciador dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;


    final String[] from = new String[] { BancoDeDados.IDP,  BancoDeDados.NOMEP, BancoDeDados.PRECO };

    final int[] to = new int[] { R.id.idProduto, R.id.nomeProduto, R.id.precoProduto };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_lista_vazia);
        final String idUsuario = Singleton.getInstance().getUsuario();

        dbManager = new BancoDadosGerenciador(this);
        dbManager.open();

        int idUsuarioInt = Integer.parseInt(idUsuario);
        Cursor cursor = dbManager.historicoCompra2(idUsuarioInt);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_lista_produto, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView idProduto = (TextView) view.findViewById(R.id.idProduto);
                TextView nomeProduto = (TextView) view.findViewById(R.id.nomeProduto);
                TextView precoProduto = (TextView) view.findViewById(R.id.precoProduto);

                String _idProduto = idProduto.getText().toString();
                String _nomeProduto = nomeProduto.getText().toString();
                String _precoProduto = precoProduto.getText().toString();

                String msg = dbManager.comprarProduto(_idProduto, idUsuario);

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });


    }





}

