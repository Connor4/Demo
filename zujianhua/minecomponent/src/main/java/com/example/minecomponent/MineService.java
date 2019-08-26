package com.example.minecomponent;

import android.content.Context;
import android.content.Intent;

import com.example.componentlib.IMineInterface;

public class MineService implements IMineInterface {
    @Override
    public void launchActivity(Context context) {
        Intent intent = new Intent(context, MineActivity.class);
        context.startActivity(intent);
    }
}
