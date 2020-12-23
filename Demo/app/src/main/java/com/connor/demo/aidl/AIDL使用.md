# AIDL使用

Android Interface Define Language(aidl) Android接口定义语言。用于定于客户端与服务均认可 的*编程接口*，以便二者使用进程间通信（IPC）进行互相通信。在Android中，一个进程通常无法访问另一个进程的内存。因此，为进行通信，进程需要将其对象分解成可供操作系统理解的原语（Parcelable），并将其编组为可供操作的对象。编写执行该编组操作的代码较为繁琐，因此Android使用AIDL解决此问题。

## 1.定义aidl接口

在src->main文件夹下创建子目录“aidl”，如果使用其他名称，需要在build.gradle sourceSets->main->aidl.srcDirs=xxxx中另外声明。

在aidl目录中创建IAnimalManager.aidl

```kotlin
package com.example.demo;
// 自定义类需要自己手动导入包名，不然build生成对应java文件会错误
import com.example.demo.Animal;

// Declare any non-default types here with import statements

interface IAnimalManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
	// 自定义的接口
    void addAnimal(in Animal animal);
	// 自定义的接口
    List<Animal> getAnimals();
}
```

## 2.定义需要传递的的对象类

1.在java文件夹中，创建Animal.kt

2.实现Parcelable接口，用于在RPC过程中传递；实现Parcelable接口会自动生成以下代码。

```kotlin
package com.example.demo

import android.os.Parcel
import android.os.Parcelable

open class Animal(val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }

}
```

## 3.创建对应Animal.kt的aidl类 Animal.aidl

在于java文件夹中(src->java->com.example.demo)Animal.kt类同路径的文件夹中创建(src->aidl->com.example.demo)Animal.aidl类

```kotlin
package com.example.demo;

parcelable Animal;
```

## 4.build构建

完成以上操作后，build构建工程后会在

``build/generate/aidl_source_output_dir/xxxx/out/com.example.demo/IAnimalManager``

生成

```java
/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.example.demo;
// Declare any non-default types here with import statements

public interface IAnimalManager extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements com.example.demo.IAnimalManager {
        private static final java.lang.String DESCRIPTOR = "com.example.demo.IAnimalManager";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.example.demo.IAnimalManager interface,
         * generating a proxy if needed.
         */
        public static com.example.demo.IAnimalManager asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof com.example.demo.IAnimalManager))) {
                return ((com.example.demo.IAnimalManager) iin);
            }
            return new com.example.demo.IAnimalManager.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            java.lang.String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_basicTypes: {
                    data.enforceInterface(descriptor);
                    int _arg0;
                    _arg0 = data.readInt();
                    long _arg1;
                    _arg1 = data.readLong();
                    boolean _arg2;
                    _arg2 = (0 != data.readInt());
                    float _arg3;
                    _arg3 = data.readFloat();
                    double _arg4;
                    _arg4 = data.readDouble();
                    java.lang.String _arg5;
                    _arg5 = data.readString();
                    this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_addAnimal: {
                    data.enforceInterface(descriptor);
                    com.example.demo.Animal _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = com.example.demo.Animal.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    this.addAnimal(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getAnimals: {
                    data.enforceInterface(descriptor);
                    java.util.List<com.example.demo.Animal> _result = this.getAnimals();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements com.example.demo.IAnimalManager {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * Demonstrates some basic types that you can use as parameters
             * and return values in AIDL.
             */
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeLong(aLong);
                    _data.writeInt(((aBoolean) ? (1) : (0)));
                    _data.writeFloat(aFloat);
                    _data.writeDouble(aDouble);
                    _data.writeString(aString);
                    mRemote.transact(Stub.TRANSACTION_basicTypes, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void addAnimal(com.example.demo.Animal animal) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((animal != null)) {
                        _data.writeInt(1);
                        animal.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_addAnimal, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public java.util.List<com.example.demo.Animal> getAnimals() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List<com.example.demo.Animal> _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getAnimals, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.createTypedArrayList(com.example.demo.Animal.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_addAnimal = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_getAnimals = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    }

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException;

    public void addAnimal(com.example.demo.Animal animal) throws android.os.RemoteException;

    public java.util.List<com.example.demo.Animal> getAnimals() throws android.os.RemoteException;
}

```



