package otomasyon.page;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import otomasyon.BasePageCollections.BasePage;

public class Page extends BasePage {

	WebDriver driver;

	public Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String CSV_File = (System.getProperty("user.dir") + "//src//main//java//otomasyon//resources//data.csv");

	private By WAIT_LOGO = By.xpath("//div[@class='logo-text']");
	private By WAIT_BOOKS = By.xpath("//div[@id='product-table']");
	private By INCRAISE_NUMBER = By.xpath("//input[@style='width:22px']");
	private By EMPTY_BASKET_CHECK = By.xpath("//div[@class='empty']");
	private By INCRAISE_BOOK_CHECK = By.id("swal2-title");
	private By BASKET_NULL = By.xpath("//span[@id='cart-items'][contains(text(),'0')]");
	private By BOOK_IN_BASKET = By.xpath("//span[@id='cart-items'][contains(text(),'1')]");
	
	@FindBy(id = "search-input")
	private WebElement SEARCH_TEXTBOX_ID;

	@FindBy(xpath = "//div[@id='product-table']//img")
	private List<WebElement> BOOKS_XPATH;

	@FindBy(id = "button-cart")
	private WebElement ADDING_BOOK_ID;

	@FindBy(id = "cart-items")
	private WebElement BASKET_BUTTON_ID;

	@FindBy(id = "js-cart")
	private WebElement GO_to_BASKET_ID;

	@FindBy(xpath = "//input[@style='width:22px']")
	private WebElement INCRAISE_BOOK_NUMBER_XPATH;

	@FindBy(xpath = "//i[@class='fa fa-refresh green-icon']")
	private WebElement REFRESH_BASKET_XPATH;

	@FindBy(id = "swal2-title")
	private WebElement INCRAISE_BOOK_NUMBER_CHECK_ID;

	@FindBy(xpath = "//i[@class='fa fa-times red-icon']")
	private WebElement CLOSE_BUTTON_ID;

	@FindBy(xpath = "//div[@class='empty']")
	private WebElement EMPTY_BASKET_XPATH;

	public String HomePageCheck() {

		WaitForElementVisibilyt(WAIT_LOGO);
		return driver.getTitle();

	}

	public void SearchInput() throws CsvValidationException {

		WaitForElementClickable(SEARCH_TEXTBOX_ID);
		CSVReader reader = null;

		try {

			reader = new CSVReader(new FileReader(CSV_File));
			String[] cell = reader.readNext();

			while ((cell = reader.readNext()) != null) {
				String keyword = cell[0];
				SEARCH_TEXTBOX_ID.sendKeys(keyword);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		SEARCH_TEXTBOX_ID.sendKeys(Keys.ENTER);

	}

	public List<WebElement> RandomBookChose() {

		WaitForElementVisibilyt(WAIT_BOOKS);

		List<WebElement> l1 = BOOKS_XPATH;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		l1.get(new Random().nextInt(l1.size())).click();
		return l1;

	}

	public void AddingBasket() {

		WaitForElementClickable(ADDING_BOOK_ID);
		ADDING_BOOK_ID.click();

	}

	public String BasketValueCheck() throws InterruptedException {
		WaitForElementVisibilyt(BOOK_IN_BASKET);
		return BASKET_BUTTON_ID.getText();

	}

	public void ClickBasket() {
		WaitForElementClickable(BASKET_BUTTON_ID);
		BASKET_BUTTON_ID.click();

	}

	public void GotoBasket() {
		WaitForElementClickable(GO_to_BASKET_ID);
		GO_to_BASKET_ID.click();

	}

	public void IncreaseBook(String t1) {
		WaitForElementVisibilyt(INCRAISE_NUMBER);
		INCRAISE_BOOK_NUMBER_XPATH.sendKeys(Keys.BACK_SPACE);
		INCRAISE_BOOK_NUMBER_XPATH.sendKeys(t1);

	}

	public void RefreshIcon() {
		WaitForElementClickable(REFRESH_BASKET_XPATH);
		REFRESH_BASKET_XPATH.click();

	}

	public String BasketUpdateCheck() {
		WaitForElementVisibilyt(INCRAISE_BOOK_CHECK);
		return INCRAISE_BOOK_NUMBER_CHECK_ID.getText();

	}

	public void CloseButton() {
		WaitForElementClickable(CLOSE_BUTTON_ID);
		CLOSE_BUTTON_ID.click();

	}

	public void EmptyBasketClick() {
		WaitForElementVisibilyt(BASKET_NULL);
		BASKET_BUTTON_ID.click();
	}

	public String EmptyBasketCheck() {
		WaitForElementVisibilyt(EMPTY_BASKET_CHECK);
		return EMPTY_BASKET_XPATH.getText();

	}

}
