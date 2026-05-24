import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesSistemaTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // 1. Inicializa o navegador Chrome
        driver = new ChromeDriver();

        // 2. Maximiza a janela do navegador
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        // 3. Fecha o navegador após o fim do teste
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testeExercicio1() {
        // 1. Acessa o site do Google
        driver.get("https://www.google.com");

        // 2. Procura TODOS os botões com o name "btnI"
        java.util.List<org.openqa.selenium.WebElement> botoes = driver.findElements(org.openqa.selenium.By.name("btnI"));

        // 3. Percorre os botões e clica apenas no que estiver visível na tela
        for (org.openqa.selenium.WebElement botao : botoes) {
            if (botao.isDisplayed()) {
                botao.click();
                break; // Sai do laço assim que conseguir clicar
            }
        }

        // 4. Pega o título da nova página e valida se é o correto
        String tituloAtual = driver.getTitle();
        org.junit.jupiter.api.Assertions.assertTrue(tituloAtual.contains("Google Doodles"),
                "O teste falhou! A página atual não é o Google Doodles. Título encontrado: " + tituloAtual);
    }
}