到此aidl部分代码已经完成。

## 5.向客户端公开接口

Service实现IBiner接口后，客户端应该便可以绑定到该服务，并调用IBinder中的方法执行IPC。

```kotlin
package com.example.demo

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AnimalManagerService : Service() {
    val store: MutableList<Animal> = mutableListOf()

    override fun onBind(intent: Intent?): IBinder? {
       
        return object : IAnimalManager.Stub() {
            
            override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float,
                    aDouble: Double, aString: String?) {
            }

            override fun addAnimal(animal: Animal?) {
                if (animal != null) {
                    store.add(animal)
                }
                println("动物 ${store?.size} 新增 ${animal?.name}")
            }

            override fun getAnimals(): MutableList<Animal> {
                return store
            }


        }
    }
}

```

*另外需要在AndroidManifest.xml中添加service的声明*

现在，当客户端(如Activity)调用``bindService()``以连接此服务时，客户端``onServiceConnected()``回调会接收服务的``onBind()``方法返回的binder实例。

客户端还必须拥有接口类的访问权限，因此如果客户端和服务在*不同应用*内，则客户端引用的src/目录必须包含.aidl文件(该文件会生成android.os.Binder接口，进而位客户端提供AIDL方法的访问权限)的副本。

当客户端在``onServiceConnected()``回调中收到IBinder时，它必须调用*YourServiceInterface.Stub.asInterface(service)*，以将它返回的参数转换程*YourServiceInterface*类型。

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bindIntent = Intent()
        bindIntent.setClass(this, AnimalManagerService::class.java)
        bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE)

        add_btn.setOnClickListener {
            ToastUtils.showShort("add")
            val dog = Animal("a lovely doggy")
            mServiceConnection.animalMs?.let {
                it.addAnimal(dog)
            }
        }
    }

    object mServiceConnection : ServiceConnection {
        var animalMs: IAnimalManager? = null

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            animalMs = IAnimalManager.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            animalMs = null
        }
    }
```



## 总结

1. 由于进程间内存无法共享，所以进程间通信需要将对象类分解成操作系统可以理解的原语，然后将其编组为可供代码操作的对象。此操作繁琐，所以Android使用aidl处理此问题。

2. 因为需要跨进程通信，所以会存在两方，即服务端和客户端。Android中，定义当包含aidl文件的应用构建(build)完成后，会在build/gen/目录中生成同名的java文件，该类包含一个IBinder接口的静态类Stub。需要提供服务的Service实现该接口后，客户端就可以通过绑定该Service，通过强转IBinder对象*YourServiceInterface.Stub.asInterface(service)*就可以执行IPC。

   如：上面的Activity中connection的*onServiceConnected()*中强转为IAnimalManager对象调用addAnimal()；

   Launcher启动App时，在Instrumentation中

   ```java
    int result = ActivityTaskManager.getService()
                   .startActivity(whoThread, who.getBasePackageName(), intent,
                           intent.resolveTypeIfNeeded(who.getContentResolver()),
                           token, target, requestCode, 0, null, options);
   ```

   ```java
   ActivityTaskManager.getService()
   // 代码如下
   public static IActivityTaskManager getService() {
       return IActivityTaskManagerSingleton.get();
   }
   
   @UnsupportedAppUsage(trackingBug = 129726065)
   private static final Singleton<IActivityTaskManager> IActivityTaskManagerSingleton =
               new Singleton<IActivityTaskManager>() {
                   @Override
                   protected IActivityTaskManager create() {
                       final IBinder b = ServiceManager.getService(Context.ACTIVITY_TASK_SERVICE);
                       return IActivityTaskManager.Stub.asInterface(b);
                   }
               };
   ```

   ServiceManager#getService()获取的并不是四大组件的Service，而是IBinder接口。
3. 谷歌文档中的范例是使用Service的onBind()返回实现了Stub的对象，而Launcher启动Activity流程中，直接是返回Stub对象，并不是通过Service。

## 参考：

https://developer.android.com/guide/components/aidl#kotlin

https://blog.csdn.net/cike110120/article/details/85333101



