package ru.uniyar.mkn.com;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Контроллер отвечающий за представление списка.
 */
@Path("/")
public class ListPresentationController {
    private final List<String> list;
    private final Node root;

    /**
     * Запоминает список, с которым будет работать.
     * @param list список, с которым Rбудет работать контроллер.
     */
    public ListPresentationController(List<String> list, Node root) {

        this.list = list;
        this.root = root;
    }

    /**
     * Пример вывода простого текста.
     */
    @GET
    @Path("example")
    @Produces("text/plain")
    public String getSimpleText() {
        return "hello world111";
    }

    /**
     * Выводит HTML-страницу со списком, ссылками на страницы редактирования и копкой добавления записи.
     * @return HTML-страница со списком.
     */
    @GET
    @Path("100")
    @Produces("text/html")
    public String getList() {
        String result =
                "<html>" +
                "  <head>" +
                "    <title>Вывод списка</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Список</h1>" +
                "    <ul>";
        for (int i = 0; i < list.size(); i++) {
            String listItem = list.get(i);
            result += "<li>" + listItem + " <a href=\"edit/" + i + "\">Редактировать</a> </li>";
        }
        result += "    </ul>" +
                "      <br/>" +
                "      <form method=\"post\" action=\"add_random_item\">" +
                "        <input type=\"submit\" value=\"Add random item\"/>" +
                "      </form>" +
                "  </body>" +
                "</html>";
        return result;
    }

    /**
     * Пример обработки POST запроса.
     * Добавляет одну случайную запись в список и перенаправляет пользователя на основную страницу со списком.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("add_random_item")
    @Produces("text/html")
    public Response addRandomItem() {
        list.add("zzz");
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Выводит страничку для редактирования одного элемента.
     * @param itemId индекс элемента списка.
     * @return страничка для редактирования одного элемента.
     */
    @GET
    @Path("/edit/{id}")
    @Produces("text/html")
    public String getEditPage(@PathParam("id") int itemId) {
        String listItem = root.gett(itemId);
        String result =
                "<html>" +
                        "  <head>" +
                        "    <title>Редактирование элемента списка</title>" +
                        "  </head>" +
                        "  <body>" +
                        "    <h1>Редактирование элемента списка</h1>" +
                        "    <form method=\"post\" action=\"/edit/" + itemId + "\">" +
                        "      <p>Значение</p>" +
                        "      <input type=\"text\" name=\"value\" value=\"" + listItem +"\"/>" +
                        "      <input type=\"submit\"/>";
        result +=
                "            </form>" +
                "  </body>" +
                "</html>";
        return result;
    }

    /**
     * Редактирует элемент списка на основе полученных данных.
     * @param itemId индекс элемента списка.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/edit/{id}")
    @Produces("text/html")
    public Response editItem(@PathParam("id") int itemId, @FormParam("value") String itemValue) {
        root.sett(itemId, itemValue);
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Пример вывода вложенного списка.
     */
    @GET
    @Path("nested_list")
    @Produces("text/html")
    public String getNestedListExample() {
        return "<html>" +
                "  <head>" +
                "    <title>Hello world</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Hello world</h1>" +
                "    <ul>" +
                "      <li>1</li>" +
                "      <li>2</li>" +
                "      <li>3" +
                "        <ul>" +
                "          <li>3.1</li>" +
                "        </ul>" +
                "      </li>" +
                "    </ul>" +
                "  </body>" +
                "</html>";
    }
    @GET
    @Path("/")
    @Produces("text/html")
    public String geTree() {
        return "<html>" +
                "  <head>" +
                "    <title>Вывод дерева</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Вывод дерева</h1>" +
                root.printToHtml()+
                "  </body>" +
                "</html>";
    }
}
