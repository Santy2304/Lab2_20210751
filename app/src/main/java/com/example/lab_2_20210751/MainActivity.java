package com.example.lab_2_20210751;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView teleGame = findViewById(R.id.textView5);

        Button jugar = findViewById(R.id.button);
        jugar.setEnabled(false);

        registerForContextMenu(teleGame);

        EditText nombre = findViewById(R.id.editTextText2);

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                jugar.setEnabled(charSequence.length() > 0);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        jugar.setOnClickListener(view -> {
            Intent intent = new Intent(this, Juego.class);
            intent.putExtra("nombre", nombre.getText().toString());
            Log.d("NOmbre", "onCreate: "+nombre.getText().toString());
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Tomado de la ppt de la clase
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);
    }

// Tomado de la ppt de la clase
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView teleGame = findViewById(R.id.textView5);

        if (item.getItemId() == R.id.verde){
            teleGame.setTextColor(Color.GREEN);
            return true;

        } else if (item.getItemId()==R.id.rojo) {
            teleGame.setTextColor(Color.RED);
            return true;

        } else if (item.getItemId()==R.id.morado) {
            teleGame.setTextColor(Color.MAGENTA);
            return true;
        }else{
            return super.onContextItemSelected(item);
        }

    }


}