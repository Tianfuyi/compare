package com.cx.service.driver.excel.Module;

import com.cx.service.util.DataParse;
import com.cx.service.driver.excel.ExcelParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScenarioDataTemplate extends BaseTemplate {


    public static void main(String[] args) throws IOException {
      List<ScenarioTemplate> list=  ScenarioDataTemplate.getScenarios(DataParse.GetProperties("EXCEL_PATH"),DataParse.GetProperties("EXCEL_SHEET"));
        System.out.println(list.size());
      for(int i=0;i<list.size();i++){
          Map<String, String> stepTemplateMap= list.get(i).getStepTemplateMap();
          System.out.println("名称："+list.get(i).getScenariodescription());
          for(Map.Entry<String, String> entry:stepTemplateMap.entrySet()){
              System.out.println("key: "+entry.getKey()+" value: "+entry.getValue());
          }
          System.out.println("---------------------------");
      }
       /* System.out.println(list.get(1).getStepTemplateMap());
       Map map = list.get(1).getStepTemplateMap();
        System.out.println(map.get("1"));*/
    }
    /**
     * 获取 excel 全部数据集合
     *
     * @param filepath
     * @param sheetname
     * @return
     */
    public static List<ScenarioTemplate> getScenarios(String filepath, String sheetname) {
        try {
            if (filepath.isEmpty() || sheetname.isEmpty()) {
                return null;
            }

            //声明场景集合
            List<ScenarioTemplate> scenarioTemplates = new ArrayList<>();
            ////LogWriter.debug(ScenarioDataTemplate.class, "Initialize scenario list success");

            //声明并初始化一个excel文件
            ExcelParse ep = new ExcelParse(filepath);
            //LogWriter.debug(ScenarioDataTemplate.class, "Create Workbook Factory success");

            //设置当前工作表格
            if (!ep.setWorkSheet(sheetname)) {
                throw new Exception(String.format("Set Work sheet failed, sheet name = %s", sheetname));
            }

            //获取当前sheet可用总行数
            int rows = ep.getRows();
            //LogWriter.debug(ScenarioDataTemplate.class, "get sheet total row number success, value = " + String.valueOf(rows));

            //设置起始查询位置
            int query_r = 0;
            int query_c = 0;

            //开始查询数据
            while (query_r < rows) {
                if (ep.isMerged(query_r, query_c)) {
                    //声明一个场景
                    ScenarioTemplate scenarioTemplate = new ScenarioTemplate();
                    //LogWriter.debug(ScenarioDataTemplate.class, "Begin to get scenario information");

                    //获取该Merge相关信息
                    int start_r = ep.getCellStartRow(query_r, query_c);
                    int end_r = ep.getCellEndRow(query_r, query_c);
                    //LogWriter.debug(ScenarioDataTemplate.class, "Get Merge basic information success");




                    //获取场景详细描述
                    scenarioTemplate.setScenariodescription(ep.getCellValue(query_r, query_c).trim());
                    //LogWriter.debug(ScenarioDataTemplate.class, "Get scenario description success, value = " + ep.getCellValue(query_r, query_c));

                    //获取场景详细步骤
                    query_c += 1;
                    Map<String, String> stepTemplateMap = getMaps(ep, start_r, end_r, query_c);
                    if (stepTemplateMap != null) {

                        for (Map.Entry<String, String> entry : stepTemplateMap.entrySet()) {
                            scenarioTemplate.setStepTemplateMap(entry.getKey(), entry.getValue());
                            //LogWriter.debug(ScenarioDataTemplate.class, "Add a StepTemplate to map");
                        }
                    } else {
                        //LogWriter.debug(ScenarioDataTemplate.class, "Do not find StepTemplate");
                    }

                    scenarioTemplates.add(scenarioTemplate);

                    query_r = end_r + 1;
                    query_c = 0;
                } else {
                    query_r += 1;
                }
            }

            return scenarioTemplates;
        } catch (Exception ex) {
            //LogWriter.error(ScenarioDataTemplate.class, String.format("get Scenarios method execute exception, value = %s", ex.getMessage()));
            return null;
        }
    }

    /**
     * 获取表信息
     *
     * @param ep
     * @param start_r
     * @param end_r
     * @param start_c
     * @return
     */
    private static Map<String, String> getMaps(ExcelParse ep, int start_r, int end_r, int start_c) {
        try {
            Map<String, String> stepTemplateMap = new LinkedHashMap();

            //获取查询的总行数
            int rows = end_r - start_r + 1;

            //获取起始查询位置
            int query_r = start_r;
            int query_c = start_c;

            //LogWriter.debug(ScenarioDataTemplate.class, "Begin to parse com.zetyun.apitest.steps");

            //对数据进行遍历
            while (query_r < rows + start_r) {
                if (ep.isMerged(query_r, query_c)) {
                    int start_row = ep.getCellStartRow(query_r, query_c);
                    int end_row = ep.getCellEndRow(query_r, query_c);
                    int end_column = ep.getCellEndColumn(query_r, query_c);
                    //LogWriter.debug(ScenarioDataTemplate.class, "Get Merge basic information success");

                    //获取Step
                    String code = ep.getCellValue(query_r, query_c).trim().replaceAll(" ","").replaceAll("\r|\n","");

                    String value = ep.getCellValue(query_r,end_column+1).trim().replaceAll(" ","").replaceAll("\r|\n","");
               //     String value1 = ep.getCellValue(query_r,query_c+2);
                    //LogWriter.debug(ScenarioDataTemplate.class, "Get Step value success, value = " + step);

                    //获取步骤信息
                    /*StepTemplate stepTemplate = getStep(ep, start_row, end_row, end_column + 1);*/
                    //LogWriter.debug(ScenarioDataTemplate.class, "Get StepTemplate value success");

                    //添加一个步骤
                    if(!code.contains("代码")) {
                        stepTemplateMap.put(code, value);
                    }
                    //LogWriter.debug(ScenarioDataTemplate.class, "Set StepTemplate success");

                    //增加遍历的起始行数
                    query_r = end_row + 1;
                } else {
                    query_r += 1;
                }
            }

            return stepTemplateMap;
        } catch (Exception ex) {
            //LogWriter.error(ScenarioDataTemplate.class, String.format("get Steps method execute exception, value = %x", ex.getMessage()));
            return null;
        }
    }


}
