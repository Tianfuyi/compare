package com.cx.service.driver.xmls;


import com.cx.service.driver.xmls.Module.XmlTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlCheck {

    public Boolean checkXML(List<XmlTemplate> xmlTemplateList){

        List<String> listcheckDesc = new ArrayList<>();

        for(XmlTemplate xmlTemplate:xmlTemplateList){
            // xml 自检 重复表
            if(!listcheckDesc.contains(xmlTemplate.getScenariodescription())){
                listcheckDesc.add(xmlTemplate.getScenariodescription());
            }else{
                System.out.println("错误原因：xml表名称重复  请检查表："+xmlTemplate.getScenariodescription());
            }
            List<String> listcheckmapping = new ArrayList<>();
            Map<String,String> checkValue= xmlTemplate.getxmlTemplateMap();
            // xml 自检  重复key
            for(String value:checkValue.values()){
                if(!listcheckmapping.contains(value)){
                    listcheckmapping.add(value);
                }else {
                    System.out.println("错误原因：xml mapping重复  请检查表："+xmlTemplate.getScenariodescription() +" mapping 值："+value+" 重复");
                }

            }

        }


        return true;
    }
}
