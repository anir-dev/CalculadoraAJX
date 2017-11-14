
package com.example.xabi.calculadora2;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;


public class Settings extends AppCompatActivity {

    private static String DATOS_SETTINGS="datos";
    int auxTeclasOperandos;
    int auxTeclasOperaciones;
    CheckBox checkBoxSumar,checkBoxRestar,checkBoxMultiplicar,checkBoxDividir,checkBoxPotencia,checkBoxRaizCuadrada;
    Boolean estadoCheckBoxSumar,estadoCheckBoxRestar,estadoCheckBoxMultiplicar,estadoCheckBoxDividir,estadoCheckBoxPotencia,estadoCheckBoxRaizCuadrada;
    Button btnConfirmar;
    String [] teclasOperaciones;

    String texto=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        //para cargar la toolbar en la actividad
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        checkBoxSumar=(CheckBox)findViewById(R.id.checkBoxSumar);
        checkBoxRestar=(CheckBox)findViewById(R.id.checkBoxRestar);
        checkBoxMultiplicar=(CheckBox)findViewById(R.id.checkBoxMultiplicar);
        checkBoxDividir=(CheckBox)findViewById(R.id.checkBoxDividir);
        checkBoxPotencia=(CheckBox)findViewById(R.id.checkBoxPotencia);
        checkBoxRaizCuadrada=(CheckBox)findViewById(R.id.checkBoxRaizCuadrada);
        btnConfirmar=(Button)findViewById(R.id.btnConfirmar);

        //valores por defecto para los checkbox
        checkBoxSumar.setChecked(true);
        checkBoxRestar.setChecked(true);
        checkBoxMultiplicar.setChecked(true);
        checkBoxDividir.setChecked(true);
        checkBoxPotencia.setChecked(true);
        checkBoxRaizCuadrada.setChecked(true);



        //para cargar los spinner en la actividad


        String [] teclasOperandos;


        teclasOperandos=getResources().getStringArray(R.array.color_TeclasOperandos);
        teclasOperaciones=getResources().getStringArray(R.array.color_TeclasOperaciones);

        final Spinner spinnerTeclasOperandos=(Spinner)findViewById(R.id.spinnerColorTeclasOperandos);
        Spinner spinnerTeclasOperaciones=(Spinner)findViewById(R.id.spinnerColorTeclasOperaciones);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, teclasOperandos);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,teclasOperaciones);


        spinnerTeclasOperandos.setAdapter(adapter);
        //spinner Teclas Operandos
        spinnerTeclasOperandos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int indice = arg0.getSelectedItemPosition();
                Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                auxTeclasOperandos=indice;
                //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //este metodo se implementa cuando el usuario no selecciona nada
                auxTeclasOperandos=0;
            }
        });
        //spinner teclas operaciones
        spinnerTeclasOperaciones.setAdapter(adapter2);
        spinnerTeclasOperaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int indice = arg0.getSelectedItemPosition();
                auxTeclasOperaciones=indice;
                Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();

                //jugar con el finish() aqui para que cuando seleccione un item cierra la actividad abierta por defecto
                // con la actividad avierta por defecto me refiero a la primera carga de la actividad MainActivity
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //este metodo se implementa cuando el usuario no selecciona nada
                auxTeclasOperaciones=0;
            }
        });


        Bundle in=new Bundle();
        //boolean b;
        in=this.getIntent().getExtras().getBundle("baul");

        if (in!=null) {
            estadoCheckBoxSumar=in.getBoolean("suma");
            estadoCheckBoxRestar=in.getBoolean("resta");
            estadoCheckBoxMultiplicar=in.getBoolean("multiplicar");
            estadoCheckBoxDividir=in.getBoolean("dividir");
            estadoCheckBoxPotencia=in.getBoolean("potencia");
            estadoCheckBoxRaizCuadrada=in.getBoolean("raiz");
            auxTeclasOperaciones=in.getInt("valorTeclasOperaciones");
            auxTeclasOperandos=in.getInt("valorTeclasOperandos");
            checkBoxSumar.setChecked(estadoCheckBoxSumar);
            checkBoxRestar.setChecked(estadoCheckBoxRestar);
            checkBoxMultiplicar.setChecked(estadoCheckBoxMultiplicar);
            checkBoxDividir.setChecked(estadoCheckBoxDividir);
            checkBoxPotencia.setChecked(estadoCheckBoxPotencia);
            checkBoxRaizCuadrada.setChecked(estadoCheckBoxRaizCuadrada);
            spinnerTeclasOperandos.setSelection(auxTeclasOperandos);
            spinnerTeclasOperaciones.setSelection(auxTeclasOperaciones);
        }
    }
    public void comprobarCheckBox(View view){
        estadoCheckBoxSumar=checkBoxSumar.isChecked()?true:false;
        estadoCheckBoxRestar=checkBoxRestar.isChecked()?true:false;
        estadoCheckBoxMultiplicar=checkBoxMultiplicar.isChecked()?true:false;
        estadoCheckBoxDividir=checkBoxDividir.isChecked()?true:false;
        estadoCheckBoxPotencia=checkBoxPotencia.isChecked()?true:false;
        estadoCheckBoxRaizCuadrada=checkBoxRaizCuadrada.isChecked()?true:false;
        //enviar los datos a la activity settings
        Toast.makeText(this, "Configuracion guardada", Toast.LENGTH_SHORT).show();
        Bundle parametros = new Bundle();
        parametros.putInt("valorTeclasOperaciones",auxTeclasOperaciones);
        parametros.putInt("valorTeclasOperandos",auxTeclasOperandos);
        parametros.putBoolean("valorCheckBoxSumar",estadoCheckBoxSumar);
        parametros.putBoolean("valorCheckBoxRestar",estadoCheckBoxRestar);
        parametros.putBoolean("valorCheckBoxMultiplicar",estadoCheckBoxMultiplicar);
        parametros.putBoolean("valorCheckBoxDividir",estadoCheckBoxDividir);
        parametros.putBoolean("valorCheckBoxPotencia",estadoCheckBoxPotencia);
        parametros.putBoolean("valorCheckBoxRaizCuadrada",estadoCheckBoxRaizCuadrada);
        //parametros.putString("texto",texto);
        //finishActivity(MainActivity.class.getModifiers());
        //Intent enviar=new Intent(this,MainActivity.class);
        Intent anIntent=new Intent();
        anIntent.putExtra(DATOS_SETTINGS,parametros);
        setResult(RESULT_OK,anIntent);
        //enviar.putExtras(parametros);
        //startActivity(enviar);
        //setResult(RESULT_OK,enviar);
        finish();

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Has seleccionado la opcion atras", Toast.LENGTH_SHORT).show();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}