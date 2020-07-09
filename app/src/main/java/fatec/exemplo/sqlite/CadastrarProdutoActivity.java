
package fatec.exemplo.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fatec.exemplo.sqlite.banco.BancoDadosGerenciador;


public class CadastrarProdutoActivity extends AppCompatActivity {

    Button btnAdicionarProduto = null;
    private BancoDadosGerenciador banco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        //int id = bundle.getInt("id");
        final String idCliente = bundle.getString("id");

        // Toast.makeText(getApplicationContext(), " Teste !!", Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);


        btnAdicionarProduto = (Button) findViewById(R.id.btnAddProduto);

        banco = new BancoDadosGerenciador(this);
        banco.open();

        btnAdicionarProduto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText nome = (EditText) findViewById(R.id.inputNomeProduto);
                EditText preco = (EditText) findViewById(R.id.inputPrecoProduto);

                String r = banco.insert(nome.getText().toString(), preco.getText().toString());

                Toast.makeText(getApplicationContext(), r, Toast.LENGTH_LONG).show();

                returnHome(idCliente);
            }

        });
    }

    public void returnHome(String idCliente) {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home_intent.putExtra("id", idCliente);
        startActivity(home_intent);
    }

}