package com.gdti;

import org.testng.TestNG;

public class TestRunner {

    static TestNG testNG;

    public static void main(String[] args){

        testNG = new TestNG();

        testNG.setTestClasses(new Class[] {Consultation.class});

        testNG.run();
    }

}
