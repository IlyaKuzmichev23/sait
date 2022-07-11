package ru.uniyar.mkn.com;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

/**
 * Консольное приложение.
 */
public class Application {

    public static void main(String[] argv) {
        startWebServer();
    }

    /**
     * Запускает web-сервер. По окончании работы требуется ручная остановка процесса.
     */
    private static void startWebServer() {
        UndertowJaxrsServer server = new UndertowJaxrsServer().start();
        server.deploy(WebApplication.class);
        System.out.println("Сервер запущен: http://localhost:8081/");
    }
}
