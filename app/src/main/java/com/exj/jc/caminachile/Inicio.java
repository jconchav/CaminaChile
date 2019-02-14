package com.exj.jc.caminachile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.exj.jc.caminachile.R.id.btnInicio;

public class Inicio extends AppCompatActivity {

    private Button btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        btnInicio = (Button)findViewById(R.id.btnInicio);

        btnInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent btnNumero = new Intent(Inicio.this, Datos.class);
                startActivity(btnNumero);
            }

        });
    }
}
