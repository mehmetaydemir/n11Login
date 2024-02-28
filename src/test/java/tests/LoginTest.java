package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import utilities.ReusableMethods;

import java.time.Duration;

public class LoginTest {

    @Test
    public void n11LoginTest(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.get("https://www.n11.com/");
        ReusableMethods.bekle(3);

        WebElement girisYap = driver.findElement(By.xpath("//*[@title='Giriş Yap']"));

        //giris yap butonuna tıklanır
        girisYap.click();
        ReusableMethods.bekle(2);
        WebElement n11Firsat = driver.findElement(By.xpath("//*[@class='dn-slide-deny-btn']"));
        n11Firsat.click();
        ReusableMethods.bekle(3);

        //ePosta Kontrolleri
        WebElement ePosta = driver.findElement(By.id("email"));

        // ePosta boş olduğunda
        ePosta.sendKeys(" ");
        WebElement sifre = driver.findElement(By.id("password"));
        sifre.click();
        String expectedEPostaUyari = "Geçerli bir e-posta adresi girmelisin.";
        WebElement ePostaUyariBildirimi = driver.findElement(By.xpath("//*[@class='errorMessage']"));
        String actualEPostaUyari = ePostaUyariBildirimi.getText();
        Assert.assertEquals(expectedEPostaUyari,actualEPostaUyari);

        // ePosta @ karakteri olmadığında
        ePosta.clear();
        ReusableMethods.bekle(5);
        ePosta.sendKeys("mhmtaydemir32gmail.com");
        sifre.click();
        expectedEPostaUyari = "Geçerli bir e-posta adresi girmelisin.";
        actualEPostaUyari = ePostaUyariBildirimi.getText();
        Assert.assertEquals(expectedEPostaUyari,actualEPostaUyari);

        // ePosta .com  olmadığında
        ePosta.clear();
        ReusableMethods.bekle(5);
        ePosta.sendKeys("mhmtaydemir32@gmail");
        sifre.click();
        expectedEPostaUyari = "Geçerli bir e-posta adresi girmelisin.";
        actualEPostaUyari = ePostaUyariBildirimi.getText();
        Assert.assertEquals(expectedEPostaUyari,actualEPostaUyari);

        //Şifre Kontrolleri

        //Şifre Alanı Boş
        ePosta.clear();
        ReusableMethods.bekle(5);
        ePosta.sendKeys("mhmtaydemir32@gmail.com");
        sifre.sendKeys(" ");
        WebElement girisYapBtn = driver.findElement(By.id("loginButton"));
        girisYapBtn.click();
        ReusableMethods.bekle(5);
        WebElement sifreUyariBildirimi = driver.findElement(By.xpath("(//*[@class='errorMessage'])[2]"));
        String expectedSifreUyariEnAz = "Şifreni girebilir misin?";
        String actualSifreUyariEnAz = sifreUyariBildirimi.getText();
        Assert.assertEquals(expectedSifreUyariEnAz,actualSifreUyariEnAz);

        // en az 6 karakter kontrolü
        ePosta.clear();
        ReusableMethods.bekle(2);
        ePosta.sendKeys("mhmtaydemir32@gmail.com");
        sifre.sendKeys("1");
        girisYapBtn = driver.findElement(By.id("loginButton"));
        girisYapBtn.click();
        ReusableMethods.bekle(5);
        sifreUyariBildirimi = driver.findElement(By.xpath("(//*[@class='errorMessage'])[2]"));
        expectedSifreUyariEnAz = "Girilen değer en az 6 karakter olmalıdır.";
        actualSifreUyariEnAz = sifreUyariBildirimi.getText();
        Assert.assertEquals(expectedSifreUyariEnAz,actualSifreUyariEnAz);

        // en çok 15 karakter kontrolü
        sifre.clear();
        ReusableMethods.bekle(5);
        sifre.sendKeys("ma12345678901234");
        girisYapBtn.click();
        ReusableMethods.bekle(5);
        sifreUyariBildirimi = driver.findElement(By.xpath("(//*[@class='errorMessage'])[2]"));
        String expectedSifreUyariEnCok = "Girilen değer en fazla 15 karakter olmalıdır.";
        String actualSifreUyariEnCok = sifreUyariBildirimi.getText();
        Assert.assertEquals(expectedSifreUyariEnCok,actualSifreUyariEnCok);

        // Login Kontrolleri
        ePosta.clear();
        sifre.clear();
        ReusableMethods.bekle(5);
        ePosta.sendKeys("mhmtaydemir32@gmail.com");
        sifre.sendKeys("ma24632");
        girisYapBtn.click();
        ReusableMethods.bekle(6);
        WebElement hesabim = driver.findElement(By.xpath("//*[@class='myAccount myAccountElliptical']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hesabim).perform();
        ReusableMethods.bekle(3);
        WebElement cikis = driver.findElement(By.xpath("//*[@class='logoutBtn']"));
        cikis.click();
        ReusableMethods.bekle(5);









        ReusableMethods.bekle(2);
        driver.quit();






    }



}
