package com.jonhenry.userio.assistantevent.Presentacion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jonhenry.userio.assistantevent.Datos.AccesoUsuario;
import com.jonhenry.userio.assistantevent.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWebView extends Fragment {
    private WebView webView;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private View v;

    public FragmentWebView() {

        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        FacebookSdk.sdkInitialize(getApplicationContext());
        v = inflater.inflate(R.layout.fragment_fragment_web_view, container, false);
        //Inicializar Facebook SDK;
        //Debe hacer el proceso de ingreso desde cero el objeto creado
        // Establecer las devoluciones de llamada
        callbackManager = AccesoUsuario.getCallbackManager();
        loginButton = AccesoUsuario.getLoginButton();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                           /*Obtener los datos del usuario*/

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {

            }
        });


        webView = (WebView) v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.facebook.com/events");
        /*webView.setWebViewClient(new WebViewClient(){public boolean shouldOverrideUrlLoading(WebView view, String url){
                                         return false;
                                     }
                                 }
        );*/


        return v;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
