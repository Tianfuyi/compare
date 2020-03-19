package com.cx.service.driver.xmls;


import com.cx.service.driver.xmls.Module.XmlTemplate;
import com.cx.service.util.Constant;
import com.cx.service.util.DataParse;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParse {
    public List<XmlTemplate> getNodes(Element node){
        System.out.println("--------------------");
        String  value="";
        String  name="";
        String tablename="";
        String mkey="";
        String mmapping="";
        List<XmlTemplate> xmlTemplates = new ArrayList();
        //递归遍历当前节点所有的子节点
        List<Element> listElement=node.elements();//所有一级子节点的list
        for(Element e:listElement){//遍历所有一级子节点
            //     System.out.println("当前节点名称："+e.getName());
            XmlTemplate xmlTemplate = new XmlTemplate();
            List<Attribute> list =e.attributes();
            for(Attribute att:list){

                if(e.getName().equals(Constant.ElELEMENT_ROOT_TAG)){
                    if(att.getName().equals(Constant.ElELEMENT_ROOT_ATTR)){
                        xmlTemplate.setScenariodescription(att.getValue());   // 名称添加
                    }

                }
            }


            List<Element> cListElement =e.elements();
            for(Element celement:cListElement){
                //       System.out.println("----");
                //    System.out.println("子节点名称："+celement.getName());
                List<Attribute> clist =celement.attributes();
                for(Attribute catt:clist){
                    if(celement.getName().equals(Constant.ElELEMENT_CHILD_TAG)){
                        if(catt.getName().equals(Constant.ElELEMENT_CHILD_KEY)){
                            mkey=catt.getValue();
                        }
                        if(catt.getName().equals(Constant.ElELEMENT_CHILD_VALUE)){
                            mmapping=catt.getValue();
                        }
                    }


                    //   System.out.println("子属性名称："+catt.getName()+"子属性值："+catt.getValue());
                }

                xmlTemplate.setStepTemplateMap(mkey,mmapping);
            }
            xmlTemplates.add(xmlTemplate);


        }
    /*    for(int i=0;i<xmlTemplates.size();i++){
            HashMap<String,String> map = (HashMap) xmlTemplates.get(i).getxmlTemplateMap();
            System.out.println("======================================");
            System.out.println("名称 "+xmlTemplates.get(i).getScenariodescription());
            for(HashMap.Entry<String,String> entry:map.entrySet()){

                System.out.println("");
                System.out.println("key "+entry.getKey() +" value: "+entry.getValue());
            }
        }
        System.out.println(xmlTemplates.size());*/
        return xmlTemplates;
    }

    public List<XmlTemplate> testGetRoot() throws Exception{
        SAXReader sax=new SAXReader();//创建一个SAXReader对象
        File xmlFile=new File(DataParse.GetProperties("XML_PATH"));//根据指定的路径创建file对象
        Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root=document.getRootElement();//获取根节点
        return getNodes(root);//从根节点开始遍历所有节点
    }

    public static void main(String[] args) throws Exception {
        XmlParse com = new XmlParse();
        com.testGetRoot();

    }
}
