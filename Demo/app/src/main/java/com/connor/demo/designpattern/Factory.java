package com.connor.demo.designpattern;

/**
 * 工厂模式
 */
public class Factory {
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

    public void test() {
        // 当需要学生对象的时候调用学生工厂
        IFactory factory = new StudentFactory();
        PeopleInSchool peopleInSchool = factory.createPeople();
        peopleInSchool.doSomething();

        IFactory factory1 = new TeacherFactory();
        PeopleInSchool peopleInSchool1 = factory1.createPeople();
        peopleInSchool1.doSomething();
    }


}
