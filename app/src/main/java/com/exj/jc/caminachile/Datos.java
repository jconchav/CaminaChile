package com.exj.jc.caminachile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.exj.jc.caminachile.R.id.btnCampeonato;

public class Datos extends AppCompatActivity {

    private Button btnCampeonato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);

        btnCampeonato = (Button)findViewById(R.id.btnCampeonato);

        btnCampeonato.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent btnCampeonato = new Intent(Datos.this, Ubicacion.class);
                startActivity(btnCampeonato);


            }

        });
    }
}
