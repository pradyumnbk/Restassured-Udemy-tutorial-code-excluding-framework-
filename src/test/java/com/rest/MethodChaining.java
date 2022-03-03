package com.rest;

public class MethodChaining {

    public static void main(String[] args) {
        //MethodChaining methodChaining = new MethodChaining();
        m1().m2().m3();
    }
    public static MethodChaining m1() { System.out.println("this is the method a1"); return new MethodChaining();}

    public  MethodChaining m2() { System.out.println("this is the method a2 "); return this; }

    public  MethodChaining m3() { System.out.println("this is the method a3"); return this; }
}
