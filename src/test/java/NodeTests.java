import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.uniyar.mkn.com.Node;
import java.io.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Struct;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTests {
    @Test
    void createNode() {
        Node node = new Node("Корень");
        assertEquals("Корень", node.getName());
    }

    @Test
    void addNode() {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.add(child);
        assertEquals(1, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
    }

    @Test
    void deleteNode() {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.add(child);
        assertEquals(1, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
        child.delll("Лист");
    }

    @Test
    void deleteChildrens() {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        Node kid = new Node("Ствол");
        root.add(child);
        root.add(kid);
        assertEquals(2, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
        assertEquals("Ствол", root.getChildren().get(1).getName());
        root.dellOC("Корень");
    }

    @Test
    void search() {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        Node kid = new Node("Ствол");
        root.add(child);
        root.add(kid);
        assertEquals(2, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
        assertEquals("Ствол", root.getChildren().get(1).getName());
        root.search("Ствол");
    }

    @Test
    void pereim() {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        Node kid = new Node("Ствол");
        root.add(child);
        root.add(kid);
        assertEquals(2, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
        assertEquals("Ствол", root.getChildren().get(1).getName());
        root.rename("Ствол", "Почка");
    }

    @Test
    void printstr() throws IOException {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        Node kid = new Node("Ствол");
        root.add(child);
        root.add(kid);
        assertEquals(2, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
        assertEquals("Ствол", root.getChildren().get(1).getName());
        System.out.println(root.printstr());
    }

    @Test
    void printfile() throws IOException {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        Node kid = new Node("Ствол");
        root.add(child);
        root.add(kid);
        assertEquals(2, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
        assertEquals("Ствол", root.getChildren().get(1).getName());
        String content = root.printstr();
        String path = "file.txt";
        Files.write(Paths.get(path), content.getBytes());
    }
}
