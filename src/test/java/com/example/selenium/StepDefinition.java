package com.example.selenium;

import com.google.common.collect.Ordering;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
import static org.junit.jupiter.api.Assertions.assertAll;

public class StepDefinition {
    static WebDriver driver;

    @Given("SVT Play is available")
    public void svt_play_is_available()throws InterruptedException{
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
    @When("user visit the homepage")
    public void user_visit_the_homepage() throws InterruptedException  {
        driver.get("https://svtplay.se");
        Thread.sleep(2000);
    }
    @Then("the title should be {string}")
    public void the_title_should_be2(String string) {
        // Write code here that turns the phrase above into concrete actions
        String websiteTitle = driver.getTitle();
        assertEquals(string, websiteTitle, "Titeln verkar inte stämma...");
    }
    @Then("the logo of the website is visible")
    public void the_logo_of_the_website_is_visible() {
        WebElement element = driver.findElement(By.tagName("svg"));
        assertTrue(element.isDisplayed());
    }

    @Then("the names of main menu should be {string}, {string} and {string}")
    public void the_names_of_main_menu_should_be_and(String string, String string2, String string3) {
        List<WebElement> menus = driver.findElements(By.className("sc-354ed288-1"));
        assertAll(
                () -> assertEquals(string, menus.get(0).getText(), "The link name of Start is false."),
                () -> assertEquals(string2, menus.get(1).getText(), "The link name of Program is false."),
                () -> assertEquals(string3, menus.get(2).getText(), "The link name of Channels is false.")
        );
    }

    @Then("the link “Tillgänglighet i SVT Play” should be visible")
    public void the_link_tillgänglighet_i_svt_play_should_be_visible() {
        WebElement availableChannel = driver.findElement(By.cssSelector("#__next > div > div.play_root-container > div > footer > div > div.sc-87f10045-6.ddRhEu > div:nth-child(2) > p:nth-child(1) > a > span.sc-343fed33-3.dmRxHt"));
        assertTrue(availableChannel.isDisplayed());
    }
    @Then("the right text on the link should be {string}")
    public void the_right_text_on_the_link_should_be(String string) {
        WebElement availableChannel = driver.findElement(By.cssSelector("#__next > div > div.play_root-container > div > footer > div > div.sc-87f10045-6.ddRhEu > div:nth-child(2) > p:nth-child(1) > a > span.sc-343fed33-3.dmRxHt"));
        assertEquals(string, availableChannel.getText());
    }
    @When("user clicks the link Tillgänglighet i SVT Play")
    public void user_clicks_the_link() throws InterruptedException{
        WebElement availableChannel = driver.findElement(By.cssSelector("#__next > div > div.play_root-container > div > footer > div > div.sc-87f10045-6.ddRhEu > div:nth-child(2) > p:nth-child(1) > a > span.sc-343fed33-3.dmRxHt"));
        availableChannel.click();
        Thread.sleep(3000);
    }

    @Then("the title of the page Tillgänglighet i SVT Play should be {string}")
    public void the_title_of_the_page_should_be(String string) {
        String titleAvailableChannel = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/h1")).getText();
        assertEquals(string,titleAvailableChannel);
    }

    @When("user clicks the link Program in the main menu")
    public void user_clicks_the_link_program_in_the_main_menu() throws InterruptedException {
        WebElement programLink = driver.findElement(By.cssSelector("li[type='programs']"));
        programLink.click();
        Thread.sleep(3000);
    }

    @Then("the total number of categories in the page Program should be {int}")
    public void the_total_number_of_categories_in_the_page_should_be(Integer int1) {
        List<WebElement> categories = driver.findElements(By.tagName("article"));
        assertEquals(int1,categories.size(),"The total number of categories is wrong");
    }
    @When("user clicks the link Vårens Serier")
    public void user_clicks_the_link_vårens_serier() throws InterruptedException {
        WebElement springSeriesLink= driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/section[4]/div[1]/h2/a"));
        springSeriesLink.click();
        Thread.sleep(3000);
    }
    @Then("the total number of series in the page should be {int}")
    public void the_total_number_of_series_in_the_page_should_be(Integer int1) {
        List<WebElement> series = driver.findElements(By.tagName("article"));
        assertEquals(int1,series.size(),"The total number of series is wrong");
    }

    @When("user clicks the link Djur&Natur")
    public void user_clicks_the_link_djur_natur()throws InterruptedException {
        WebElement animalAndNatureLink = driver.findElement(By.cssSelector("a[href='/kategori/djur-och-natur']"));
        animalAndNatureLink.click();
        Thread.sleep(2000);
    }

    @When("clicks the tab Program A-Ö in the page Djur&Natur")
    public void clicks_the_tab_program_a_ö_in_the_page_djur_natur() throws InterruptedException {
        WebElement programAÖLink = driver.findElement(By.id("tab-1"));
        programAÖLink.click();
        Thread.sleep(2000);
    }

    @When("clicks the checkbox Kan ses utomlands in the tab Program A-Ö")
    public void clicks_the_checkbox_kan_ses_utomlands_in_the_tab_program_a_ö()throws InterruptedException {
        WebElement checkboxAbroad = driver.findElement(By.id("abroad"));
        checkboxAbroad.click();
        Thread.sleep(3000);
    }

    @Then("the total number of programs which can be seen from abroad should be {int}")
    public void the_total_number_of_programs_which_can_be_seen_from_abroad_should_be(Integer int1) {
        List<WebElement> abroadPrograms = driver.findElements(By.tagName("article"));
        assertEquals(int1,abroadPrograms.size());
    }

    @Then("the programs are sorted in the alphabetic sequence")
    public void the_programs_are_sorted_in_the_alphabetic_sequence() {
        List<WebElement> abroadProgramsA = driver.findElements(By.cssSelector("a[classname='sc-b6440fda-1']"));
        ArrayList<String> abroadProgramNames = new ArrayList<>();
        for (WebElement program: abroadProgramsA){
            abroadProgramNames.add(program.getAttribute("aria-label"));
        }
        boolean isSorted = Ordering.natural().isOrdered(abroadProgramNames);
        assertTrue(isSorted);
    }

    @Then("The device names which displayed in the page should be {string},{string} and {string}")
    public void the_device_names_which_displayed_in_the_page_should_be_and(String string, String string2, String string3) {
        WebElement apparatDiv = driver.findElement(By.className("llOfBu"));
        List<WebElement> apparaterna = apparatDiv.findElements(By.cssSelector("span[classname='sc-1eacba3e-7']"));

        ArrayList<String> apparatNames = new ArrayList<>();
        apparatNames.add(string);
        apparatNames.add(string2);
        apparatNames.add(string3);

        for (int i = 0; i< apparaterna.size(); i++) {
            assertEquals(apparatNames.get(i), apparaterna.get(i).getText());
        }
    }
    @When("user clicks the link Barn in the home page")
    public void user_clicks_the_link_barn_in_the_home_page()throws InterruptedException {
        WebElement kidsLink = driver.findElement(By.cssSelector("a[href=\"/kategori/barn\"]"));
        kidsLink.click();
        Thread.sleep(2000);
    }

    @Then("the text which is under the title should be {string}")
    public void the_text_which_is_under_the_title_should_be(String string) {
        WebElement textUnderTitleElement = driver.findElement(By.className("cebpuI"));
        assertEquals(string,textUnderTitleElement.getText());
    }
    @When("User clicks Kanaler in the main menu")
    public void user_visits_svt_play() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        WebElement kanalLink = driver.findElement(By.cssSelector("li[type='channels']"));
        kanalLink.click();
        Thread.sleep(2000);
    }
    @Then("The title of page Kanaler should be {string}")
    public void the_title_should_be(String string) {
        // Write code here that turns the phrase above into concrete actions
        WebElement titleKanal = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/h1"));
        assertEquals(string, titleKanal.getText(), "Titeln verkar inte stämma...");
    }
    @When("user input the word Agenda")
    public void user_input_the_word() throws InterruptedException {
        WebElement searchElement = driver.findElement(By.name("q"));
        searchElement.sendKeys("Agenda");
        searchElement.sendKeys(Keys.RETURN);
        Thread.sleep(3000);
    }

