package com.cx.service.server;

import com.cx.service.driver.excel.Module.ScenarioDataTemplate;
import com.cx.service.driver.excel.Module.ScenarioTemplate;
import com.cx.service.driver.xmls.Module.XmlDataTemplate;
import com.cx.service.driver.xmls.Module.XmlTemplate;
import com.cx.service.util.DataParse;
import com.cx.service.util.MapCompare;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelXMLComPareServer {
    private static final Logger logger =LoggerFactory.getLogger(ExcelXMLComPareServer.class);
    private  List<ScenarioTemplate> excelTempatelist;
    private List<XmlTemplate> xmlTemplateList;
    Map<String,String> checkValue;
    private String excelDesc;
    private String xmlDesc;
    Map<String,String> xmlMap;
    Map<String,String> excelMap;
    List listcontains = new ArrayList();
    List listequals = new ArrayList();
    List<XmlTemplate> listnotMatch = new ArrayList();
    Boolean flag=false;
    Boolean flagChecKNonlyMap=false;
    int xmlMapLength;
    int excelMapLength;
   MapCompare compare= new MapCompare();
   @Test
public void comparEXCL_XML() throws Exception {


   excelTempatelist=  ScenarioDataTemplate.getScenarios(DataParse.GetProperties("EXCEL_PATH"),DataParse.GetProperties("EXCEL_SHEET"));  //  excel
    xmlTemplateList=    new XmlDataTemplate().testGetRoot();  // xml


    for(XmlTemplate xmlTemplate:xmlTemplateList){
        xmlDesc= xmlTemplate.getScenariodescription();

            for(ScenarioTemplate scenarioTemplate:excelTempatelist){  //
                excelDesc=scenarioTemplate.getScenariodescription();
                xmlMap=xmlTemplate.getxmlTemplateMap();
                excelMap=scenarioTemplate.getStepTemplateMap();

                if (xmlDesc.equals(excelDesc)){

                    listequals.add(excelDesc+"_"+xmlDesc);
                    if (xmlMap.size()==excelMap.size()){  // 对比map 长度
                        Boolean resultCompare=    new MapCompare().compare1(xmlMap,excelMap);

                        if(resultCompare==false){
                            logger.error("--------------------------");
                            logger.error("xml 表名："+xmlDesc +" excel 表名：" +excelDesc+" 匹配结果：");
                            logger.error(xmlDesc+" : "+xmlMap);
                            logger.error(excelDesc+" : "+excelMap);
                            logger.error("错误原因：xml内容错误  请检查表：" +xmlDesc);

                            logger.error("--------------------------");
                            System.out.println("=========相同表=======");
                            System.out.println("错误原因：xml内容匹配错误  请检查表：" +xmlDesc);
                            System.out.println(xmlMap);
                            System.out.println(excelMap);
                            System.out.println("========相同表========");
                        }

                    }else{   // 长度不同
                        System.out.println("========相同表 长度不匹配========");
                        logger.error("--------------------------");
                        logger.error("xml 表名："+xmlDesc +" excel 表名：" +excelDesc+" 匹配结果：");
                        logger.error(xmlDesc+" : "+xmlMap);
                        logger.error(excelDesc+" : "+excelMap);
                        logger.error("错误原因：xml子项数量不匹配  请检查表：" +xmlDesc);

                        logger.error("--------------------------");
                        System.out.println(xmlDesc);
                        System.out.println(xmlTemplate.getxmlTemplateMap());
                        System.out.println(excelDesc);
                        System.out.println(scenarioTemplate.getStepTemplateMap());
                        System.out.println("=========相同表 长度不匹配=======");
                    }
                    flag =true;
                    break;
                }else if(excelDesc.contains(xmlDesc)|xmlDesc.contains(excelDesc)){

                  listcontains.add(excelDesc+"_"+xmlDesc);
                    if (xmlMap.size()==excelMap.size()){  // 对比map 长度
                        Boolean resultCompare=    new MapCompare().compare1(xmlMap,excelMap);

                        if(resultCompare==false){
                            System.out.println("=========描述相似表=======");
                            System.out.println("错误原因：xml内容错误  请检查表：" +xmlDesc);
                            System.out.println(xmlMap);
                            System.out.println(excelMap);
                            System.out.println("========描述相似表========");
                            logger.error("--------------------------");
                            logger.error("xml 表名："+xmlDesc +" excel 表名：" +excelDesc+" 匹配结果：");
                            logger.error(xmlDesc+" : "+xmlMap);
                            logger.error(excelDesc+" : "+excelMap);
                            logger.error("错误原因：xml内容匹配错误  请检查表：" +xmlDesc);

                            logger.error("--------------------------");
                        }

                    }else{   // 长度不同

                        System.out.println("========描述相似表 长度不匹配========");
                        System.out.println(xmlDesc);
                        System.out.println(xmlTemplate.getxmlTemplateMap());
                        System.out.println(excelDesc);
                        System.out.println(scenarioTemplate.getStepTemplateMap());
                        System.out.println("=========描述相似表 长度不匹配=======");
                        logger.error("--------------------------");
                        logger.error("xml 表名："+xmlDesc +" excel 表名：" +excelDesc+" 匹配结果：");
                        logger.error(xmlDesc+" : "+xmlMap);
                        logger.error(excelDesc+" : "+excelMap);
                        logger.error("错误原因：xml子项数量不匹配  请检查表：" +xmlDesc);

                        logger.error("--------------------------");
                    }
                    flag =true;
                    break;
                }else {
                        flag =false;

                }

        }
        if(flag==false){
            //listnotMatch.add(xmlDesc);
            listnotMatch.add(xmlTemplate);

        }
    }
    System.out.println("***************************");
    System.out.println(listequals);
    System.out.println(listcontains);
    System.out.println(listnotMatch);
    for(XmlTemplate xmlTemplate:listnotMatch){
        System.out.println(xmlTemplate.getScenariodescription());
    }
    System.out.println("***************************");

    // 描述未匹配
    for(XmlTemplate xmlTemplate:listnotMatch){
        if(listnotMatch==null){
            break;
        }
        xmlDesc=xmlTemplate.getScenariodescription();
        xmlMap=xmlTemplate.getxmlTemplateMap();
         xmlMapLength =xmlTemplate.getxmlTemplateMap().size();
        for(ScenarioTemplate scenarioTemplate:excelTempatelist){
            excelDesc=scenarioTemplate.getScenariodescription();
             excelMapLength =scenarioTemplate.getStepTemplateMap().size();
             if(xmlMapLength==excelMapLength){

                 excelMap=scenarioTemplate.getStepTemplateMap();
                flagChecKNonlyMap= compare.compare1(xmlMap,excelMap);
                 if(flagChecKNonlyMap==true){   // 匹配成功则跳出循环 否则一直匹配到最后map
                     System.out.println(xmlDesc+" 匹配成功 "+excelDesc+" 下一个");
                     break;
                 }
             }

        }
        if(flagChecKNonlyMap==false){
            System.out.println(xmlDesc+" 未匹配成功");
            logger.error("xml 表："+xmlDesc+" 未找到匹配项 请核实 （原因： 匹配子项数量或内容不符！）");
        }
    }
}


}
