package com.example.remedialahorcado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity {

    private String[] palabras;
    private Random random;
    private String actual;
    private TextView[] charViews;
    private LinearLayout palabraLayout;
    private LetrasAdaptador adaptador;
    private GridView gridView;
    private int numcor;
    private int numchar;
    private ImageView[]partes;
    private  int tamaño=6;
    private int parteactual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        palabras=getResources().getStringArray(R.array.palabras);
        palabraLayout=findViewById(R.id.palabras);
        gridView=findViewById(R.id.letras);
        random=new Random();
        partes= new ImageView[tamaño];
        partes[0] = findViewById(R.id.cabeza);
        partes[1] = findViewById(R.id.cuerpo);
        partes[2] = findViewById(R.id.brazoizq);
        partes[3] = findViewById(R.id.brazoder);
        partes[4] = findViewById(R.id.piernaizq);
        partes[5] = findViewById(R.id.piernader);
        iniciarjuego();
    }

    private void iniciarjuego(){
        String newpalabra = palabras[random.nextInt(palabras.length)];
        while (newpalabra.equals(actual))newpalabra=palabras[random.nextInt(palabras.length)];
        actual=newpalabra;
        charViews = new TextView[actual.length()];
        palabraLayout.removeAllViews();
        for (int i = 0; i<actual.length(); i++){
            charViews[i]=new TextView(this);
            charViews[i].setText(""+actual.charAt(i));
            charViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charViews[i].setGravity(Gravity.CENTER);
            charViews[i].setTextColor(Color.WHITE);
            charViews[i].setBackgroundResource(R.drawable.letter_bg);
            palabraLayout.addView(charViews[i]);
        }
        adaptador = new LetrasAdaptador(this);
        gridView.setAdapter(adaptador);
        numcor=0;
        parteactual =0;
        numchar=actual.length();
        for (int i = 0;i<tamaño;i++){
            partes[i].setVisibility(View.INVISIBLE);
        }
    }
    public void letraspres(View view){
        String letra=((TextView)view).getText().toString();
        char letraschar=letra.charAt(0);
        view.setEnabled(false);
        boolean correcto=false;
        for (int i = 0;i<actual.length();i++){
            if (actual.charAt(i)==letraschar){
                correcto=true;
                numcor++;
                charViews[i].setTextColor(Color.BLACK);
            }
        }
        if (correcto){
            if (numcor==numchar){
                deshabilitarbtns();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Haz Ganado");
                builder.setMessage("Lo Lograste\n\n La palabra era\n\n " + actual);
                builder.setPositiveButton("De Nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JuegoActivity.this.iniciarjuego();
                    }
                });
                builder.setNegativeButton("Salir del juego", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JuegoActivity.this.finish();
                    }
                });
                builder.show();
            }
        }else if (parteactual<tamaño){
            partes[parteactual].setVisibility(View.VISIBLE);
            parteactual++;
        }else{
            deshabilitarbtns();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Haz perdido");
            builder.setMessage("No Atinaste \n\n La palabra era\n\n " + actual);
            builder.setPositiveButton("De Nuevo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    JuegoActivity.this.iniciarjuego();
                }
            });
            builder.setNegativeButton("Salir del juego", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    JuegoActivity.this.finish();
                }
            });
            builder.show();
        }

    }
    public  void deshabilitarbtns(){
        for (int i = 0;i<gridView.getChildCount();i++){
            gridView.getChildAt(i).setEnabled(false);
        }
    }
}
