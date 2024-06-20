package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Test
public class vpntest {

    protected WebDriver driver;

    @Test
    public void testActions2() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < TimeUnit.HOURS.toMillis(5)) {

            ChromeOptions chromeOptions = new ChromeOptions();
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("windows")) {
                WebDriverManager.chromedriver().setup();
            } else {
                System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver-mac-x64");
            }

            try {
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                chromeOptions.setExperimentalOption("useAutomationExtension", false);

                driver = new ChromeDriver(chromeOptions);

                Dimension dimension = new Dimension(2400, 1400);
                driver.manage().window().setSize(dimension);

                driver.get("https://whatismyipaddress.com/");
                Thread.sleep(3000);
                takeScreenHhot("ip");

                driver.get("https://www.youtube.com/@day2day/playlists");
                Thread.sleep(8000);
                takeScreenHhot("Justnavigate");

                String a = driver.getCurrentUrl().toString();
                System.out.println(a);

                WebElement element = driver.findElement(By.xpath("(//*[@class='yt-simple-endpoint style-scope ytd-playlist-thumbnail'])[4]"));
                element.click();
                Thread.sleep(3700000);

                takeScreenHhot("check pause");

                try {
                    WebElement pause = driver.findElement(By.xpath("(//*[contains(@class,'yt-spec-button-shape-next--call-to-action')])[3]"));
                    takeScreenHhot("check paus end");
                    takeScreenHhot("start while");
                    
                    while (pause.isDisplayed()) {
                        takeScreenHhot("atBeforePause");
                        pause.click();
                        takeScreenHhot("atAfterPause");
                        Thread.sleep(900000);
                    }
                } catch (Exception e) {
                    takeScreenHhot("exe_at_while");
                }

                takeScreenHhot("beforeclose");
                driver.quit();
            } catch (Exception e) {
                takeScreenHhot("1exe_at_while");
                System.out.println(e.toString());
            }
        }
    }

    public void takeScreenHhot(String name) {
        if (driver == null) {
            System.out.println("Driver is not initialized. Cannot take screenshot.");
            return;
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss");
        String currentTimeAsString = currentTime.format(formatter);
        Path destination = Paths.get(System.getProperty("user.dir") + "/screenshots/" + currentTimeAsString + "_" + name + ".png");

        try {
            Files.copy(source.toPath(), destination);
            System.out.println("Screenshot taken: " + destination);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

}
