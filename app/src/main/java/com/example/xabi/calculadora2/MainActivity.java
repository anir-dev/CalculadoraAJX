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

        import java.util.Objects;

public class MainActivity extends AppCompatActivity {
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

        //se enlazan los atributos con los del diseño
        texto= (TextView) findViewById(R.id.textView);
        btnBorrar=(Button) findViewById(R.id.btnBorrar);
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
}
