# SauceDemo E2E Automation Testing Project

Проект автоматизированного тестирования веб-приложения [SauceDemo](https://www.saucedemo.com/) с использованием **Java**, **Selenium WebDriver**, **JUnit 5**, **Maven** и **Allure Report**. Поддерживается запуск тестов в браузерах **Google Chrome** и **Microsoft Edge**.

---

## 🛠 Технологии и стек
* **Java 17**
* **Selenium WebDriver (v4.23.0)** — инструмент для управления браузерами
* **JUnit 5 (Jupiter)** — фреймворк для написания и выполнения тестов
* **Maven** — система управления проектом и сборки
* **Allure Report** — инструмент для генерации красивых и наглядных отчетов о результатах тестирования
* **Page Object Model (POM)** — паттерн проектирования для разделения логики страниц и тестов

---

## 📁 Структура проекта
```text
FinalTest/
│
├── .mvn/                      # Настройки Maven
├── drivers/                   # Драйверы для браузеров (например, msedgedriver.exe)
├── src/
│   ├── main/java/com/saucedemo/pages/     # Классы страниц (Page Objects)
│   └── test/java/com/saucedemo/tests/     # Тестовые классы (BaseTest, SauceDemoTest)
│
├── allure-results/            # Временные результаты для отчетов Allure
├── pom.xml                    # Конфигурация Maven и зависимости
└── README.md                  # Документация проекта