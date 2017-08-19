
package com.kileyowen.swarmsimbot.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.kileyowen.swarmsim.utils.DebugMessages;
import com.kileyowen.swarmsimbot.pages.Director;
import com.kileyowen.swarmsimbot.pages.ManagerPage;

public class Main {

	private static final String BASE_URL = "https://swarmsim.github.io/";

	private static final String WEBDRIVER_CHROME_PATH = "C:\\Program Files (x86)\\webdriver\\chromedriver.exe";

	private static final String WEBDRIVER_CHROME_PROPERTY = "webdriver.chrome.driver";

	public static void main(final String[] args) {

		final int timeoutInSeconds = 10;
		final int pollIntervalInMs = 10;

		try (final ManagerPage manager = Main.setup()) {

			final Director director = Director.init(manager, timeoutInSeconds, pollIntervalInMs);

			director.go();

			System.out.println(DebugMessages.DONE);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static ManagerPage setup() {

		System.setProperty(Main.WEBDRIVER_CHROME_PROPERTY, Main.WEBDRIVER_CHROME_PATH);

		final WebDriver driver = new ChromeDriver();

		driver.get(Main.BASE_URL);

		driver.manage().window().maximize();

		return ManagerPage.init(driver);
	}

}
