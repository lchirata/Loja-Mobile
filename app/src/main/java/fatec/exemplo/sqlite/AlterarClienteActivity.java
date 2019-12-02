package fatec.exemplo.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import fatec.exemplo.sqlite.banco.BancoDadosGerenciador;

public class AlterarClienteActivity extends Activity implements OnClickListener {

    private EditText edtNome;
    private EditText edtEndereco;
    private EditText edtIdade;

    private Button btnAlterar, btnDeletar;


    private long _id;

    private BancoDadosGerenciador dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alterar);

        dbManager = new BancoDadosGerenciador(this);
        dbManager.open();

        edtNome = (EditText) findViewById(R.id.edtNomeT);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtIdade = (EditText) findViewById(R.id.edtIdade);

        btnAlterar = (Button) findViewById(R.id.btnAlterar);
        btnDeletar = (Button) findViewById(R.id.btnDeletar);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nome = intent.getStringExtra("nome");
        String endereco = intent.getStringExtra("endereco");
        String idade = intent.getStringExtra("idade");


        _id = Long.parseLong(id);

        edtNome.setText(nome);
        edtEndereco.setText(endereco);
        edtIdade.setText(idade);

        btnAlterar.setOnClickListener(this);
        btnDeletar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAlterar:
                String nome =  edtNome.getText().toString();
                String endereco = edtEndereco.getText().toString();
                String idade = edtIdade.getText().toString();

                dbManager.update(_id, nome, endereco, idade);
                this.returnHome();
                break;

            case R.id.btnDeletar:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ListarClienteActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
