package com.example.xabi.calculadora2;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.Toast;

/**
 * Created by in2dm3_09 on 31/10/2017.
 */

public class Settings extends AppCompatActivity {
    String [] teclasOperandos;
    private static final int[] ArrayteclasOperandos = {
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
            R.id.btnComa,
    };

    String [] teclasOperaciones;
    private static final int[] ArrayTeclasOperaciones = {
            R.id.btnBorrar,
            R.id.btnRaizCuadrada,
            R.id.btnPotencia,
            R.id.btnMultiplicar,
            R.id.btnRestar,
            R.id.btnSumar,
            R.id.btnIgual,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        //para cargar la toolbar en la actividad
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //para cargar los spinner en la actividad

        teclasOperandos=getResources().getStringArray(R.array.color_TeclasOperandos);
        teclasOperaciones=getResources().getStringArray(R.array.color_TeclasOperaciones);

        final Spinner spinnerTeclasOperandos=(Spinner)findViewById(R.id.spinnerColorTeclasOperandos);
        Spinner spinnerTeclasOperaciones=(Spinner)findViewById(R.id.spinnerColorTeclasOperaciones);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, teclasOperandos);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,teclasOperaciones);

        spinnerTeclasOperandos.setAdapter(adapter);
        //spinner Teclas Operandos
        spinnerTeclasOperandos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int indice = arg0.getSelectedItemPosition();
                switch (indice){
                    case 0:

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //este metodo se implementa cuando el usuario no selecciona nada

            }
        });
        //spinner teclas operaciones
        spinnerTeclasOperaciones.setAdapter(adapter2);
        spinnerTeclasOperaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int indice = arg0.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //este metodo se implementa cuando el usuario no selecciona nada
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Has seleccionado la opcion atras", Toast.LENGTH_SHORT).show();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    Los dos siguientes metodos sirven para cargar las opciones del menu en el activity mai*/


}