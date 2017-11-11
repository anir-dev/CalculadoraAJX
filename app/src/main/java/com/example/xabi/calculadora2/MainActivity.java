package com.example.xabi.calculadora2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    Boolean valorCheckBoxSumar = false;
    Boolean valorCheckBoxRestar=false;
    Boolean valorCheckBoxMultiplicar=false;
    Boolean valorCheckBoxDividir=false;
    Boolean valorCheckBoxPotencia=false;
    Boolean valorCheckBoxRaizCuadrada=false;

    Button[] ArrayTeclasOperaciones = {};

    //se crean las variables para enlazarlas con los los del diseño
    private Button btnOpcion,btnBorrar;
    private static String s="";
    private TextView texto;
    protected String operador,op1, op2;
    protected double operando1, operando2, resultado;
    protected boolean terminado=false;
    private static boolean listo=false; //Marca si ya se ha metido un operador o no
    protected TextView pantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recibir los datos de la activity settings
        int valorTeclasOperandos=0;
        int valorTeclasOperaciones=0;

        Bundle parametros = this.getIntent().getExtras();
        if(parametros!=null){
            valorCheckBoxSumar=parametros.getBoolean("valorCheckBoxSumar");
            valorCheckBoxRestar=parametros.getBoolean("valorCheckBoxRestar");
            valorCheckBoxMultiplicar=parametros.getBoolean("valorCheckBoxMultiplicar");
            valorCheckBoxDividir=parametros.getBoolean("valorCheckBoxDividir");
            valorCheckBoxPotencia=parametros.getBoolean("valorCheckBoxPotencia");
            valorCheckBoxRaizCuadrada=parametros.getBoolean("valorCheckBoxRaizCuadrada");
            valorTeclasOperaciones=parametros.getInt("valorTeclasOperaciones");
            valorTeclasOperandos=parametros.getInt("valorTeclasOperandos");
        }
        ArrayTeclasOperaciones= new Button[]{findViewById(R.id.btnBorrar),
                findViewById(R.id.btnRaizCuadrada),
                findViewById(R.id.btnPotencia),
                findViewById(R.id.btnDividir),
                findViewById(R.id.btnMultiplicar),
                findViewById(R.id.btnRestar),
                findViewById(R.id.btnSumar),
                findViewById(R.id.btnIgual),};
        habilitarDesabilitarOperacionesCalculo();

        //cargar los botones para el cambio de configuracion
        //String [] teclasOperandos;
        /*final Button botonesOperandos[]=new Button[11];
        botonesOperandos[0]=(Button) findViewById(R.id.btn0);
        botonesOperandos[1]=(Button) findViewById(R.id.btn1);*/
        final Button[] ArrayteclasOperandos = {
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
        };


        //teclasOperandos=getResources().getStringArray(R.array.color_TeclasOperandos);
        /*if(valorTeclasOperandos==1){
            botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
        }*/
        //switch para cambiar el color de las teclas de los operandos
        switch(valorTeclasOperandos){
            case 0:
                for(int i=0;i<ArrayteclasOperandos.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.boton_redondo));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 1:
                for(int i=0;i<ArrayteclasOperandos.length;i++){
                            //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_rojo));
                        }
                        //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 2:
                for(int i=0;i<ArrayteclasOperandos.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_morado));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 3:
                for(int i=0;i<ArrayteclasOperandos.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_azuloscuro));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 4:
                for(int i=0;i<ArrayteclasOperandos.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_azulclaro));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 5:
                for(int i=0;i<ArrayteclasOperandos.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_verdeoscuro));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 6:
                for(int i=0;i<ArrayteclasOperandos.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_amarillo));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
        }
        //switch para cambiar los colores de las teclas de operaciones
        switch(valorTeclasOperaciones){
            case 0:
                for(int i=0;i<ArrayTeclasOperaciones.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.boton_redondo));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 1:
                for(int i=0;i<ArrayTeclasOperaciones.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_rosa));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 2:
                for(int i=0;i<ArrayTeclasOperaciones.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_marron));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 3:
                for(int i=0;i<ArrayTeclasOperaciones.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_naranja));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 4:
                for(int i=0;i<ArrayTeclasOperaciones.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_verdeclaro));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 5:
                for(int i=0;i<ArrayTeclasOperaciones.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_azulturquesa));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
            case 6:
                for(int i=0;i<ArrayTeclasOperaciones.length;i++){
                    //ArrayteclasOperandos[i].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                    ArrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_lila));
                }
                //botonesOperandos[0].setBackgroundColor(getResources().getColor(R.color.color_teclasOperandos1));
                break;
        }

        //se enlazan los atributos con los del diseño
        texto= (TextView) findViewById(R.id.textView);
        btnBorrar=(Button) findViewById(R.id.btnBorrar);
        operador = "";
        op1 = "";
        op2 = "";
        resultado = 0;
        operando1 = 0;
        operando2 = 0;
        //se le asigna una doble funcion al boton de borrar
        //OnClick significa cuando se pulse el boton
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(texto.getText().length()!=0){
                    texto.setText(texto.getText().toString().substring(0,texto.getText().length()-1));
                }
            }
        });
        //OnLongClick significa cuando se mantenga pulsado el boton
        btnBorrar.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                //debe retornar un valor boolean
                texto.setText("");
                return true;
            }
        });

        //para cargar la toolbar en la actividad
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //Los dos siguientes metodos sirven para cargar las opciones del menu en el activity main
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater=getMenuInflater();
        //inflater.inflate(R.menu.menu,menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }
    //Este metodo sirve para obtener los resultados seleccionados por el usuario y asignarles una funcion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                Toast.makeText(this, "Has seleccionado la opcion salir", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                //quiza haya que configurar un finish() por aqui
                //this.finish();
                btnSettingsPulsado();
                break;
        }
        /*int id=item.getItemId();
        if(id==R.id.action_settings){
            Intent a=new Intent(MainActivity.this,Settings.class);
            startActivity(a);
        }*/

        return super.onOptionsItemSelected(item);

    }
    //Metodos al pulsar los botones:
    public void pulsaNumero(View v){
        int id;
        Button n;
        String s="";
        id=v.getId();
        n=(Button) findViewById(id);
        s= (String) n.getText();
        agregarCifra(s);
    }

    public void pulsaOperador(View v){
        if(listo==false) {
            int id;
            Button operador;
            id=v.getId();
            operador=(Button) findViewById(id);
            s= (String) operador.getText();
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            listo=true;
            agregarCifra(s);
        }else{
            int id;
            Button operador;
            id=v.getId();
            operador=(Button) findViewById(id);
            String z=(String) operador.getText();
            String u=texto.getText().toString(); //34+
            String p=u.replace(s,z);
            texto.setText(p);
            s=z;
            //terminado=false;
        }

    }
    public void pulsaComa(View v) {
        int id;
        Button decimal;
        String s="";
        id=v.getId();
        decimal=(Button) findViewById(id);
        s= (String) decimal.getText();
        agregarCifra(s);
    }

    public void pulsaIgual(View v) {
        operador = s;

        String numeros = texto.getText().toString(); //Metemos en un String lo mostrado en pantalla

        int limit = numeros.indexOf(s); //Coje la posicion de donde esta el operador
        if (numeros.isEmpty() || terminado==true){ //Si le dan al igual sin nada en pantalla no hara nada.

        }else{


            if (limit == 0) { //Si el limite es = 0 quiere decir que no tiene ningun operador, por lo cual el numero a mostrar es el que ha metido Ejemplo: Mete un 3 -> = Muestra el 3
                op1 = numeros;
                op2 = "0";

            } else { //Si limite es mayor a 0 hace la operacion
                op1 = numeros.substring(0, limit);

                if (!s.equals("X^")) { //Tenemos que comprobar si es la potencia o no.
                    op2 = numeros.substring(limit + 1);
                }
                else{
                    op2 = numeros.substring(limit + 2);
                }
            }


            operando1=Double.parseDouble(op1); //Pasar a double el  operando
            operando2=Double.parseDouble(op2);

            switch(operador){  //Se realizan las operaciones dependiendo del tipo.
                case "+":  resultado=operando1+operando2;  break;
                case "-": resultado=operando1-operando2; break;
                case "X": resultado=operando1*operando2; break;
                case "÷":
                    if((int)operando1==0 || (int)operando2==0) //Para controlar la division entre 0
                        resultado=0;
                    else
                        resultado=operando1/operando2;
                    break;
                case "X^":
                    resultado=operando1*(operando1*operando2) ;break;

                default:
                    operando1=Double.parseDouble(op1); //Pasar a double el primer operando
                    resultado=operando1;
                    break;
            }
            String res=String.valueOf(resultado);
            //Falta comprobar si contiene coma
            texto.setText(res);
            listo=false;
            terminado=true;
        }

    }

    public void btnSettingsPulsado(){
        Toast.makeText(this, "Seleccione la configuracion requerida", Toast.LENGTH_SHORT).show();
        Intent a=new Intent(MainActivity.this,Settings.class);
        startActivity(a);
        //Toast.makeText(this, "aaaaa", Toast.LENGTH_SHORT).show();
    }

    public String agregarCifra(String n){ //Para añadir cifras a un operando
        if (terminado==true){
            limpiar();
        }
        terminado=false;
        int tamaño=texto.getText().length();
        if (tamaño>18){
            Toast.makeText(this, "No se pueden añadir mas numeros, limite alcanzado", Toast.LENGTH_SHORT).show();
        }else{
            texto.setText(texto.getText() + n);
        }
        Toast.makeText(this, texto.getText(), Toast.LENGTH_SHORT).show();
        return "0";
    }

    public void limpiar(){
        operando1=0;
        operando2=0;
        op1="";
        op2="";
        s="";
        // operador=op2="";
        resultado=0;
        texto.setText("");
        terminado=false;
    }
    @Override
    //Para guardar la informacion necesaria que se pierde al girar la pantalla
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("pantalla", texto.getText().toString());
        savedInstanceState.putString("operador", operador);
        savedInstanceState.putString("op1", op1);
        savedInstanceState.putString("op2", op2);
        savedInstanceState.putDouble("oper1", operando1);
        savedInstanceState.putDouble("oper2", operando2);
        savedInstanceState.putDouble("resultado", resultado);

    }
    @Override
    //Para recuperar la informacion guardada al girar la pantalla
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        texto.setText(savedInstanceState.getString("pantalla"));
        operador = savedInstanceState.getString("operador");
        op1 = savedInstanceState.getString("op1");
        op2 = savedInstanceState.getString("op2");
        operando1 = savedInstanceState.getDouble("oper1");
        operando2 = savedInstanceState.getDouble("oper2");
        resultado = savedInstanceState.getDouble("resultado");

    }
    public void habilitarDesabilitarOperacionesCalculo(){
        if(valorCheckBoxSumar==true){
            ArrayTeclasOperaciones[6].setEnabled(false);
        }
        if(valorCheckBoxRestar){
            ArrayTeclasOperaciones[5].setEnabled(false);
        }
        if(valorCheckBoxMultiplicar){
            ArrayTeclasOperaciones[4].setEnabled(false);
        }
        if(valorCheckBoxDividir){
            ArrayTeclasOperaciones[3].setEnabled(false);
        }
        if(valorCheckBoxPotencia){
            ArrayTeclasOperaciones[2].setEnabled(false);
        }
        if(valorCheckBoxRaizCuadrada){
            ArrayTeclasOperaciones[1].setEnabled(false);
        }
    }
}