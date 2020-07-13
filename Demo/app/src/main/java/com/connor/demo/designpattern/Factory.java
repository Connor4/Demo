package com.connor.demo.designpattern;

/**
 * 工厂模式
 * 创建对象时不会对客户端暴露创建逻辑，都是通过同一个接口（IFactory）来指向新创建的对象。
 * 定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂对象（ IFactory factory = new StudentFactory()）。
 *
 *
 * 应用场景：
 * 1.日志记录器：记录可以记录到本地硬盘，系统事件，远程服务器等，用户可以选择记录日志到什么地方。
 * 2.数据库替换：可以选择替换使用什么数据库
 * 3.连接服务器框架："POP3"、"IMAP"、"HTTP"，可以把这三个作为产品类，共同实现一个接口。
 */
public class Factory {

    public static void main(String[] args) {
        // 当需要学生对象的时候调用学生工厂
        IFactory factory = new StudentFactory();
        PeopleInSchool peopleInSchool = factory.createPeople();
        peopleInSchool.doSomething();

        IFactory factory1 = new TeacherFactory();
        PeopleInSchool peopleInSchool1 = factory1.createPeople();
        peopleInSchool1.doSomething();
    }

}

class PeopleInSchool {
    public void doSomething() {

    }
}

class Student extends PeopleInSchool {
    public void doSomething() {
        System.out.println("回答问题");
    }
}

class Teacher extends PeopleInSchool {
    public void doSomething() {
        System.out.println("提出问题");
    }
}

interface IFactory {
    PeopleInSchool createPeople();
}

class StudentFactory implements IFactory {
    @Override
    public PeopleInSchool createPeople() {
        return new Student();
    }
}

class TeacherFactory implements IFactory {
    @Override
    public PeopleInSchool createPeople() {
        return new Teacher();
    }
}
