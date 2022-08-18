import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import util.PropertiesReader;

import static util.PropertiesReader.readPropertiesFile;

public class AbstractTest {
    @BeforeClass
    public static void setup() throws Exception {
        readPropertiesFile(); // Подтягиваем параметры из src/main/resources/browser.properties
        //BrowserFactory.selectBrowser(PropertiesReader.getProperty("browser"));

        Configuration.baseUrl = PropertiesReader.getProperty("url");
        Configuration.timeout = 10000;

    }


}
