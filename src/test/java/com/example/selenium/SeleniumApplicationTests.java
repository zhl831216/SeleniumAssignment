package com.example.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class SeleniumApplicationTests {

	private static WebDriver driver;

	@BeforeAll
	static void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("incognito");
		driver = new ChromeDriver(options);
	}

	@BeforeEach
	void navigate() {
		driver.get("https://iths.se");
	}

	@Test
	void checkWebsiteTitle() {
		String websiteTitle = driver.getTitle();
		assertEquals("IT-Högskolan – Här startar din IT-karriär!", websiteTitle, "Titeln verkar inte stämma...");
	}

	@Test
	void checkWebsiteHeading() {
		WebElement websiteHeading = driver.findElement(By.xpath("//*[@id=\"frontpage\"]/div/div[1]/div/div/div[1]/h1"));
		String websiteHeadingText = websiteHeading.getText();
		assertEquals("Här startar din IT-karriär!", websiteHeadingText, "Huvudrubriken verkar inte stämma...");
	}

	@Test
	void checkImage() {
		WebElement image = driver.findElement(By.xpath("//div[@class=\"banner__image\" and @style=\"background-image:url('https://www.iths.se/wp-content/uploads/2019/02/thumbnails/ithsred11nyptwebb-1-2466-627x320.jpg')\"]"));
		boolean result = image.isDisplayed();
		assertTrue(result, "Bilden verkar inte synas...");
	}

	@Test
	void checkEmail() {
		// Hämta ut H5-elementet ovanför den div där e-posten finns
		WebElement preHeading = driver.findElement(By.xpath("//h5[text()='Göteborg' and @class='preheading']"));

		// Hämta den div (nedanför H5) där eposten finns
		WebElement contactInfoDiv = driver.findElement(with(By.className("contact-site--info")).below(preHeading));

		// Hämta ut e-postelementet
		WebElement emailElement = contactInfoDiv.findElement(By.xpath("//a[@href='mailto:info@iths.se']"));

		String emailAddress = emailElement.getAttribute("href");

		assertEquals("mailto:info@iths.se", emailAddress, "E-posten verkar inte stämma...");
	}

	@Test
	void checkQuote() {
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-omoss\"]/a")));

		WebElement aboutLink = driver.findElement(By.xpath("//*[@id=\"nav-omoss\"]/a"));

		aboutLink.click();

		String quoteText = driver.findElement(By.className("quote-block__quote-text")).getText();

		assertEquals("IT-Högskolan är det självklara valet för dig som är nyfiken på IT-branschen!", quoteText, "Citatet stämmer inte...");
	}

	@Test
	void checkHeading() {
		driver.get("https://svt.se");

		driver.manage().window().maximize();

		WebElement cookieButton = driver.findElement(By.className("CookieConsent__primaryButton___Th47k"));

		cookieButton.click();

		WebElement aboutUsLink = driver.findElement(By.xpath("//*[@id=\"nyh_a11y-primary-navigation-list\"]/li[8]/a"));

		aboutUsLink.click();

		String headingText = driver.findElement(By.id("h-ValkommentillhelaSverigestelevision")).getText();

		assertEquals("Välkommen till hela Sveriges television!", headingText, "Rubriken stämmer inte...");
	}

	@Test
	void checkNumberOfMenuLinks() {
		driver.get("https://coop.se");

		driver.manage().window().maximize();

		driver.findElement(By.className("cmptxt_btn_yes")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("Navigation-item")));

		List<WebElement> menuItems = driver.findElements(By.className("Navigation-item"));

		assertEquals(5, menuItems.size(), "Antalet länkar verkar inte stämma...");
	}

	@Test
	void checkSearch() {
		driver.get("https://sr.se");

		driver.manage().window().maximize();

		driver.findElement(By.id("acceptAllCookiesBtn")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-input")));

		WebElement searchInput = driver.findElement(By.id("search-input"));

		searchInput.sendKeys("p3");

		searchInput.sendKeys(Keys.ENTER);

		WebElement headingElement = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[1]/section/section[1]/div/header/h6"));

		String headingText = headingElement.getText();

		assertEquals("Kanaler", headingText, "Rubriken i sökresultatet verkar inte stämma...");
	}

	@AfterAll
	static void teardown() {
		driver.quit();
	}
}
