package com.example.lab_2_20210751;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Juego extends AppCompatActivity {

    private String[] palabras;
    private Random random;
    private TextView[] letra;
    private LinearLayout linearLayout;

    private ImageView cabeza;
    private ImageView torso;
    private ImageView brazoDer;
    private ImageView brazoIz;
    private ImageView piernaDer;
    private ImageView piernaIz;

    private int vidas = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_juego);
        palabras = getResources().getStringArray(R.array.palabras);
        linearLayout=findViewById(R.id.linearLayout);
        random = new Random();
        elegirPalabra();

        cabeza = findViewById(R.id.cabeza);
        torso = findViewById(R.id.torso);
        brazoDer = findViewById(R.id.brazoDere);
        brazoIz = findViewById(R.id.brazoIz);
        piernaIz = findViewById(R.id.piernaIz);
        piernaDer = findViewById(R.id.piernaDer);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void elegirPalabra(){
        String palabraElegida = palabras[random.nextInt(palabras.length)];
        Log.d("Juego", "Palabra seleccionada: " + palabraElegida);

        letra = new TextView[palabraElegida.length()];

        for (int i=0; i<palabraElegida.length();i++){
            letra[i] = new TextView(this);
            letra[i].setText("");
            letra[i].setGravity(Gravity.CENTER);
            letra[i].setBackgroundResource(R.drawable.pngwing_com);
            //Código usado de ChatGPT para añadir margenes entre las letras
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            params.setMargins(20, 5, 20, 5);
            letra[i].setLayoutParams(params);

            linearLayout.addView(letra[i]);
        }

    }


}