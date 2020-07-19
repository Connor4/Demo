package com.connor.demo.designpattern;

/**
 * 代理模式
 *
 * 适用场景：
 * 1.远程代理：为一个对象在不同的地址空间提供局部代表，这样可以隐藏一个对象不存在于不同地址空间的事实。
 * 2.虚拟代理：需要创建开销很大的对象，通过它来存放实例化需要长时间的对象。如下列用例所示
 * 3.安全代理：用来控制真实对象访问时的权限。一般用于对象应该有不同的访问权限。
 * 4.智能指引：调用真实的对象时，代理处理另外一些事。如计算真实对象的引用次数，当没有引用的时候释放对象；就是额外给真实对象处理一些失误。
 */
public class Proxy {

    public static void main(String[] args) {
        ProxyClass proxyClass = new ProxyClass();
        proxyClass.request();

        // 具体用例
        Image image = new ProxyImage("test_10mb.jpg");
        // 图像将从磁盘加载
        image.display();
        // 图像不需要从磁盘加载
        image.display();
    }

}

abstract class ProxySubject {
    abstract void request();
}

class RealSubject extends ProxySubject {
    @Override
    void request() {
        System.out.println("真实的请求");
    }
}

class ProxyClass extends ProxySubject {
    RealSubject realSubject;

    @Override
    void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();
    }
}

// 具体用例
interface Image {
    void display();
}

class RealImage implements Image {
    private String mFileName;

    public RealImage(String fileName) {
        this.mFileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("load image from  disk");
    }

    @Override
    public void display() {
        System.out.println("display image");
    }
}

class ProxyImage implements Image {
    private RealImage mRealImage;
    private String mFileName;

    public ProxyImage(String mFileName) {
        this.mFileName = mFileName;
    }

    @Override
    public void display() {
        if (mRealImage == null) {
            mRealImage = new RealImage(mFileName);
        }
        mRealImage.display();
    }
}
