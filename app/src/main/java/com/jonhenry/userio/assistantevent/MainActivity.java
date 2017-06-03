package com.jonhenry.userio.assistantevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jonhenry.userio.assistantevent.Datos.LoginDatos;
import com.jonhenry.userio.assistantevent.Presentacion.MenuAplication;

public class MainActivity extends AppCompatActivity {
    private static boolean fueInicio = false;
    private static boolean salida = false;
    Toast toast;
    private Bundle usuario;
    private boolean quiereSalir=false;
    private  String nombre=null;
    private  String urlFoto=null;
    private String valor;

    private String token;

    private String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        valor=String.valueOf(nombre);

        if(fueInicio){//Para poder saber si ya tiene la informacin que se necesita

            usuario=getIntent().getExtras();

            nombre=usuario.getString("NOMBRE");

            urlFoto=usuario.getString("FOTO");

            token = usuario.getString("TOKEN");

            /*Uri uriUrl = Uri.parse("https://www.facebook.com/events/upcoming");
            Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(intent);*/

        }


        if(nombre==null&&urlFoto==null)
        {
            fueInicio=true;

            goLoginDatos();

        }else{

            fueInicio=false;

        }

        if(salida){//para poder saber si el usuario desea salir; false si no quiere y true si es que quiere salir

            salida=false;

            gomenu();

        }else{

            nombre=null;

            urlFoto=null;

            salida=true;


            goLoginDatos();
        }

    }

    private void goLoginDatos() {//Para poder ir a la actividad que registra el inicio de sesion
        Intent intent =new Intent(this, LoginDatos.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    private void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }


    private void gomenu() {
        Intent intent = new Intent(this,MenuAplication.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("NOMBRE",nombre);
        intent.putExtra("FOTO",urlFoto);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

  /*  public void onSaveInstanceState(Bundle estado){//Para guardar la informacion de un usuario
        //antes de ser puesta en on Pause
        estado.putString("nombre",nombre);
        estado.putString("foto",urlFoto);
        super.onSaveInstanceState(estado);
    }

    public void onRestoreInstanceState(Bundle estado){//Para poder recuperar la informacion
        //que se guardo de OnSaveInstanceState
        super.onRestoreInstanceState(estado);

        nombre=estado.getString("nombre");

        urlFoto=estado.getString("foto");

    }*/

}
