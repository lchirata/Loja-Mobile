package fatec.exemplo.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fatec.exemplo.sqlite.banco.BancoDadosGerenciador;
import fatec.exemplo.sqlite.banco.BancoDeDados;
import fatec.exemplo.sqlite.dto.Cliente;

public class CadastrarClienteActivity extends AppCompatActivity {

    Button btnAdicionarCliente = null;
    private BancoDadosGerenciador banco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnAdicionarCliente = (Button) findViewById(R.id.btnAddCliente);

        banco = new BancoDadosGerenciador(this);
        banco.open();

        btnAdicionarCliente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText nome = (EditText) findViewById(R.id.edtNome);
                EditText endereco = (EditText) findViewById(R.id.edtEndereco);
                EditText idade = (EditText) findViewById(R.id.edtIdade);

                String r = banco.insert(nome.getText().toString(), endereco.getText().toString(),
                        idade.getText().toString());

                Toast.makeText(getApplicationContext(), r, Toast.LENGTH_LONG).show();

                returnHome();

            }

        });
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
