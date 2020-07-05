package fatec.exemplo.sqlite;

import android.app.Activity;
import android.content.Intent;
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

public class ListarClienteActivity extends Activity {

    private BancoDadosGerenciador dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { BancoDeDados.ID,
            BancoDeDados.NOME, BancoDeDados.ENDERECO, BancoDeDados.IDADE };

    final int[] to = new int[] { R.id.id, R.id.nome, R.id.endereco, R.id.idade };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragmento_lista_vazia);

        dbManager = new BancoDadosGerenciador(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_lista_cliente, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtId = (TextView) view.findViewById(R.id.id);
                TextView txtNome = (TextView) view.findViewById(R.id.nome);

                TextView txtEndereco = (TextView) view.findViewById(R.id.endereco);
                TextView txtIdade = (TextView) view.findViewById(R.id.idade);

                String idd = txtId.getText().toString();
                String nome = txtNome.getText().toString();
                String endereco = txtEndereco.getText().toString();
                String idade = txtIdade.getText().toString();

                Toast.makeText(getApplicationContext(), nome, Toast.LENGTH_LONG).show();

                Intent alterarIntent = new Intent(getApplicationContext(), AlterarClienteActivity.class);
                alterarIntent.putExtra("nome", nome);
                alterarIntent.putExtra("endereco", endereco);
                alterarIntent.putExtra("idade", idade);
                alterarIntent.putExtra("id", idd);

                startActivity(alterarIntent);

                //Toast.makeText(getApplicationContext(), nome + " " + idade, Toast.LENGTH_LONG).show();
            }
        });

    }

    public static class CadastrarProdutoActivity {
    }
}
