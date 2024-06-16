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

                //ChromeOptions chromeOptions = new ChromeOptions();
            } else {

                System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver-mac-x64");


            }
//             Map prefs = new HashMap();
//             prefs.put("profile.default_content_settings.cookies", 2);
//             chromeOptions.setExperimentalOption("prefs", prefs);
            try {

                chromeOptions.addArguments("--disable-extensions");
                //chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                //chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);

                Dimension dimension = new Dimension(2400, 1400);
                driver.manage().window().setSize(dimension);

//driver.execute_script("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})") 

                 driver.get("https://whatismyipaddress.com/");
            Thread.sleep(3000);
            takeScreenHhot("ip");
                
                driver.get("https://www.youtube.com/@day2day/playlists");

                Thread.sleep(8000); // Sleep
            takeScreenHhot("Justnavigate");

//            try {
//                takeScreenHhot("Loadyoub");
//
//                WebElement ck = driver.findElement(By.xpath("//input[@type='checkbox']"));
//
//                if (ck.isDisplayed())
//                {
//                    ck.click();
//                    Thread.sleep(8000); // Slee
//                    takeScreenHhot("chkbxClick");
//                }
//
//            }
//            catch (Exception e)
//            {
//                System.out.println(e.toString());
//                takeScreenHhot("Inexception");
//            }


                String a = driver.getCurrentUrl().toString();
                System.out.println(a);


                // try {
                //     WebElement cookies = driver.findElement(By.xpath("//*[text()='Accept all']"));
                //     if (cookies.isDisplayed()) {
                //         cookies.click();
                //         Thread.sleep(8000); // Sleep
                //         takeScreenHhot("cookies");
                //     }
                // } catch (Exception e) {

                // }


                WebElement element = driver.findElement(By.xpath("(//*[@class='yt-simple-endpoint style-scope ytd-playlist-thumbnail'])[4]"));

                // Simulate pressing the space button on the element
                element.click();
                Thread.sleep(1700000); // Sleep for 1 second
                //Thread.sleep(3000); // Sleep for 1 second

                takeScreenHhot("check pause");

                 try {
                WebElement pause = driver.findElement(By.xpath("(//*[contains(@class,'yt-spec-button-shape-next--call-to-action')])[3]"));
                takeScreenHhot("check paus end");
                takeScreenHhot("start while");
                while (pause.isDisplayed())
                {
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


    public void testscreen() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < TimeUnit.SECONDS.toMillis(10)) {


            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("windows")) {
                WebDriverManager.chromedriver().setup();

                //ChromeOptions chromeOptions = new ChromeOptions();
            } else {


                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  OS");


            }
            ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

            driver = new ChromeDriver(chromeOptions);

            driver.get("https://whatismyipaddress.com/");
            Thread.sleep(3000);
            takeScreenHhot("ip");
            driver.quit();

        }
    }


    public void takeScreenHhot(String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        LocalDateTime currentTime = LocalDateTime.now();

        // Define a formatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss");

        // Format the current time as a string
        String currentTimeAsString = currentTime.format(formatter);

        // Define the path to save the screenshot
        Path destination = Paths.get(System.getProperty("user.dir") + "/screenshots/" + currentTimeAsString + "_" + name + ".png");

        try {
            // Copy screenshot to the destination
            Files.copy(source.toPath(), destination);
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  Screenshot taken Email");
            System.out.println(System.getProperty("user.dir"));
            System.out.println(destination);
            //sendEmail(destination.toString());
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }


    public void sendEmail(String filename) {
// Recipient's email ID needs to be mentioned.
        String to = "chathurahjm@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "brakesautomation002@gmail.com";

        final String username = "brakesautomation002@gmail.com";//change accordingly
        final String password = "vtayrvuxrrdsjsbg";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();

            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
