package com.taliano.preparazione;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button buttonChiudi = findViewById(R.id.buttonChiudiAdd);
        buttonChiudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                Add.this.setResult(RESULT_CANCELED, intent);
                Add.this.finish();
            }
        });

        Button buttonSalva = findViewById(R.id.buttonSalvaAdd);
        buttonSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                Bundle bundle = new Bundle();

                EditText editTextNome = findViewById(R.id.editTextNomeAdd);
                EditText editTextCognome = findViewById(R.id.editTextCognomeAdd);
                EditText editTextCitta = findViewById(R.id.editTextCittaAdd);
                EditText editTextSesso = findViewById(R.id.editTextSessoAdd);
                ImageView imageView = findViewById(R.id.imageViewAdd);

                Studenti studente = new Studenti();
                studente.setNome(editTextNome.getText().toString());
                studente.setCognome(editTextCognome.getText().toString());
                studente.setCitta(editTextCitta.getText().toString());
                studente.setSesso(editTextSesso.getText().toString());
                studente.setID(-1);
                studente.setImg(0);
                studente.setImgI(-1);
                studente.setEta(18);
                bundle.putSerializable("studente", studente);
                intent.putExtras(bundle);
                Add.this.setResult(RESULT_OK, intent);
                Add.this.finish();
            }
        });
    }
}
