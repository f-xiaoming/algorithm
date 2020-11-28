package com.xiaofang.socket;

/*
    解析回答的数据类
*/
public class Result {
    String name;
    int type;
    int Cs;
    int TTL;
    int Length;
    String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCs() {
        return Cs;
    }

    public void setCs(int cs) {
        Cs = cs;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "域名='" + name + '\'' +
                ", 类型=" + type +
                ", Class=" + Cs +
                ", TTL=" + TTL +
                ", 数据长度=" + Length +
                ", 数据='" + data + '\'' +
                '}';
    }
}
