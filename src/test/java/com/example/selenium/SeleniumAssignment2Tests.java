package com.example.selenium;

import com.google.common.collect.Ordering;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumAssignment2Tests {
    private static WebDriver driver;

    @BeforeAll
    static void setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.get("https://svtplay.se");
        driver.manage().window().maximize();
        WebElement cookieButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/button[3]"));
        cookieButton.click();
        Thread.sleep(3000);
    }

    @BeforeEach
    void navigate() throws InterruptedException {
        driver.navigate().to("https://svtplay.se");
        Thread.sleep(3000);
    }

    @Test
    void checkWebsiteTitle() {

        String websiteTitle = driver.getTitle();
        assertEquals("SVT Play", websiteTitle, "Titeln verkar inte stämma...");

    }

    // Kontrollera att webbplatsens logotyp är synlig
    @Test
    void checkLogoVisible() {

        WebElement element = driver.findElement(By.tagName("svg"));
        assertTrue(element.isDisplayed());
    }
    //Kontrollera namnen på de tre länkarna i huvudmenyn “Start, Program,Kanaler”
    @Test
    void checkMainMenuName() {

        WebElement startLink = driver.findElement(By.cssSelector("li[type='start']"));
        WebElement programLink = driver.findElement(By.cssSelector("li[type='programs']"));
        WebElement channelLink = driver.findElement(By.cssSelector("li[type='channels'"));
        assertEquals("START", startLink .getText(),"The link name of Start is false.");
        assertEquals("PROGRAM", programLink.getText(),"The link name of Program is false.");
        assertEquals("KANALER", channelLink.getText(),"The link name of Channels is false.");
    }

    //Kontrollera att länken för “Tillgänglighet i SVT Play” är synlig och att rätt länktext visas.
    //Följ länken Tillgänglighet i SVT Play och kontrollera huvudrubriken
    @Test
    void checkAvailableChannelLink() throws InterruptedException{
        WebElement availableChannel = driver.findElement(By.cssSelector("#__next > div > div.play_root-container > div > footer > div > div.sc-87f10045-6.ddRhEu > div:nth-child(2) > p:nth-child(1) > a > span.sc-343fed33-3.dmRxHt"));
        assertTrue(availableChannel.isDisplayed());
        assertEquals("Tillgänglighet i SVT Play", availableChannel.getText());
        availableChannel.click();
        Thread.sleep(3000);

        String titleAvailableChannel = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/h1")).getText();
        assertEquals("Så arbetar SVT med tillgänglighet",titleAvailableChannel);

    }

    //Använd metoden “click()” för att navigera in till sidan “Program”. Kontrollera antalet kategorier som listas.
    @Test
    void checkTotalCategoriesInProgramLink()throws InterruptedException{

        WebElement programLink = driver.findElement(By.cssSelector("li[type='programs']"));
        programLink.click();
        Thread.sleep(3000);

        List<WebElement> categories = driver.findElements(By.tagName("article"));
        assertEquals(17,categories.size(),"The total number of categories is wrong");

    }

    // G Extra 1:
    // Klicka på länken till Vårens Serier och kontrollera antalet serier
    @Test
    void checkTotalSpringSeries() throws InterruptedException{

        WebElement springSeriesLink= driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/section[4]/div[1]/h2/a"));
        springSeriesLink.click();
        Thread.sleep(3000);

        List<WebElement> series = driver.findElements(By.tagName("article"));
        assertEquals(24,series.size(),"The total number of series is wrong");
    }

    //G Extra 2 :
    // Navigera till sidan Djur&Natur
    // Klicka vidare till fliken Program A-Ö och
    // Klicka på checkbox "Kan ses utomlands"
    // Verifiera att antalet av program som visas stämmer.

    //G Extra 3 :
    // Verifiera att program som kan ses utomlands sorterades i alfabetiska ordning.
    @Test
    void checkAnimalAndNaturPage() throws InterruptedException{
//G Extra 2
        WebElement animalAndNatureLink = driver.findElement(By.cssSelector("a[href='/kategori/djur-och-natur']"));
        animalAndNatureLink.click();
        Thread.sleep(2000);

        WebElement programAÖLink = driver.findElement(By.id("tab-1"));
        programAÖLink.click();
        Thread.sleep(2000);

        WebElement checkboxAbroad = driver.findElement(By.id("abroad"));
        checkboxAbroad.click();
        Thread.sleep(3000);

        List<WebElement> abroadPrograms = driver.findElements(By.tagName("article"));
        assertEquals(22,abroadPrograms.size());
//G Extra 3
        List<WebElement> abroadProgramsA = driver.findElements(By.cssSelector("a[classname='sc-b6440fda-1']"));
        ArrayList<String> abroadProgramNames = new ArrayList<>();
        for (WebElement program: abroadProgramsA){
            abroadProgramNames.add(program.getAttribute("aria-label"));
        }
        boolean isSorted = Ordering.natural().isOrdered(abroadProgramNames);
        assertTrue(isSorted);
    }


    // G extra 4:
    // Navigera till home till SVTPlay
    // Verifiera att Olika apparat namn som visas längre ner i sidan(footer)stämmer
    @Test
    void checkApparatNames () {
        WebElement apparatDiv = driver.findElement(By.className("llOfBu"));
        List<WebElement> apparaterna = apparatDiv.findElements(By.cssSelector("span[classname='sc-1eacba3e-7']"));

        ArrayList<String> apparatNames = new ArrayList<>();
        apparatNames.add("Dator");
        apparatNames.add("Mobil & platta");
        apparatNames.add("TV");

        for (int i = 0; i< apparaterna.size(); i++) {
            assertEquals(apparatNames.get(i), apparaterna.get(i).getText());
        }
    }

    // G extra 5:
    // Navigera till länk Barn
    //Verifiera att texten visas under rubriken stämmer
    @Test
    void checkTextUnderTitle () throws InterruptedException {
        WebElement kidsLink = driver.findElement(By.cssSelector("a[href=\"/kategori/barn\"]"));
        kidsLink.click();
        Thread.sleep(2000);

        String textUnderTitle = "Se populära barnprogram och barnserier, tecknade program och klassiska barnprogram som Pippi Långstrump, Bolibompa, Labyrint och Greta gris.";
        WebElement textUnderTitleElement = driver.findElement(By.className("cebpuI"));
        assertEquals(textUnderTitle,textUnderTitleElement.getText());
    }



