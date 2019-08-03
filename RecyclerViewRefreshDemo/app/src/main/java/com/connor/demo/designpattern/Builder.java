package com.connor.demo.designpattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.connor.recyclerviewrefreshdemo.R;

public class Builder {

    class Product {
        private String body;
        private String foot;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getFoot() {
            return foot;
        }

        public void setFoot(String foot) {
            this.foot = foot;
        }
    }

    // 构造抽象类
    abstract class AbsBuilder {
        abstract void buildBody();

        abstract void buildFoot();

        abstract Product getProduct();
    }

    // 具体构造实现类
    class PersonBuilder extends AbsBuilder {
        private Product result = new Product();

        @Override
        void buildBody() {
            result.setBody("body type 1");
        }

        @Override
        void buildFoot() {
            result.setFoot("foot type 1");
        }

        @Override
        Product getProduct() {
            return result;
        }

    }

    // 构造指挥类
    class BuilderDirector {
        private AbsBuilder builder;

        public BuilderDirector(AbsBuilder absBuilder) {
            builder = absBuilder;
        }

        public void buildPerson() {
            builder.buildBody();
            builder.buildFoot();
        }
    }

    public void test() {
        AbsBuilder builder = new PersonBuilder();
        BuilderDirector builderDirector = new BuilderDirector(builder);
        builderDirector.buildPerson();

        Product result = builder.getProduct();
    }

    // android中使用例子
    private void showDialog(Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setIcon(R.drawable.progressbar_refresh)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).create();
        dialog.show();
    }
}