    @Then("the program which is visible on the top of list should be {string}")
    public void the_program_which_is_visible_on_the_top_of_list_should_be(String string) {
        WebElement agendaSearchResult = driver.findElement(By.tagName("em"));
        assertEquals(string, agendaSearchResult.getText());
    }

    @When("user input the word Pistvakt")
    public void user_input_the_word_pistvakt()throws InterruptedException {
        WebElement searchElement = driver.findElement(By.name("q"));
        searchElement.sendKeys("Pistvakt");
        searchElement.sendKeys(Keys.RETURN);
        Thread.sleep(3000);
    }
    @Then("the program name of the search result is {string}")
    public void pistvakt_is_searched_out(String string) {
        WebElement pistvaktSearchResult = driver.findElement(By.tagName("em"));
        assertEquals(string,pistvaktSearchResult.getText());
    }

    @When("user visit the page Pistvakt")
    public void user_visit_the_page_pistvakt() throws InterruptedException {
        WebElement pistvaktSearchResult = driver.findElement(By.tagName("em"));
        pistvaktSearchResult.click();
        Thread.sleep(3000);
    }
    @When("user clicks season2 of Pistvakt")
    public void user_clicks_season2_of_pistvakt() throws InterruptedException {
        WebElement season2Link = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/div[3]/section[2]/h2/a"));
        season2Link.click();
        Thread.sleep(3000);
    }

    @Then("the total number of the series should be {int}")
    public void the_total_number_of_the_series_should_be(Integer int1) {
        WebElement season2 = driver.findElement(By.id("season-2-jx3za5B"));
        List<WebElement> seasons = season2.findElements(By.tagName("h3"));
        assertEquals(int1,seasons.size());
    }

    @Then("the name of the episode5 in season2 should be {string}")
    public void the_name_of_the_episode5_in_season2_should_be(String string) {
        WebElement season2 = driver.findElement(By.id("season-2-jx3za5B"));
        List<WebElement> seasons = season2.findElements(By.tagName("h3"));
        WebElement seasonFive = seasons.stream().toList().get(4);
        String season5name = seasonFive.getText();
        assertEquals(string, season5name);
        driver.quit();
    }

}

