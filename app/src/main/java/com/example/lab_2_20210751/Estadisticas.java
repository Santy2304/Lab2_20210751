package com.example.lab_2_20210751;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Estadisticas extends AppCompatActivity {

    private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estadisticas);
        Button nuevoJuego = findViewById(R.id.buttonJuegoEsta);

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        Log.d("Nombre", "onCreate: "+nombre);
        nuevoJuego.setOnClickListener(view ->  {
                Intent intent1 = new Intent();
                intent1.putExtra("nombreUsuario",nombre);
                setResult(RESULT_OK,intent1);
                finish();

        });


        TextView jugador = findViewById(R.id.textView2);
        jugador.setText("Jugador: "+nombre);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}