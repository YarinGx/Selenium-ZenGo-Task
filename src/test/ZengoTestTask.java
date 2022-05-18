package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class ZengoTestTask {
    private WebDriver driver;

    @Test
    public void testURL(String expectedURL){
        try{
            Assert.assertEquals(expectedURL, driver.getCurrentUrl());
            System.out.println("The site on "+expectedURL+" has navigated successfully!");
        }
        catch(Throwable pageNavigationError){
            System.out.println("The site didn't navigate successfully");
        }
    }
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\yarin\\Downloads\\SeleniumJars\\chromedriver.exe");
        String expectedURL = "https://zengo.com/";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://zengo.com/");
        testURL(expectedURL);

    }
    public void navbarSelection() throws InterruptedException {
        String expectedURL = "https://zengo.com/assets/ethereum-wallet/";
        Thread.sleep(2000);
        WebElement assets =  driver.findElement(By.linkText("Assets"));
        //mouth-hover menu
        Actions action = new Actions(driver);
        action.moveToElement(assets).perform();
        Thread.sleep(2000);

        WebElement ethereum = driver.findElement(By.xpath("//*[text()='Ethereum (ETH)']"));
        action.moveToElement(ethereum).click().perform();

        testURL(expectedURL);

        Thread.sleep(2000);
        //verify logo displayed successfully
        logoVerification();
        Thread.sleep(2000);

        driver.navigate().to("https://zengo.com/");
    }
    public void logoVerification(){
        boolean logoPresent = driver.findElement(By.className("no-lazy-load")).isDisplayed();
        if(logoPresent){
            System.out.println("ZenGo logo is displayed successfully!");
        }
        else{
            System.out.println("ZenGo logo isn't displayed successfully!");
        }

    }
     public void closeBrowser(){
         driver.quit();
         System.out.println("Browser Closed !");
     }

    public static void main(String[] args) throws InterruptedException {
        ZengoTestTask zt = new ZengoTestTask();
        zt.launchBrowser();
        zt.navbarSelection();

        Thread.sleep(2000);
        zt.closeBrowser();
    }
}
