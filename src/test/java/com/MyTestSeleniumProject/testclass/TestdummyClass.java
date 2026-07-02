package com.MyTestSeleniumProject.testclass;

import com.MyTestSeleniumProject.baseClass.BaseClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestdummyClass extends BaseClass {

    @Test
    public void testdummy () {
       String title = driver.getTitle();
       assert title.equals("OrangeHRM"): "Test Fail - Title does not match";
        System.out.println("Test Pass title is matching");

}



}
