package com.jonhenry.userio.assistantevent.Presentacion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = AccesoUsuario.getCallbackManager();
        loginButton = AccesoUsuario.getLoginButton();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {

            }
        });

        v = inflater.inflate(R.layout.fragment_fragment_web_view, container, false);
        webView = (WebView) v.findViewById(R.id.MwebView);
        webView.loadUrl("https://www.facebook.com/events/upcoming");

        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        //webView.setWebViewClient(new WebViewClient());

        return v;
    }



}


