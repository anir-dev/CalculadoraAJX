
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


    int auxTeclasOperandos;
    int auxTeclasOperaciones;
    CheckBox checkBoxSumar,checkBoxRestar,checkBoxMultiplicar,checkBoxDividir,checkBoxPotencia,checkBoxRaizCuadrada;
    Boolean estadoCheckBoxSumar,estadoCheckBoxRestar,estadoCheckBoxMultiplicar,estadoCheckBoxDividir,estadoCheckBoxPotencia,estadoCheckBoxRaizCuadrada;
    Button btnConfirmar;
    String [] teclasOperaciones;
    /*
     final Button[] ArrayTeclasOperaciones = {
            findViewById(R.id.btnBorrar),
             findViewById(R.id.btnRaizCuadrada),
             findViewById(R.id.btnPotencia),
             findViewById(R.id.btnMultiplicar),
             findViewById(R.id.btnRestar),
             findViewById(R.id.btnSumar),
             findViewById(R.id.btnIgual),
    };*/


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
        //para cargar los spinner en la actividad



        String [] teclasOperandos;

        /*final Button[] ArrayteclasOperandos = {
                findViewById(R.id.btn0),
                findViewById(R.id.btn1),
                findViewById(R.id.btn2),
                findViewById(R.id.btn3),
                findViewById(R.id.btn4),
                findViewById(R.id.btn5),
                findViewById(R.id.btn6),
                findViewById(R.id.btn7),
                findViewById(R.id.btn8),
                findViewById(R.id.btn9),
                findViewById(R.id.btnComa),
        };*/

        teclasOperandos=getResources().getStringArray(R.array.color_TeclasOperandos);
        teclasOperaciones=getResources().getStringArray(R.array.color_TeclasOperaciones);

        final Spinner spinnerTeclasOperandos=(Spinner)findViewById(R.id.spinnerColorTeclasOperandos);
        Spinner spinnerTeclasOperaciones=(Spinner)findViewById(R.id.spinnerColorTeclasOperaciones);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, teclasOperandos);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,teclasOperaciones);


        /*final Button botonesOperandos[]=new Button[11];
        botonesOperandos[0]=(Button) findViewById(R.id.btn0);
        botonesOperandos[1]=(Button) findViewById(R.id.btn1);*/


        spinnerTeclasOperandos.setAdapter(adapter);
        //spinner Teclas Operandos
        spinnerTeclasOperandos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int indice = arg0.getSelectedItemPosition();
                Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                auxTeclasOperandos=indice;
                //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                switch (indice){
                    case 0:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        /*for(int i=0;i<ArrayteclasOperandos.length-1;i++){
                            ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.greyButton));
                        }*/
                        break;

                    //ArrayteclasOperandos[indice].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        /*
                        botonesOperandos[indice].setBackgroundColor();
                         */
                    case 1:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        //for(int i=0;i<ArrayteclasOperandos.length-1;i++){
                        //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                        //)}
                        break;
                    //ArrayteclasOperandos[indice].setBackgroundColor(getResources().getColor(R.color.color_teclasOperaciones1));
                    case 2:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();

                        break;
                    case 3:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();

                        break;
                    case 4:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();

                        break;
                    case 5:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();

                        break;
                    case 6:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();

                        break;
                }
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
                switch (indice){
                    case 0:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();

                        break;

                    //ArrayteclasOperandos[indice].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        /*
                        botonesOperandos[indice].setBackgroundColor();
                        for(int i=0;i<ArrayteclasOperandos.length-1;i++){
                            ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        }
                        */
                    case 1:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        //ArrayteclasOperandos[arg2].setBackgroundColor(getResources().getColor(R.color.color_teclasOperaciones1));

                        break;

                    case 2:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        //Toast.makeText(arg0.getContext(),(String) arg0.getItemAtPosition(indice), Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //este metodo se implementa cuando el usuario no selecciona nada
                auxTeclasOperaciones=0;
            }
        });

        /*btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estadoCheckBoxSumar=checkBoxSumar.isChecked()?true:false;
                estadoCheckBoxRestar=checkBoxRestar.isChecked()?true:false;
                estadoCheckBoxMultiplicar=checkBoxMultiplicar.isChecked()?true:false;
                estadoCheckBoxDividir=checkBoxDividir.isChecked()?true:false;
                estadoCheckBoxPotencia=checkBoxPotencia.isChecked()?true:false;
                estadoCheckBoxRaizCuadrada=checkBoxRaizCuadrada.isChecked()?true:false;


            }
        });*/
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
        finishActivity(MainActivity.class.getModifiers());
        Intent enviar=new Intent(this,MainActivity.class);
        enviar.putExtras(parametros);
        startActivity(enviar);
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

    /*public void comprobarCheckBox(View v){
        estadoCheckBox=checkTeclasOperaciones.isChecked()?true:false;
    }*/
    /*
    Los dos siguientes metodos sirven para cargar las opciones del menu en el activity mai*/


}