// VG nr.1
    @Test
    void checkSearchAgenda()throws InterruptedException {
        WebElement searchElement = driver.findElement(By.name("q"));
        searchElement.sendKeys("Agenda");
        searchElement.sendKeys(Keys.RETURN);
        Thread.sleep(3000);

        WebElement agendaSearchResult = driver.findElement(By.tagName("em"));
        assertEquals("Agenda", agendaSearchResult.getText());
    }

    // VG nr.2
    @Test
    void checkSearchPistvakt() throws InterruptedException {
        WebElement searchElement = driver.findElement(By.name("q"));
        searchElement.sendKeys("Pistvakt");
        searchElement.sendKeys(Keys.RETURN);
        Thread.sleep(3000);

        WebElement pistvaktSearchResult = driver.findElement(By.tagName("em"));
        pistvaktSearchResult.click();
        Thread.sleep(3000);

//Klicka på länken till Säsong 2
        WebElement season2Link = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/div[3]/section[2]/h2/a"));
        season2Link.click();
        Thread.sleep(3000);

//Kontrollera därefter antalet program i säsong 2 av serien
        WebElement season2 = driver.findElement(By.id("season-2-jx3za5B"));
        List<WebElement> seasons = season2.findElements(By.tagName("h3"));
        assertEquals(6,seasons.size());

//Kontrollera även namnet på avsnitt 5 i säsong 2
        WebElement seasonFive = seasons.stream().toList().get(4);
        String season5name = seasonFive.getText();
        assertEquals("5. Personalfestan", season5name);
    }




    @AfterAll
    static void closeWebsite() {
        driver.quit();
    }


}
