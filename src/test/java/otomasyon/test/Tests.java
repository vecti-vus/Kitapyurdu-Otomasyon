package otomasyon.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.testng.Assert;


import otomasyon.BaseTestCollections.BaseTests;
import otomasyon.page.Page;

public class Tests extends BaseTests {

	private Logger log = LogManager.getLogger(Tests.class.getName());

	@Test
	public void Check() {
		Page browser = new Page(driver);
		try {

			String title_Check = browser.HomePageCheck();
			Assert.assertEquals(title_Check, "Kitapyurdu, Kitapla buluşmanın en kolay yolu");
			log.info("Kitapyurdu Anasayfası Başarıyla Açıldı.");
			browser.SearchInput();
			log.info("Gerekli Arama Başarıyla Gerçekleşti.");
			browser.RandomBookChose();
			log.info("Rastgele Kitap Başarıyla Şeçildi.");
			browser.AddingBasket();
			log.info("Kitap Sepete Eklendi.");
			String basket_Check = browser.BasketValueCheck();
			Assert.assertEquals(basket_Check, "1");
			log.info("Sepette ki değer kontrol edilir.");
			browser.ClickBasket();
			log.info("Sepet iconuna tıklanır.");
			browser.GotoBasket();
			log.info("Sepete gidilir.");
			browser.IncreaseBook("2");
			log.info("Sepette ki değer arttırılır.");
			browser.RefreshIcon();
			log.info("Sepet yenilenir.");
			String basket_Update = browser.BasketUpdateCheck();
			Assert.assertEquals(basket_Update, "Sepetiniz güncelleniyor!");
			log.info("Sepetin güncellenmesi kontrol edilir.");
			browser.CloseButton();
			log.info("Sepette ki ürünler kaldırılır.");
			browser.EmptyBasketClick();
			log.info("Sepete tıklanır.");
			String emptyBasketCheck = browser.EmptyBasketCheck();
			Assert.assertEquals(emptyBasketCheck, "Alışveriş sepetiniz boş!");
			log.info("Sepetin boş olduğu kontrol edilir.");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
