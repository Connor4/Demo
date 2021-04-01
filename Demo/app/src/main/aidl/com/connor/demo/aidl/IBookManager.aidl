// IBookManager.aidl
package com.connor.demo.aidl;

import com.connor.demo.aidl.model.Book;

// Declare any non-default types here with import statements

interface IBookManager {
 List<Book> getBooks();
 void addBook(in Book book);
}