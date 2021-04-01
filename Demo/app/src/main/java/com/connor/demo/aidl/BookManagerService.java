package com.connor.demo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.connor.demo.aidl.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by dzb 2021/04/01
 */
public class BookManagerService extends Service {
    private List<Book> mBooks = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("dzb", "onbind");
        return new IBookManager.Stub() {
            @Override
            public List<Book> getBooks() throws RemoteException {
                return mBooks;
            }

            @Override
            public void addBook(Book book) throws RemoteException {
                if (book != null) {
                    mBooks.add(book);
                }
                Log.d("dzb", "service add book：" + mBooks.size() + "新添加的是" + book.getName());
            }
        };
    }

}
