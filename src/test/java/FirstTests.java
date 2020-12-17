import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstTests {

    WebDriver driver; // utworzenie pustego pola driver aby bylo dostepne we wszystkich metodach testowych
    WebDriverWait wait;


    public void highlightElement(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js. executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    @Before //warunki poczatkowe testow, wykona sie przed kazda metoda Test
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("wykonuje sie tutaj! przed metoda testowa");
        wait = new WebDriverWait(driver,10);

    }


    @Test //kroki testowe - po prostu test do wykonania
    public void firstTest(){
        wait = new WebDriverWait(driver,10); //przejdź na stronę dev.to
        WebElement sideBarVideo = driver.findElement(By.xpath("/html/body/div[9]/div/div/div[1]/aside/nav[1]/div/a[3]")); //znajdz element week
        highlightElement(sideBarVideo);
        //sideBarVideo.click(); //klikamy w weekBtn
    }
    @Test
    public void openFirstVideoPage(){
        driver.get("https:dev.to");
        WebElement sideBarVideo = driver.findElement(By.partialLinkText("Videos"));
        highlightElement(sideBarVideo);
        sideBarVideo.click();
        //przechodzimy na strone z wideo
        //powinnismy poczekac na zaladowanie nastepnej strony
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/videos"));
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        highlightElement(firstVideo);
        firstVideo.click();

    }
    @Test
    public void highlightFirstVideo(){
        driver.get("https://dev.to/videos");
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        highlightElement(firstVideo);
        firstVideo.click();
    }
//wejdz na strone dev.to
//kliknac poedcast
//wybrac pierwszy podcast
//sprawdzic czy jestem na odpowiedniej stronie - czy tytul podcastu sie zgadza
//sprawdzic czy moge nacisnac play w podcascie

    @Test
    public void selectFirstPodcast() {
        driver.get("https://dev.to");
        WebElement podcasts = driver.findElement(By.partialLinkText("Podcasts"));
        podcasts.click();
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        WebElement firstPodcast = driver.findElement(By.cssSelector(".content > h3:first-child"));
        highlightElement(firstPodcast);
        String podcastTitleFromList = firstPodcast.getText();

        String firstPodcastFromListLink = driver.findElement(By.cssSelector("#substories > a:first-child")).getAttribute("href");


        firstPodcast.click();
        wait.until(ExpectedConditions.urlToBe(firstPodcastFromListLink));
        WebElement podcastTitle = driver.findElement(By.cssSelector(".title > h1:nth-child(2)"));
        String podcastTitleText = podcastTitle.getText();
        assertTrue(podcastTitleFromList.contains(podcastTitleText));

        WebElement record = driver.findElement(By.className("record"));
        record.click();
        WebElement initializing = driver.findElement(By.className("status-message"));
        wait.until(ExpectedConditions.invisibilityOf(initializing));

        WebElement recordWrapper = driver.findElement(By.className("record-wrapper"));
        Boolean isPodcastPlayed = recordWrapper.getAttribute("class").contains("playing");

        assertTrue(isPodcastPlayed);

        //  Na stronie dev.to://
        //  1. Wyszukaj w search barze text : testing
//2. Naciśnij enter
//3. Poczekaj na stronę - wait tym razem będzie troche inny niż urlToBe
//4. Sprawdź czy pierwszy element na stronie zawiera słowo testing w nazwie ;)



    }




}

 //   @After
  //  public void tearDown(){
  //      driver.quit();
  //      System.out.println("po kazdej metodzie testowej");
   // }


