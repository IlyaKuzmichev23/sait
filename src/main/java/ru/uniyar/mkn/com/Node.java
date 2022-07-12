package ru.uniyar.mkn.com;

import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class Node {
    int id;
    String name;
    List<Node> children;

    public Node(String name) {
        this.name = name;
        this.id = getRandomNumber(1, 1000);
        this.children = new ArrayList<>();
    }
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void add(Node child) {
        this.children.add(child);
    }

    public void delll(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(name)) {
                children.remove(i);
            }
        }
    }

    public void dellOC(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(name)) {
                for (int j = i + 1; i < children.size(); j++) {
                    children.remove(j);
                }
            }
        }
    }

    public boolean search(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(name)) {
                return true;
            } else
                return false;
        }
        return false;
    }

    public void rename(String name, String rename) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(name)) {
                children.get(i).setName(rename);
            }
        }
    }
    public String printstr()
    {
        return printstr("", 0);
    }
    private String printstr(String s, int t) {
        for (int i = 0; i < t; i++) {
            s += " ";
        }
        s += name;
        s += "\n";
        if (children.size() == 0) {
            return s;
        } else {
            for (int i = 0; i < children.size(); i++) {
                s += children.get(i).printstr("", t+1);
            }
            return s;
        }
    }

    private String printstr4()
    {
        String result = "";
        result+= "<li>";
        result+= name + " <a href=\"edit/" + id + "\">Редактировать</a> </li>";;
        result+= "</li>";
        for(int i = 0; i<children.size(); i++)
        {
            result += children.get(i).printToHtml();
        }
        result+= "</li>";
        return result;
    }
    public String printToHtml() {
        String result = "<ul>";
        result+= printstr4();
        result+="</ul>";
        return result;
    }
    public String gett(int itemId)//Найти узел с идент. itemID
    {
        String result = "";
        if(id == itemId){
            result = name;
            return result;
        }
        for(int i = 0; i<children.size(); i++)
        {
            if(children.get(i).id == itemId) {
                return children.get(i).name;
            }
            result = children.get(i).gett(itemId);
            if(!result.isEmpty())
                return result;
        }
        return result;
    }
    public void sett(int itemId,String itemValue)
    {
        for(int i = 0; i<children.size(); i++)
        {
            if (children.get(i).getName().equals(itemValue)) {
                children.get(i).setName(itemValue);
            }
            children.get(i).sett(itemId, itemValue);
        }
    }
}