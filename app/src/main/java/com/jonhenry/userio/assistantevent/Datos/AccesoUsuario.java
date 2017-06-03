package com.jonhenry.userio.assistantevent.Datos;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Userio on 24/5/2017.
 */


public class AccesoUsuario {
    private static CallbackManager callbackManager;
    private static LoginButton loginButton;

    public AccesoUsuario(CallbackManager callbackManager, LoginButton loginButtonnbutton) {
        this.callbackManager = callbackManager;
        this.loginButton = loginButtonnbutton;
    }

    public static CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public static LoginButton getLoginButton() {
        return loginButton;
    }


}
