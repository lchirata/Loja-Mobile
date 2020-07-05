package fatec.exemplo.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCadastro = null;
    Button btnListar = null;
    Button btnProduto = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnProduto = (Button) findViewById(R.id.btnProduto);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastrarClienteActivity.class);
                startActivity(i);
            }

        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListarClienteActivity.class);
                startActivity(i);
            }
        });

        btnProduto = findViewById(R.id.btnProduto);
        btnProduto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToCreateProduct();
                    }
                }
        );
    }

    private void goToCreateProduct() {
        // REDIRECT CADASTRA PRODUTO
        Intent it = new Intent(MainActivity.this, CadastrarProdutoActivity.class);
        startActivity(it);
    }

}
