package com.cx.service.driver.xmls.Module;

import com.cx.service.util.DataParse;
import com.cx.service.driver.xmls.XmlParse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class XmlDataTemplate {
    public List<XmlTemplate> testGetRoot() throws Exception{
        SAXReader sax=new SAXReader();//创建一个SAXReader对象
        File xmlFile=new File(DataParse.GetProperties("XML_PATH"));//根据指定的路径创建file对象
        Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root=document.getRootElement();//获取根节点
        XmlParse xmlParse = new XmlParse();
        return   xmlParse.getNodes(root);//从根节点开始遍历所有节点

    }

    public static void main(String[] args) {
        XmlDataTemplate xml = new XmlDataTemplate();
        try {
            List<XmlTemplate> xmlTemplates=    xml.testGetRoot();

            for(int i=0;i<xmlTemplates.size();i++){
                HashMap<String,String> map = (HashMap) xmlTemplates.get(i).getxmlTemplateMap();
                System.out.println("======================================");
                System.out.println("名称 "+xmlTemplates.get(i).getScenariodescription());
                for(HashMap.Entry<String,String> entry:map.entrySet()){

                    System.out.println("");
                    System.out.println("key "+entry.getKey() +" value: "+entry.getValue());
                }
            }
            System.out.println(xmlTemplates.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
