package com.example.lab_2_20210751;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private String palabraElegida;

    private ImageView cabeza;
    private ImageView torso;
    private ImageView brazoDer;
    private ImageView brazoIz;
    private ImageView piernaDer;
    private ImageView piernaIz;

    private TextView mensaje;
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

        mensaje = findViewById(R.id.mensaje);
        mensaje.setVisibility(View.INVISIBLE);

        cabeza = findViewById(R.id.cabeza);
        torso = findViewById(R.id.torso);
        brazoDer = findViewById(R.id.brazoDere);
        brazoIz = findViewById(R.id.brazoIz);
        piernaIz = findViewById(R.id.piernaIz);
        piernaDer = findViewById(R.id.piernaDer);

        Button letraA = findViewById(R.id.letraA);
        Button letraB = findViewById(R.id.letraB);
        Button letraC = findViewById(R.id.letraC);
        Button letraD = findViewById(R.id.letraD);
        Button letraE = findViewById(R.id.letraE);
        Button letraF = findViewById(R.id.letraF);
        Button letraG = findViewById(R.id.letraG);
        Button letraH = findViewById(R.id.letraH);
        Button letraI = findViewById(R.id.letraI);
        Button letraJ = findViewById(R.id.letraJ);
        Button letraK = findViewById(R.id.letraK);
        Button letraL = findViewById(R.id.letraL);
        Button letraM = findViewById(R.id.letraM);
        Button letraN = findViewById(R.id.letraN);
        Button letraO = findViewById(R.id.letraO);
        Button letraP = findViewById(R.id.letraP);
        Button letraQ = findViewById(R.id.letraQ);
        Button letraR = findViewById(R.id.letraR);
        Button letraS = findViewById(R.id.letraS);
        Button letraT = findViewById(R.id.letraT);
        Button letraU = findViewById(R.id.letraU);
        Button letraV = findViewById(R.id.letraV);
        Button letraW = findViewById(R.id.letraW);
        Button letraX = findViewById(R.id.letraX);
        Button letraY = findViewById(R.id.letraY);
        Button letraZ = findViewById(R.id.letraZ);


        letraA.setOnClickListener(view -> verificarLetra('A',letraA));
        letraB.setOnClickListener(view -> verificarLetra('B',letraB));
        letraC.setOnClickListener(view -> verificarLetra('C',letraC));
        letraD.setOnClickListener(view -> verificarLetra('D',letraD));
        letraE.setOnClickListener(view -> verificarLetra('E',letraE));
        letraF.setOnClickListener(view -> verificarLetra('F',letraF));
        letraG.setOnClickListener(view -> verificarLetra('G',letraG));
        letraH.setOnClickListener(view -> verificarLetra('H',letraH));
        letraI.setOnClickListener(view -> verificarLetra('I',letraI));
        letraJ.setOnClickListener(view -> verificarLetra('J',letraJ));
        letraK.setOnClickListener(view -> verificarLetra('K',letraK));
        letraL.setOnClickListener(view -> verificarLetra('L',letraL));
        letraM.setOnClickListener(view -> verificarLetra('M',letraM));
        letraN.setOnClickListener(view -> verificarLetra('N',letraN));
        letraO.setOnClickListener(view -> verificarLetra('O',letraO));
        letraP.setOnClickListener(view -> verificarLetra('P',letraP));
        letraQ.setOnClickListener(view -> verificarLetra('Q',letraQ));
        letraR.setOnClickListener(view -> verificarLetra('R',letraR));
        letraS.setOnClickListener(view -> verificarLetra('S',letraS));
        letraT.setOnClickListener(view -> verificarLetra('T',letraT));
        letraU.setOnClickListener(view -> verificarLetra('U',letraU));
        letraV.setOnClickListener(view -> verificarLetra('V',letraV));
        letraW.setOnClickListener(view -> verificarLetra('W',letraW));
        letraX.setOnClickListener(view -> verificarLetra('X',letraX));
        letraY.setOnClickListener(view -> verificarLetra('Y',letraY));
        letraZ.setOnClickListener(view -> verificarLetra('Z',letraZ));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void elegirPalabra(){
        palabraElegida = palabras[random.nextInt(palabras.length)];
        Log.d("Juego", "Palabra: " + palabraElegida);

        letra = new TextView[palabraElegida.length()];

        for (int i=0; i<palabraElegida.length();i++){
            letra[i] = new TextView(this);
            letra[i].setText("");
            letra[i].setGravity(Gravity.CENTER);
            letra[i].setBackgroundResource(R.drawable.pngwing_com);

            //Código usado de ChatGPT para añadir margenes entre los espacios entre letras
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            params.setMargins(20, 5, 20, 5);
            letra[i].setLayoutParams(params);

            linearLayout.addView(letra[i]);
        }

    }

    private void verificarLetra(char letraBoton, Button boton){

        boolean centinela = false;

        for (int i = 0; i< palabraElegida.length();i++){
            if(palabraElegida.charAt(i) == letraBoton){
                letra[i].setText(String.valueOf(letraBoton));
                centinela=true;
            }
        }

        boton.setEnabled(false);
        if (!centinela) {
            vidas = vidas-1;
            mostrarFigura();
        }

        gameOver();
    }

    private void mostrarFigura(){

        if (vidas==5){
            cabeza.setVisibility(View.VISIBLE);
        } else if (vidas==4) {
            torso.setVisibility(View.VISIBLE);
        } else if (vidas==3) {
            brazoDer.setVisibility(View.VISIBLE);
        } else if (vidas==2) {
            brazoIz.setVisibility(View.VISIBLE);
        } else if (vidas==1) {
            piernaIz.setVisibility(View.VISIBLE);
        } else if (vidas==0) {
            piernaDer.setVisibility(View.VISIBLE);
            mensaje.setText("Perdiste");
            mensaje.setVisibility(View.VISIBLE);
        }
    }

    private void gameOver(){
        boolean centinela = true;
        //  Código usado de CHATGPT para poder deslglosar en el arreglos de letras y verificar si se descubrieron
        for (TextView textView : letra) {
            if (textView.getText().toString().isEmpty()) {
                centinela = false;
                break;
            }
        }
        if (centinela){
            mensaje.setText("Ganaste");
            mensaje.setVisibility(View.VISIBLE);
        }

    }



}