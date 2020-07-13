package com.connor.demo.designpattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.connor.demo.R;

/**
 * 建造者模式
 * 对一个复杂对象得构建与它的表示分离，使得同样的构建过程可以创建不同表示的意图。
 * 建造者模式的好处就是是的建造代码与表示代码分离，由于建造者隐藏了该产品如何组装的。
 * 如果需要改变一个产品的内部表示，只需要再定义一个具体的建造者就可以了
 *
 * 使用场景：
 * 需要生成的对象具有复杂的内部结构，并且生成的对象内部属性本身相互依赖。
 *
 * 与工厂模式区别：建造者模式更加关注与零件装配的顺序。工厂模式内部是对对象的选择，建造者模式是对对象的组装。
 */
public class Builder {
    public static void main(String[] args) {
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