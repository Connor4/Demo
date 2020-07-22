package com.connor.demo.designpattern;

/**
 * 抽象工厂模式
 * 提供一个创建一系列相关或相互依赖对象的接口，而无需指定他们具体的类。
 * <p>
 * 跟工厂方法不同：工厂方法是直接生产明确的类，抽象工厂模式生产具体工厂(AccessFactory/SqlServerFactory)去处理自己对应的事务(insert/get)
 */
public class AbstractFactory {

    public static void main(String[] args) {
        User user = new User();
        Department department = new Department();

        IAbstractFactory factory = new AccessFactory();
        IUser user1 = factory.createUser();
        user1.insert(user);
        user1.getUser(1);
        IDepartment department1 = factory.createDepartment();
        department1.Insert(department);
        department1.getDepartment(1);

        IAbstractFactory factory1 = new SqlServerFactory();
        IUser user2 = factory1.createUser();
        user2.insert(user);
        user2.getUser(1);
        IDepartment department2 = factory1.createDepartment();
        department2.Insert(department);
        department2.getDepartment(1);
    }
}

class User {
}

class Department {
}

interface IDepartment {
    void Insert(Department department);

    Department getDepartment(int id);
}

class SqlServerDepartment implements IDepartment {

    @Override public void Insert(Department department) {

    }

    @Override public Department getDepartment(int id) {
        return null;
    }
}

class AccessDepartment implements IDepartment {

    @Override public void Insert(Department department) {

    }

    @Override public Department getDepartment(int id) {
        return null;
    }
}

interface IUser {
    void insert(User user);

    User getUser(int id);
}

class SqlServerUser implements IUser {

    @Override public void insert(User user) {

    }

    @Override public User getUser(int id) {
        return null;
    }
}

class AccessUser implements IUser {

    @Override public void insert(User user) {

    }

    @Override public User getUser(int id) {
        return null;
    }
}

interface IAbstractFactory {
    IUser createUser();

    IDepartment createDepartment();
}

class SqlServerFactory implements IAbstractFactory {

    @Override public IUser createUser() {
        return new SqlServerUser();
    }

    @Override public IDepartment createDepartment() {
        return new SqlServerDepartment();
    }
}

class AccessFactory implements IAbstractFactory {

    @Override public IUser createUser() {
        return new AccessUser();
    }

    @Override public IDepartment createDepartment() {
        return new AccessDepartment();
    }
}