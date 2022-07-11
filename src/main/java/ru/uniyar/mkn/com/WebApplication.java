package ru.uniyar.mkn.com;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Web-приложение в котором регистрируются все ресурсы.
 */
public class WebApplication extends Application {

    private List<String> list = new ArrayList<>();
    private Node root;


    public WebApplication() {
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        root = new Node("Корень");
        Node child = new Node("Лист");
        root.add(child);
        root.add(new Node("Ствол"));
        Node kid = new Node("Нечто");
        child.add(kid);
    }

    /**
     * Возвращает список всех ресурсов web-приложения.
     * @return список всех ресурсов web-приложения.
     */
    @Override
    public Set<Object> getSingletons() {
        Set<Object> resources = new HashSet<>();
        resources.add(new ListPresentationController(list, root));
        return resources;
    }
}
