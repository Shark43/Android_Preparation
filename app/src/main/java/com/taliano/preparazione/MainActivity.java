package com.taliano.preparazione;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_DESCRIZIONE = 545;
    private static final int REQUEST_CODE_ADD = 752;
    private static final int DEF_ID = 100;
    String path;
    ArrayList<Studenti> studentiArrayList;
    ArrayList<Integer> immagini;
    LinearLayout verticalLayout;
    Integer nuser;
    Integer currentMaxID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = MainActivity.this.getFilesDir().getPath() + "/studenti.txt";

        nuser = 0;
        currentMaxID = -1;
        studentiArrayList = new ArrayList<Studenti>();
        verticalLayout = findViewById(R.id.verticalLeyout);
        caricaFile();
        caricaLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_menu_add:
                Intent intent = new Intent(MainActivity.this, Add.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                MainActivity.this.startActivityForResult(intent, REQUEST_CODE_ADD);
                break;
            case R.id.item_menu_salva:
                salva();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salva()
    {
        if(!studentiArrayList.isEmpty()) {
            try {
                FileWriter writer=new FileWriter(path,false); //secondo parametro: false overwrite, true append
                BufferedWriter bufferedWriter=new BufferedWriter(writer);

                bufferedWriter.write(Integer.toString(currentMaxID));
                bufferedWriter.newLine();
                boolean b = false;
                for(Studenti studenti:studentiArrayList)
                {
                    if(b) {
                        bufferedWriter.newLine();
                    }
                    b = true;
                    bufferedWriter.newLine();
                    bufferedWriter.write(Integer.toString(studenti.getID()));
                    bufferedWriter.newLine();
                    bufferedWriter.write(studenti.getNome());
                    bufferedWriter.newLine();
                    bufferedWriter.write(studenti.getCognome());
                    bufferedWriter.newLine();
                    bufferedWriter.write(studenti.getCitta());
                    bufferedWriter.newLine();
                    bufferedWriter.write(studenti.getSesso());
                    bufferedWriter.newLine();
                    if(studenti.getImgI() == -1) {
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write(Integer.toString(studenti.getImgI()));
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.write(Integer.toString(studenti.getEta()));
                }
                bufferedWriter.flush();
                bufferedWriter.close();
                writer.close();

                alert("File salvato CORRETTAMENTE!!");
            } catch (IOException e) {
                e.printStackTrace();
                alert(e.getMessage());
            }
        }
        else
        {
            try {
                alert("Lista VUOTA!!");

                FileWriter writer = new FileWriter(path,false);
                BufferedWriter bufferedWriter=new BufferedWriter(writer);

                bufferedWriter.write("");
                bufferedWriter.close();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void caricaLayout() {
        verticalLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 80);
        layoutParams.setMargins(20, 20, 0, 0);
        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(80,80);
        imageLayoutParams.setMargins(2,2,2,2);
        for (final Studenti studente:studentiArrayList) {
            LinearLayout horizzontal = new LinearLayout(MainActivity.this);
            horizzontal.setOrientation(LinearLayout.HORIZONTAL);

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(studente.getImg());
            imageView.setLayoutParams(imageLayoutParams);
            horizzontal.addView(imageView);

            TextView textViewNome = new TextView(MainActivity.this);
            textViewNome.setText(studente.getNome());
            textViewNome.setLayoutParams(layoutParams);
            horizzontal.addView(textViewNome);

            TextView textViewCognome = new TextView(MainActivity.this);
            textViewCognome.setText(studente.getCognome());
            textViewCognome.setLayoutParams(layoutParams);
            horizzontal.addView(textViewCognome);

            TextView textViewCitta = new TextView(MainActivity.this);
            textViewCitta.setText(studente.getCitta());
            textViewCitta.setLayoutParams(layoutParams);
            horizzontal.addView(textViewCitta);

            TextView textViewSesso = new TextView(MainActivity.this);
            textViewSesso.setText(studente.getSesso());
            textViewSesso.setLayoutParams(layoutParams);
            horizzontal.addView(textViewSesso);

            ImageView imageInfo = new ImageView(MainActivity.this);
            imageInfo.setImageResource(R.mipmap.info);
            imageInfo.setLayoutParams(imageLayoutParams);
            imageInfo.setId(studente.getID() + DEF_ID - 1);
            imageInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView  imageView = (ImageView) v;
                    MainActivity.this.alert(imageView.getId()+"");
                    Studenti studente = studentiArrayList.get(imageView.getId() - DEF_ID);
                    Intent intent = new Intent(MainActivity.this, Dettagli.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studente", studente);
                    intent.putExtras(bundle);
                    MainActivity.this.startActivityForResult(intent, REQUEST_CODE_DESCRIZIONE);
                }
            });
            horizzontal.addView(imageInfo);

            verticalLayout.addView(horizzontal);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_DESCRIZIONE && data != null){
            if(resultCode == RESULT_OK){
                alert("salva");
                Bundle bundle = data.getExtras();
                Studenti studente = (Studenti) bundle.getSerializable("studente");
                studentiArrayList.set(studente.getID() - 1, studente);
                caricaLayout();
            } else {
                alert("not ok");
            }
        } else if(requestCode == REQUEST_CODE_ADD && data != null){
            if(resultCode == RESULT_OK){
                alert("aggiungi");
                Bundle bundle = data.getExtras();
                Studenti studente = (Studenti) bundle.getSerializable("studente");
                studente.setID(currentMaxID);
                currentMaxID++;
                studentiArrayList.add(studente);
                caricaLayout();
            } else {
                alert("not ok");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void caricaFile() {
        File file=new File(path);
        if(file.isFile())
        {
            try {
                FileReader reader=new FileReader(file);

                BufferedReader bufferedReader=new BufferedReader(reader); //riga per riga

                String tmp;
                while((tmp=bufferedReader.readLine())!=null)
                {
                    if(currentMaxID == -1){
                        currentMaxID = Integer.parseInt(tmp);
                        bufferedReader.readLine();
                    }
                    Studenti studente = new Studenti();
                    studente.setID(Integer.parseInt(bufferedReader.readLine()));
                    studente.setNome(bufferedReader.readLine());
                    studente.setCognome(bufferedReader.readLine());
                    studente.setCitta(bufferedReader.readLine());
                    studente.setSesso(bufferedReader.readLine());
                    try {
                        studente.setImgI(Integer.parseInt(bufferedReader.readLine()));
                    } catch (Exception e) {
                        studente.setImgI(-1);
                    }
                    if(studente.getImgI() != null && studente.getImgI() != -1) {
                        studente.setImg(this.getResources().getIdentifier("img"+ (studente.getImgI()),"mipmap", this.getPackageName()));
                        if(studente.getImg() == 0){
                            studente.setImg(this.getResources().getIdentifier("user","mipmap", this.getPackageName()));
                        }
                    } else {
                        studente.setImg(this.getResources().getIdentifier("user","mipmap", this.getPackageName()));
                    }
                    studente.setEta(Integer.parseInt(bufferedReader.readLine()));
                    nuser++;
                    studentiArrayList.add(studente);
                }

                bufferedReader.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            alert("File non TROVATO!!");
    }

    public void alert(String s)
    {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }
}
