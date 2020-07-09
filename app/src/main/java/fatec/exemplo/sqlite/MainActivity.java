package fatec.exemplo.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fatec.exemplo.sqlite.banco.BancoDadosGerenciador;

public class MainActivity extends AppCompatActivity {

    Button btnCadastro = null;
    Button btnListar = null;
    Button btnCadastroProduto = null;
    Button btnListaProduto = null;
    Button btnListaCompras = null;

    private BancoDadosGerenciador dbManager;

    // Bundle bundle = getIntent().getExtras();
    // int id = bundle.getInt("id");
    // this.getUsuario(id);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new BancoDadosGerenciador(this);
        dbManager.open();

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnCadastroProduto = (Button) findViewById(R.id.btnProduto);
        btnListaProduto = (Button) findViewById(R.id.btnListaProduto);
        btnListaCompras = (Button) findViewById(R.id.btnListaCompras);

        TextView nomeUsuarioHome = (TextView) findViewById(R.id.nomeUsuarioHome);

        Bundle bundle = getIntent().getExtras();
        final int idCliente = bundle.getInt("id");
        String username = this.dbManager.getUsernameById(idCliente);
        nomeUsuarioHome.setText(username);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                try {
                    final String id = bundle.getString("id");
                    Intent i = new Intent(MainActivity.this, CadastrarClienteActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                } catch (Exception e) { }

            }

        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                try {
                    final String id = bundle.getString("id");
                    Intent i = new Intent(MainActivity.this, ListarClienteActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                } catch (Exception e) { }

            }
        });

        btnCadastroProduto = findViewById(R.id.btnProduto);
        btnCadastroProduto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = getIntent().getExtras();
                        try {
                            final String id = bundle.getString("id");
                            goToCreateProduct(id);
                        } catch (Exception e) { }
                    }
                }
        );

        //btnListaProduto = findViewById(R.id.btnListaProduto);
        btnListaProduto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = getIntent().getExtras();
                        try {
                            final String id = bundle.getString("id");
                            goToListProduct(id);
                        } catch (Exception e) { }
                    }
                }
        );

        btnListaCompras.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = getIntent().getExtras();
                        //int id = bundle.getInt("id");

                        try {
                            final String id = bundle.getString("id");
                            goToBuyed(id);
                        } catch (Exception e) {

                        }


                    }
                }
        );
    }

    private void goToCreateProduct(String id) {
        // REDIRECT CADASTRA PRODUTO
        Intent it = new Intent(MainActivity.this, CadastrarProdutoActivity.class);
        it.putExtra("id", id);
        startActivity(it);
    }

    private void goToListProduct(String id) {
        // REDIRECT Lista PRODUTO
        Intent it = new Intent(MainActivity.this, ListarProdutoActivity.class);
        it.putExtra("id", id);
        startActivity(it);
    }

    private void goToBuyed(String id) {
        // REDIRECT COMPRAS
        Intent it = new Intent(MainActivity.this, ListarComprasActivity.class);
        it.putExtra("id", id);
        startActivity(it);
    }

    private void bemVindo(String nome){
        // MOSTRAR MENSAGEM DE BEM VINDO
        String menssage = "Bem vindo " + nome;
        Toast.makeText(MainActivity.this, menssage, Toast.LENGTH_LONG).show();
    }

}
