package fatec.exemplo.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fatec.exemplo.sqlite.banco.BancoDadosGerenciador;
import fatec.exemplo.sqlite.banco.Singleton;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin = null;
    private BancoDadosGerenciador banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        banco = new BancoDadosGerenciador(this);
        banco.open();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText nome = (EditText) findViewById(R.id.edtNomeLogin);
                EditText senha = (EditText) findViewById(R.id.edtSenhaLogin);

                String _nome = nome.getText().toString();
                String _senha = senha.getText().toString();

                // LOGIN
                int idUsuario = banco.login(_nome, _senha);
                String id = String.valueOf(idUsuario);
                Singleton.getInstance().setUsuario(id);

                if (idUsuario != 0) {
                    // REDIRECT
                    goToMain(idUsuario);
                } else {
                    // FIXME: APAGA CAMPOS

                    // (OK) MSG ERRO
                    Toast.makeText(getApplicationContext(), "Login incorreto", Toast.LENGTH_LONG).show();
                }

            }


        });


    }

    private void goToMain(int idUsuario) {
        // REDIRECT Main
        Intent it = new Intent(LoginActivity.this, MainActivity.class);
        // it.putExtra("id", idUsuario);
        startActivity(it);
    }
}
