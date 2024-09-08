package com.example.lab_2_20210751;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class Juego extends AppCompatActivity {

    private String[] palabras;
    private Random random;
    private TextView[] letra;
    private LinearLayout linearLayout;
    private String palabraElegida;

    private ArrayList<Partida> partidas;
    private long startTime;
    private long endTime;

    private ImageView cabeza;
    private ImageView torso;
    private ImageView brazoDer;
    private ImageView brazoIz;
    private ImageView piernaDer;
    private ImageView piernaIz;

    private TextView mensaje;
    private int vidas;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_juego);


        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        Log.d("Nombre", "onCreate: "+nombre);
        palabras = getResources().getStringArray(R.array.palabras);
        linearLayout=findViewById(R.id.linearLayout);
        random = new Random();
        vidas=6;
        elegirPalabra();

        Button nuevoJuego = findViewById(R.id.button2);
        nuevoJuego.setOnClickListener(view -> crearNuevoJuego());

        // Código tomado de ChatGPT para sacar el tiempo inicial
        startTime = System.currentTimeMillis();

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


        Partida partida = new Partida();
        partida.setNombre(nombre);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void elegirPalabra(){
        linearLayout.removeAllViews();
        palabraElegida = palabras[random.nextInt(palabras.length)];
        Log.d("Juego", "Palabra: " + palabraElegida);

        letra = new TextView[palabraElegida.length()];

        for (int i=0; i<palabraElegida.length();i++){
            letra[i] = new TextView(this);
            letra[i].setText("");
            letra[i].setGravity(Gravity.CENTER);
            letra[i].setBackgroundResource(R.drawable.pngwing_com);
            letra[i].getBackground().setAlpha(255);

            //Código usado de ChatGPT para añadir margenes entre los espacios entre letras
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(90, 90);
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
                letra[i].getBackground().setAlpha(0);
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
            // Código tomado de ChatGPT para calcular el tiempo final
            endTime = System.currentTimeMillis();
            long tiempo = endTime - startTime;
            long tiempoSegundos = tiempo / 1000;
            piernaDer.setVisibility(View.VISIBLE);
            mensaje.setText("Perdiste / Termino en " + tiempoSegundos + " segundos");
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

            letraA.setEnabled(false);
            letraB.setEnabled(false);
            letraC.setEnabled(false);
            letraD.setEnabled(false);
            letraE.setEnabled(false);
            letraF.setEnabled(false);
            letraG.setEnabled(false);
            letraH.setEnabled(false);
            letraI.setEnabled(false);
            letraJ.setEnabled(false);
            letraK.setEnabled(false);
            letraL.setEnabled(false);
            letraM.setEnabled(false);
            letraN.setEnabled(false);
            letraO.setEnabled(false);
            letraP.setEnabled(false);
            letraQ.setEnabled(false);
            letraR.setEnabled(false);
            letraS.setEnabled(false);
            letraT.setEnabled(false);
            letraU.setEnabled(false);
            letraV.setEnabled(false);
            letraW.setEnabled(false);
            letraX.setEnabled(false);
            letraY.setEnabled(false);
            letraZ.setEnabled(false);
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

            // Código tomado de ChatGPT para calcular el tiempo final
            endTime = System.currentTimeMillis();
            long tiempo = endTime - startTime;
            long tiempoSegundos = tiempo / 1000;
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

            letraA.setEnabled(false);
            letraB.setEnabled(false);
            letraC.setEnabled(false);
            letraD.setEnabled(false);
            letraE.setEnabled(false);
            letraF.setEnabled(false);
            letraG.setEnabled(false);
            letraH.setEnabled(false);
            letraI.setEnabled(false);
            letraJ.setEnabled(false);
            letraK.setEnabled(false);
            letraL.setEnabled(false);
            letraM.setEnabled(false);
            letraN.setEnabled(false);
            letraO.setEnabled(false);
            letraP.setEnabled(false);
            letraQ.setEnabled(false);
            letraR.setEnabled(false);
            letraS.setEnabled(false);
            letraT.setEnabled(false);
            letraU.setEnabled(false);
            letraV.setEnabled(false);
            letraW.setEnabled(false);
            letraX.setEnabled(false);
            letraY.setEnabled(false);
            letraZ.setEnabled(false);


            mensaje.setText("Ganó / Termino en " + tiempoSegundos + " segundos");
            mensaje.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_icono,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.estadisticas){
            Intent intent = new Intent(this, Estadisticas.class);
            intent.putExtra("nombre", nombre);
            launcher.launch(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void crearNuevoJuego(){
        vidas=6;
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

        letraA.setEnabled(true);
        letraB.setEnabled(true);
        letraC.setEnabled(true);
        letraD.setEnabled(true);
        letraE.setEnabled(true);
        letraF.setEnabled(true);
        letraG.setEnabled(true);
        letraH.setEnabled(true);
        letraI.setEnabled(true);
        letraJ.setEnabled(true);
        letraK.setEnabled(true);
        letraL.setEnabled(true);
        letraM.setEnabled(true);
        letraN.setEnabled(true);
        letraO.setEnabled(true);
        letraP.setEnabled(true);
        letraQ.setEnabled(true);
        letraR.setEnabled(true);
        letraS.setEnabled(true);
        letraT.setEnabled(true);
        letraU.setEnabled(true);
        letraV.setEnabled(true);
        letraW.setEnabled(true);
        letraX.setEnabled(true);
        letraY.setEnabled(true);
        letraZ.setEnabled(true);

        palabras = getResources().getStringArray(R.array.palabras);
        linearLayout=findViewById(R.id.linearLayout);
        random = new Random();

        elegirPalabra();

        startTime = System.currentTimeMillis();

        mensaje = findViewById(R.id.mensaje);
        mensaje.setVisibility(View.INVISIBLE);

        cabeza = findViewById(R.id.cabeza);
        torso = findViewById(R.id.torso);
        brazoDer = findViewById(R.id.brazoDere);
        brazoIz = findViewById(R.id.brazoIz);
        piernaIz = findViewById(R.id.piernaIz);
        piernaDer = findViewById(R.id.piernaDer);

        cabeza.setVisibility(View.INVISIBLE);
        torso.setVisibility(View.INVISIBLE);
        brazoDer.setVisibility(View.INVISIBLE);
        brazoIz.setVisibility(View.INVISIBLE);
        piernaIz.setVisibility(View.INVISIBLE);
        piernaDer.setVisibility(View.INVISIBLE);


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

    }


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    Intent resultData = o.getData();
                    String nombre = resultData.getStringExtra("nombreUsuario");
                    crearNuevoJuego();
                }

            }
    );
}