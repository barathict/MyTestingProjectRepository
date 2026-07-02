package com.MyTestSeleniumProject.baseClass;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.locks.LockSupport;
//import com.MyTestSeleniumProject.utilities.BrowserFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    protected static Properties prop;
    protected WebDriver driver;
    //public static FileInputStream fis;

    @BeforeSuite
    public void loadpropertyfile() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);
    }

    private void launchbrowser() {

        //initialize the browser that has been declared in properties file
        String browser = prop.getProperty("browserName");
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else {
            throw new IllegalArgumentException("Browser name not recognized: " + browser);
        }
    }

    //Navigate to URL
    private void configbrowser () {

        //define implicitwait
        int Impwait = Integer.parseInt(prop.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Impwait));

        //maximize the browser window
        driver.manage().window().maximize();

        //launching the URL
        try {
            driver.get(prop.getProperty("URL"));
        } catch (Exception e) {
            System.out.println("URL Launched successfully: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setup() throws IOException {
      loadpropertyfile();
      launchbrowser();
      configbrowser();
      staticwait();
      System.out.println("all testmethods are passed");
    }

    public void staticwait() {
        LockSupport.parkNanos(Duration.ofSeconds(5).toNanos());
    }

    @AfterMethod
    public void teardown(){
        if(driver != null){
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("if browser did not quit properly: " + e.getMessage());
            }
        }
    }

}
