package com.cx.service.driver.xmls.Module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.LinkedHashMap;
import java.util.Map;

public class XmlTemplate {
    private static final Logger logger = LoggerFactory.getLogger(XmlTemplate.class);
    private String xmldescription;  // excel 名称描述
    private LinkedHashMap xmlTemplateMap;   // 存放代码  中文名称

    public XmlTemplate(){
        xmldescription = "";
        xmlTemplateMap = new LinkedHashMap();

    }

    public String getScenariodescription() {
        return xmldescription;
    }
    public void setScenariodescription(String xmldescription) {
        this.xmldescription = xmldescription;
    }

    public Map<String, String> getxmlTemplateMap() {
        return xmlTemplateMap;
    }
    public void setStepTemplateMap(String scope, String value) {

     if(this.xmlTemplateMap.containsKey(scope)){
         logger.error("错误原因：xml自检 key值重复   请检查表："+getScenariodescription()+"  key值:"+scope+" 重复 ");
         System.out.println("错误原因：xml key值重复   请检查表："+getScenariodescription()+"  key值:"+scope+" 重复 ");
         this.xmlTemplateMap.put(scope+"_repeat", value);

        }else
        this.xmlTemplateMap.put(scope, value);
    }

}
