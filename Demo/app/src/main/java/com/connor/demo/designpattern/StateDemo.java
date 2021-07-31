package com.connor.demo.designpattern;

/**
 * 状态模式
 * 当一个对象的内在状态改变时允许改变其行为，这个对象看起来是改变了其类。
 * 状态模式主要解决的是当控制一个对象状态转换的条件表达式过于复杂的情况，把状态的判断逻辑转移到表示不同状态的一系列当中，
 * 可以把复杂的判断逻辑简化。
 *
 * 使用场景：
 * 条件分支语句判断，但是不能太多判断。
 *
 * 与责任链有点类似，区别在哪里？
 */
public class StateDemo {
    public static void main(String[] args) {
        Work newWork = new Work();
        newWork.mHour = 9;
        newWork.WriteProgram();
        newWork.mHour = 12;
        newWork.WriteProgram();
        newWork.mHour = 14;
        newWork.WriteProgram();
        newWork.mHour = 17;
        newWork.WriteProgram();
        newWork.mWorkFinished = false;
        newWork.mHour = 18;
        newWork.WriteProgram();
    }
}

class Work {
    private State mCurrent;
    public int mHour;
    public boolean mWorkFinished;

    public Work() {
        mCurrent = new ForenoonState();
    }

    public void setState(State state) {
        mCurrent = state;
    }

    public void WriteProgram() {
        mCurrent.WriteProgram(this);
    }
}

abstract class State {
    abstract void WriteProgram(Work work);
}

class ForenoonState extends State {

    @Override void WriteProgram(Work work) {
        if (work.mHour < 12) {
            // doSomething
        } else {
            work.setState(new NoonState());
            work.WriteProgram();
        }
    }
}

class NoonState extends State {

    @Override void WriteProgram(Work work) {
        if (work.mHour < 13) {
            // doSomething
        } else {
            work.setState(new AfternoonState());
            work.WriteProgram();
        }
    }
}

class AfternoonState extends State {
    @Override void WriteProgram(Work work) {
        if (work.mWorkFinished) {
            work.setState(new OffWorkState());
            work.WriteProgram();
        } else {
            if (work.mHour < 17) {
                // doSomething
            } else {
                work.setState(new OffWorkState());
                work.WriteProgram();
            }
        }
    }
}

class OffWorkState extends State {
    @Override void WriteProgram(Work work) {
        // doSomething
    }
}