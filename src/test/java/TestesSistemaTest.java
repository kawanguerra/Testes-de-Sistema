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

    @Test
    public void testeExercicio2() {
        // 1. Acessa a página de login do HerokuApp
        driver.get("http://the-internet.herokuapp.com/login");

        // 2. Preenche os campos de Usuário e Senha
        // O site indica usar o usuário "tomsmith" e a senha "SuperSecretPassword!"
        driver.findElement(org.openqa.selenium.By.id("username")).sendKeys("tomsmith");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("SuperSecretPassword!");

        // 3. Clica no botão de Login (que é um botão do tipo submit)
        driver.findElement(org.openqa.selenium.By.cssSelector("button[type='submit']")).click();

        // 4. Verifica se a mensagem de sucesso "You logged into a secure area!" aparece na tela
        String mensagemAlerta = driver.findElement(org.openqa.selenium.By.id("flash")).getText();
        org.junit.jupiter.api.Assertions.assertTrue(mensagemAlerta.contains("You logged into a secure area"),
                "O teste falhou! A mensagem de sucesso no login não foi encontrada.");
    }

}