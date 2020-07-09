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

public class ListarComprasActivity extends Activity {

    private BancoDadosGerenciador dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;


    final String[] from = new String[] { BancoDeDados.IDP,  BancoDeDados.NOMEP, BancoDeDados.PRECO };

    final int[] to = new int[] { R.id.idProduto, R.id.nomeProduto, R.id.precoProduto };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int clientId = 1;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragmento_lista_vazia);

        Bundle bundle = getIntent().getExtras();
        //int id = bundle.getInt("id");
        final String idCliente = bundle.getString("id");

        dbManager = new BancoDadosGerenciador(this);
        dbManager.open();
        Cursor cursor = dbManager.historicoCompra2(clientId);

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

                String msg = dbManager.comprarProduto(_idProduto, idCliente);

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();


            }
        });


    }





}

