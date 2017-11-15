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

public class MainActivity extends AppCompatActivity {
    private static int REQUEST_CODE1=1;
    Boolean valorCheckBoxSumar=true;
    Boolean valorCheckBoxRestar=true;
    Boolean valorCheckBoxMultiplicar=true;
    Boolean valorCheckBoxDividir=true;
    Boolean valorCheckBoxPotencia=true;
    Boolean valorCheckBoxRaizCuadrada=true;
    Bundle parametros=new Bundle();
    Button[]  arrayTeclasOperaciones = {};
    Button[] arrayteclasOperandos = {};

    //se crean las variables para enlazarlas con los los del diseño
    private Button btnOpcion,btnBorrar;
    private static String s="";
    private TextView texto;
    String textoGuardado;
    protected String operador,op1, op2;
    protected double operando1, operando2, resultado;
    protected boolean terminado=false,comprobado;
    private static boolean listo=false; //Marca si ya se ha metido un operador o no
    protected TextView pantalla;
    int valorTeclasOperandos=0;
    int valorTeclasOperaciones=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recibir los datos de la activity settings
        valorCheckBoxSumar = true;
        valorCheckBoxRestar=true;
        valorCheckBoxMultiplicar=true;
        valorCheckBoxDividir=true;
        valorCheckBoxPotencia=true;
        valorCheckBoxRaizCuadrada=true;



         arrayTeclasOperaciones= new Button[]{findViewById(R.id.btnBorrar),
                findViewById(R.id.btnRaizCuadrada),
                findViewById(R.id.btnPotencia),
                findViewById(R.id.btnDividir),
                findViewById(R.id.btnMultiplicar),
                findViewById(R.id.btnRestar),
                findViewById(R.id.btnSumar),
                findViewById(R.id.btnIgual),};
        //habilitarDesabilitarOperacionesCalculo();

        //cargar los botones para el cambio de configuracion

