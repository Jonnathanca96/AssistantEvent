package com.jonhenry.userio.assistantevent.Datos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jonhenry.userio.assistantevent.Logica.MainActivity;
import com.jonhenry.userio.assistantevent.R;


public class LoginDatos extends AppCompatActivity {

    public LoginButton loginButton;
    public CallbackManager callbackManager;
    private  Profile usuario;
    private String foto;
    private String nombre;
    private String datos;
    private TextView informacion;
    private String token;

    /**
     * Comprueba si el usuario ha iniciado sesión en Facebook y el
     * token de acceso está activo
     *
     * @return
     */
    public static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return (accessToken != null) && (!accessToken.isExpired());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_datos);

        FacebookSdk.sdkInitialize(getApplicationContext());

        //Inicializar Facebook SDK;
        //Debe hacer el proceso de ingreso desde cero el objeto creado
        callbackManager = CallbackManager.Factory.create();
        // Establecer las devoluciones de llamada
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile","email");
        // Registrar las devoluciones de llamada
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
                {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                           /*Obtener los datos del usuario*/

                            usuario = Profile.getCurrentProfile();

                            nombre = usuario.getName();

                            token = loginResult.getAccessToken().getToken();

                            Uri urlImagen = usuario.getProfilePictureUri(150, 150);

                            foto=urlImagen.toString();

                            makeToast("Bienvenido: "+nombre);

                            AccesoUsuario usuario = new AccesoUsuario(callbackManager, loginButton);

                            goMainActivity();//para ir al menu de las opciones */

                        }

                        @Override
                        public void onCancel() {
                            makeToast("Login Cancelado.");
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            makeToast("Login error. No hay Acceso a Internet!.");
                        }
                });
    }

    /**
     * datos de interés en el gestor de devolución de llamada
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * creamos los toast
     * @param text
     */
    private void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //los logs de 'instalar' y 'aplicación activa' App Eventos.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs de'app desactivada' App Eventos.
        AppEventsLogger.deactivateApp(this);
    }

    public void goMainActivity(){
        Intent intent =new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("NOMBRE",nombre);
        intent.putExtra("FOTO", foto);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
    }




}
