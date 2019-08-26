package com.example.logincomponent;

import android.content.Context;
import android.content.Intent;

import com.example.componentlib.ILoginInterface;

public class LoginService implements ILoginInterface {
    @Override
    public void launchActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
