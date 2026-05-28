import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Collections;

public class TestesSistemaTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();

        // Remove a barra de "teste automatizado" e esconde as flags de automação
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new org.openqa.selenium.chrome.ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        //Fecha o navegador após o fim do teste
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
                break; // Sai do for assim que conseguir clicar
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
        driver.findElement(org.openqa.selenium.By.id("username")).sendKeys("tomsmith");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("SuperSecretPassword!");

        // 3. Clica no botão de Login
        driver.findElement(org.openqa.selenium.By.cssSelector("button[type='submit']")).click();

        // 4. Verifica se a mensagem de sucesso
        String mensagemAlerta = driver.findElement(org.openqa.selenium.By.id("flash")).getText();
        org.junit.jupiter.api.Assertions.assertTrue(mensagemAlerta.contains("You logged into a secure area"),
                "O teste falhou! A mensagem de sucesso no login não foi encontrada.");
    }

// ========== EXERCÍCIO 3 - MENU PRINCIPAL ==========

    @Test
    public void testeMenuMeuIFMG() {
        driver.get("https://www.ifmg.edu.br/sabara");
        driver.findElement(org.openqa.selenium.By.linkText("Meu IFMG")).click();
        String titulo = driver.getTitle();
        org.junit.jupiter.api.Assertions.assertFalse(titulo.isBlank(),
                "Falhou! A página Meu IFMG não carregou.");
    }

    @Test
    public void testeMenuSEI() {
        driver.get("https://www.ifmg.edu.br/sabara");
        driver.findElement(org.openqa.selenium.By.linkText("SEI")).click();
        String titulo = driver.getTitle();
        org.junit.jupiter.api.Assertions.assertFalse(titulo.isBlank(),
                "Falhou! A página SEI não carregou.");
    }

    @Test
    public void testeMenuSUAP() {
        driver.get("https://www.ifmg.edu.br/sabara");
        driver.findElement(org.openqa.selenium.By.linkText("SUAP")).click();
        String titulo = driver.getTitle();
        org.junit.jupiter.api.Assertions.assertFalse(titulo.isBlank(),
                "Falhou! A página SUAP não carregou.");
    }

    @Test
    public void testeMenuContato() {
        driver.get("https://www.ifmg.edu.br/sabara");
        driver.findElement(org.openqa.selenium.By.linkText("Contato")).click();
        String titulo = driver.getTitle();
        org.junit.jupiter.api.Assertions.assertTrue(
                titulo.contains("Contato") || titulo.contains("IFMG") || titulo.contains("Instituto Federal"),
                "Falhou! Título encontrado: " + titulo);
    }

    @Test
    public void testeMenuWebmail() {
        driver.get("https://www.ifmg.edu.br/sabara");
        driver.findElement(org.openqa.selenium.By.linkText("Webmail")).click();
        // Verifica que a URL mudou, indicando que o link funcionou
        String urlAtual = driver.getCurrentUrl();
        org.junit.jupiter.api.Assertions.assertFalse(
                urlAtual.equals("https://www.ifmg.edu.br/sabara") || urlAtual.isBlank(),
                "Falhou! O link Webmail não redirecionou para nenhuma página.");
    }

    @Test
    public void testeBuscaNoPortal() {

        driver.get("https://www.ifmg.edu.br/sabara/@@search?SearchableText=cursos");

        // Aguarda a página carregar
        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        // Verifica se o título da página carregou
        String titulo = driver.getTitle();
        org.junit.jupiter.api.Assertions.assertFalse(titulo.isBlank(),
                "Falhou! A página de resultados não carregou.");

        // Verifica se a seção de resultados está visível na página
        org.openqa.selenium.WebElement resultados = wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(
                        org.openqa.selenium.By.cssSelector("#content, #region-content, .searchResults")));

        org.junit.jupiter.api.Assertions.assertTrue(resultados.isDisplayed(),
                "Falhou! O campo de resultados da busca não está visível.");
    }
}