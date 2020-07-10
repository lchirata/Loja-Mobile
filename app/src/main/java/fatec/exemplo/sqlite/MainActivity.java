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
import fatec.exemplo.sqlite.banco.Singleton;

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

        // PEGA ID DO USUARIO
        String idUsuario = Singleton.getInstance().getUsuario();

        dbManager = new BancoDadosGerenciador(this);
        dbManager.open();

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnCadastroProduto = (Button) findViewById(R.id.btnProduto);
        btnListaProduto = (Button) findViewById(R.id.btnListaProduto);
        btnListaCompras = (Button) findViewById(R.id.btnListaCompras);

        TextView nomeUsuarioHome = (TextView) findViewById(R.id.nomeUsuarioHome);

        String username = this.dbManager.getUsernameById(idUsuario);
        nomeUsuarioHome.setText(username);

        // CADASTRA CLIENTE
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastrarClienteActivity.class);
                startActivity(i);
            }

        });

        // LISTA CLIENTE
        btnListar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListarClienteActivity.class);
                startActivity(i);
            }
        });

        // CADASTRAR PRODUTO
        btnCadastroProduto = findViewById(R.id.btnProduto);
        btnCadastroProduto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToCreateProduct();
                    }
                }
        );

        // LISTA PRODUTO
        //btnListaProduto = findViewById(R.id.btnListaProduto);
        btnListaProduto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToListProduct();
                    }
                }
        );

        // COMPRAS
        btnListaCompras.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToBuyed();
                    }
                }
        );
    }

    private void goToCreateProduct() {
        // REDIRECT CADASTRA PRODUTO
        Intent it = new Intent(MainActivity.this, CadastrarProdutoActivity.class);
        startActivity(it);
    }

    private void goToListProduct() {
        // REDIRECT Lista PRODUTO
        Intent it = new Intent(MainActivity.this, ListarProdutoActivity.class);
        startActivity(it);
    }

    private void goToBuyed() {
        // REDIRECT COMPRAS
        Intent it = new Intent(MainActivity.this, ListarComprasActivity.class);
        startActivity(it);
    }

}
