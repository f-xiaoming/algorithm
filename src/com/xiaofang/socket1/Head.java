package com.xiaofang.socket1;

/*
    解析首部的数据类
*/
public class Head {
    String ID;
    String QR;
    String Opcode;
    String AA;
    String TC;
    String RD;
    String RA;
    String Z;
    String Recode;
    int Questions;
    int Answers;
    int Authority;
    int Additional;

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getQuestions() {
        return Questions;
    }

    public void setQuestions(int questions) {
        Questions = questions;
    }

    public int getAnswers() {
        return Answers;
    }

    public void setAnswers(int answers) {
        Answers = answers;
    }

    public int getAuthority() {
        return Authority;
    }

    public void setAuthority(int authority) {
        Authority = authority;
    }

    public int getAdditional() {
        return Additional;
    }

    public void setAdditional(int additional) {
        Additional = additional;
    }

    public void setQR(String QR) {
        this.QR = QR;
    }

    public void setOpcode(String opcode) {
        Opcode = opcode;
    }

    public void setAA(String AA) {
        this.AA = AA;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public void setRD(String RD) {
        this.RD = RD;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public void setZ(String z) {
        Z = z;
    }

    public void setRecode(String recode) {
        Recode = recode;
    }

    @Override
    public String toString() {
        return "Head{" +
                "ID='" + ID + '\'' +
                ", QR='" + QR + '\'' +
                ", Opcode='" + Opcode + '\'' +
                ", AA='" + AA + '\'' +
                ", TC='" + TC + '\'' +
                ", RD='" + RD + '\'' +
                ", RA='" + RA + '\'' +
                ", Z='" + Z + '\'' +
                ", Recode='" + Recode + '\'' +
                ", Questions=" + Questions +
                ", Answers=" + Answers +
                ", Authority=" + Authority +
                ", Additional=" + Additional +
                '}';
    }
}
