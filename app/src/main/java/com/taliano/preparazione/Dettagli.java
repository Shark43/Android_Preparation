package com.taliano.preparazione;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Dettagli extends AppCompatActivity {

    private Integer id;
    private Integer img;
    private Integer imgI;
    private Integer anni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli);

        Button buttonChiudi = findViewById(R.id.buttonChiudi);
        buttonChiudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli.this, MainActivity.class);
                Dettagli.this.setResult(RESULT_CANCELED, intent);
                Dettagli.this.finish();
            }
        });

        Button buttonSalva = findViewById(R.id.buttonSalva);
        buttonSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli.this, MainActivity.class);
                Bundle bundle = new Bundle();

                EditText editTextNome = findViewById(R.id.editTextNome);
                EditText editTextCognome = findViewById(R.id.editTextCognome);
                EditText editTextCitta = findViewById(R.id.editTextCitta);
                EditText editTextSesso = findViewById(R.id.editTextSesso);
                ImageView imageView = findViewById(R.id.imageView);

                Studenti studente = new Studenti();
                studente.setNome(editTextNome.getText().toString());
                studente.setCognome(editTextCognome.getText().toString());
                studente.setCitta(editTextCitta.getText().toString());
                studente.setSesso(editTextSesso.getText().toString());
                studente.setID(id);
                studente.setImg(img);
                studente.setImgI(imgI);
                studente.setEta(anni);
                bundle.putSerializable("studente", studente);
                intent.putExtras(bundle);
                Dettagli.this.setResult(RESULT_OK, intent);
                Dettagli.this.finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Studenti studente = (Studenti) bundle.getSerializable("studente");
            EditText editTextNome = findViewById(R.id.editTextNome);
            EditText editTextCognome = findViewById(R.id.editTextCognome);
            EditText editTextCitta = findViewById(R.id.editTextCitta);
            EditText editTextSesso = findViewById(R.id.editTextSesso);
            ImageView imageView = findViewById(R.id.imageView);
            if (studente != null) {
                id = studente.getID();
                img = studente.getImg();
                imgI = studente.getImgI();
                anni = studente.getEta();
                editTextNome.setText(studente.getNome().toString());
                editTextCognome.setText(studente.getCognome().toString());
                editTextCitta.setText(studente.getCitta().toString());
                editTextSesso.setText(studente.getSesso().toString());
                imageView.setImageResource(studente.getImg());
            } else {
                alert("disco null");
            }
        } else {
            alert("bundle null");
        }
    }

    public void alert(String s)
    {
        Toast.makeText(Dettagli.this,s,Toast.LENGTH_LONG).show();
    }
}
