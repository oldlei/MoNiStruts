package com.dy.java17;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Iterator;

public class Wjjx {
    private String path;

    public Wjjx(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public HashMap<String,Ban_sj> qz(){
        HashMap<String,Ban_sj> fh = new HashMap<>();
        SAXReader dom = new SAXReader();

        try{
            Document read = dom.read(this.getClass().getResource("/" + path).getPath());
            Element aPackage = read.getRootElement().element("package");
            Iterator<Element> elementIterator = aPackage.elementIterator();
            while (elementIterator.hasNext()){
                Element next = elementIterator.next();
                String actionName=next.attribute("name").getValue();
                String classname = next.attribute("class").getValue();
                Iterator<Element> elementIterator1 = next.elementIterator();
                HashMap<String,String> temp = new HashMap<>();
                while(elementIterator1.hasNext()){
                    Element next1 = elementIterator1.next();
                    String attrvalue=next1.attribute("name").getValue();
                    String value = next1.getText();
                    temp.put(attrvalue,value);
                }
                fh.put(actionName,new Ban_sj(classname,temp));
            }
        }catch (Exception e){
            System.out.println("解析异常");
        }





        return fh;
    }
}
