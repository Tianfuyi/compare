package com.cx.service.driver.excel.Module;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScenarioTemplate {

    private String scenariodescription;  // excel 名称描述

    private LinkedHashMap stepTemplateMap;   // 存放代码  中文名称


    public ScenarioTemplate(){

        scenariodescription = "";
        stepTemplateMap = new LinkedHashMap();

    }


    public String getScenariodescription() {
        return scenariodescription;
    }
    public void setScenariodescription(String scenariodescription) {
        this.scenariodescription = scenariodescription;
    }

    public Map<String, String> getStepTemplateMap() {
        return stepTemplateMap;
    }
    public void setStepTemplateMap(String scope, String value) {
        this.stepTemplateMap.put(scope, value);
    }

}