        arrayteclasOperandos =new Button[] {
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
                //this.finish();
                //en lugar de finish() poner finishAffinity() para cerrar la aplicacion
                finishAffinity();
                Toast.makeText(this, "Has seleccionado la opcion salir", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                //quiza haya que configurar un finish() por aqui
                //this.finish();
                btnSettingsPulsado();
                break;
        }

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
                } else {
                    op2 = numeros.substring(limit + 2);
                }
            }
            if(operador.equalsIgnoreCase("√")) {
                op1 = "0";
                op2 = numeros.substring(limit + 1);
            }

            comprobarcoma(op1,op2);

            operando1=Double.parseDouble(op1); //Pasar a double el  operando
            operando2=Double.parseDouble(op2);

            switch(operador){  //Se realizan las operaciones dependiendo del tipo.
                case "+": resultado=operando1+operando2;  break;
                case "-": resultado=operando1-operando2; break;
                case "X": resultado=operando1*operando2; break;
                case "÷":
                    if((int)operando1==0 || (int)operando2==0) //Para controlar la division entre 0
                        resultado=0;
                    else
                        resultado=operando1/operando2;
                    break;
                case "X^":

                    resultado = Math.pow(operando1, operando2);
                    break;

                case "√":
                    resultado=Math.sqrt(operando2);
                    break;
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
            comprobado=false;
        }

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
            String aux;
            aux=(String) texto.getText().toString();
            if (aux.indexOf("-")==0 && comprobado==false){
                listo=false;
                comprobado=true;
            }
        }
        texto.setText(texto.getText() + n);
        Toast.makeText(this, texto.getText(), Toast.LENGTH_SHORT).show();
        return "0";
    }

    public void limpiar(){
        operando1=0;
        operando2=0;
        op1="";
        op2="";
        s="";
        resultado=0;
        texto.setText("");
        terminado=false;
    }
    public void comprobarcoma(String o1,String o2){

        String c=",";
        String p=".";

        if(o1.contains(c)){
            op1=o1.replace(c,p);
        }
        if(o2.contains(c)){
            op2=o2.replace(c,p);
        }
    }


    public void btnSettingsPulsado(){
        Toast.makeText(this, "Seleccione la configuracion requerida", Toast.LENGTH_SHORT).show();
        Intent a=new Intent(MainActivity.this,Settings.class);
        Bundle baul = new Bundle();
        baul.putBoolean("suma",valorCheckBoxSumar);
        baul.putBoolean("resta",valorCheckBoxRestar);
        baul.putBoolean("multiplicar",valorCheckBoxMultiplicar);
        baul.putBoolean("dividir",valorCheckBoxDividir);
        baul.putBoolean("potencia",valorCheckBoxPotencia);
        baul.putBoolean("raiz",valorCheckBoxRaizCuadrada);
        baul.putInt("valorTeclasOperandos",valorTeclasOperandos);
        baul.putInt("valorTeclasOperaciones",valorTeclasOperaciones);
        a.putExtra("baul",baul);
        startActivityForResult(a,REQUEST_CODE1);

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
        savedInstanceState.putBoolean("suma",valorCheckBoxSumar);
        savedInstanceState.putBoolean("resta",valorCheckBoxRestar);
        savedInstanceState.putBoolean("multiplicar",valorCheckBoxMultiplicar);
        savedInstanceState.putBoolean("dividir",valorCheckBoxDividir);
        savedInstanceState.putBoolean("potencia",valorCheckBoxPotencia);
        savedInstanceState.putBoolean("raiz",valorCheckBoxRaizCuadrada);
        savedInstanceState.putInt("valorTeclasOperandos",valorTeclasOperandos);
        savedInstanceState.putInt("valorTeclasOperaciones",valorTeclasOperaciones);

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
        valorCheckBoxSumar=savedInstanceState.getBoolean("suma");
        valorCheckBoxRestar=savedInstanceState.getBoolean("resta");
        valorCheckBoxMultiplicar=savedInstanceState.getBoolean("multiplicar");
        valorCheckBoxDividir=savedInstanceState.getBoolean("dividir");
        valorCheckBoxPotencia=savedInstanceState.getBoolean("potencia");
        valorCheckBoxRaizCuadrada=savedInstanceState.getBoolean("raiz");
        valorTeclasOperaciones=savedInstanceState.getInt("valorTeclasOperaciones");
        valorTeclasOperandos=savedInstanceState.getInt("valorTeclasOperandos");
        habilitarDesabilitarOperacionesCalculo();
        cambiarColores();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        parametros = this.getIntent().getExtras();
        if (requestCode==REQUEST_CODE1){
            if(resultCode==RESULT_OK){
                parametros=data.getBundleExtra("datos");
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
                habilitarDesabilitarOperacionesCalculo();
                cambiarColores();
            }

        }
    }
    public void habilitarDesabilitarOperacionesCalculo(){
        if(valorCheckBoxSumar==false){
             arrayTeclasOperaciones[6].setEnabled(false);
        }else{
             arrayTeclasOperaciones[6].setEnabled(true);
        }
        if(!valorCheckBoxRestar){
             arrayTeclasOperaciones[5].setEnabled(false);
        }else{
             arrayTeclasOperaciones[5].setEnabled(true);
        }
        if(!valorCheckBoxMultiplicar){
             arrayTeclasOperaciones[4].setEnabled(false);
        }else{
             arrayTeclasOperaciones[4].setEnabled(true);
        }
        if(!valorCheckBoxDividir){
             arrayTeclasOperaciones[3].setEnabled(false);
        }else{
             arrayTeclasOperaciones[3].setEnabled(true);
        }
        if(!valorCheckBoxPotencia){
             arrayTeclasOperaciones[2].setEnabled(false);
        }else{
             arrayTeclasOperaciones[2].setEnabled(true);
        }
        if(!valorCheckBoxRaizCuadrada){
             arrayTeclasOperaciones[1].setEnabled(false);
        }else{
             arrayTeclasOperaciones[1].setEnabled(true);
        }
    }


    public  void cambiarColores(){


        //switch para cambiar el color de las teclas de los operandos
        switch(valorTeclasOperandos){
            case 0:
                for(int i = 0; i< arrayteclasOperandos.length; i++){
                    arrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.boton_redondo));
                }
                break;
            case 1:
                for(int i = 0; i< arrayteclasOperandos.length; i++){
                    arrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_rojo));
                }
                break;
            case 2:
                for(int i = 0; i< arrayteclasOperandos.length; i++){
                    arrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_morado));
                }
                break;
            case 3:
                for(int i = 0; i< arrayteclasOperandos.length; i++){
                    arrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_azuloscuro));
                }
                break;
            case 4:
                for(int i = 0; i< arrayteclasOperandos.length; i++){
                    arrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_azulclaro));
                }
                break;
            case 5:
                for(int i = 0; i< arrayteclasOperandos.length; i++){
                    arrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_verdeoscuro));
                }
                break;
            case 6:
                for(int i = 0; i< arrayteclasOperandos.length; i++){
                    arrayteclasOperandos[i].setBackground(getResources().getDrawable(R.drawable.btn_amarillo));
                }
                break;
        }
        //switch para cambiar los colores de las teclas de operaciones
        switch(valorTeclasOperaciones){
            case 0:
                for(int i=0;i< arrayTeclasOperaciones.length;i++){
                     arrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.boton_redondo));
                }
                break;
            case 1:
                for(int i=0;i< arrayTeclasOperaciones.length;i++){
                     arrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_rosa));
                }
                break;
            case 2:
                for(int i=0;i< arrayTeclasOperaciones.length;i++){
                     arrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_marron));
                }
                break;
            case 3:
                for(int i=0;i< arrayTeclasOperaciones.length;i++){
                     arrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_naranja));
                }
                break;
            case 4:
                for(int i=0;i< arrayTeclasOperaciones.length;i++){
                     arrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_verdeclaro));
                }
                break;
            case 5:
                for(int i=0;i< arrayTeclasOperaciones.length;i++){
                     arrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_azulturquesa));
                }
                break;
            case 6:
                for(int i=0;i< arrayTeclasOperaciones.length;i++){
                     arrayTeclasOperaciones[i].setBackground(getResources().getDrawable(R.drawable.btn_lila));
                }
                break;
        }
    }
}