package com.cx.service.util;

import java.util.Map;

public class MapCompare {

    public boolean compare(Map<String,String> xmlMap,Map<String,String> excelMap){

        {  // 对比map 长度

            boolean m2_c_m1=true;
            for(Map.Entry e1: xmlMap.entrySet()){         // 比对key
                if(!excelMap.containsKey(e1.getKey())){
                    m2_c_m1=false;
                    break;
                }
                if(!xmlMap.get(e1.getKey()).equals(excelMap.get(e1.getKey()))){  // 比对value
                    m2_c_m1=false;
                    break;
                }
            }
            boolean m1_c_m2=true;
            for(Map.Entry e2: excelMap.entrySet()){
                if(!xmlMap.containsKey(e2.getKey())){
                    m1_c_m2=false;
                    break;
                }
                if(!xmlMap.get(e2.getKey()).equals(excelMap.get(e2.getKey()))){
                    m1_c_m2=false;
                    break;
                }
            }

            boolean m2_c_m1_1=true;
            for(Map.Entry e1: xmlMap.entrySet()){
                String xmlskey= e1.getKey().toString();
                String exlskey ="";


                if(!excelMap.containsKey(e1.getValue())){
                    m2_c_m1_1=false;
                    break;
                }

                for (Map.Entry<String, String> m :excelMap.entrySet())  {
                    if (m.getValue().equals(xmlskey)) {
                        exlskey = m.getKey();
                        if(exlskey.equals("")){
                            m2_c_m1_1=false;
                            break;
                        }
                    }}
                if(!xmlskey.equals(exlskey)){
                    m2_c_m1_1=false;
                    break;
                }
            }
            boolean m1_c_m2_2=true;
            for(Map.Entry e2: excelMap.entrySet()){
                if(!xmlMap.containsKey(e2.getValue())){
                    m1_c_m2_2=false;
                    break;
                }
            }

            boolean resultCompare= (m2_c_m1&m1_c_m2)|(m2_c_m1&m1_c_m2);

            return resultCompare;

        }

    }
    public boolean compare1(Map<String,String> xmlMap,Map<String,String> excelMap){

        boolean resultCompare=true;
        boolean comFlag=true;
        boolean RcomFlag=true;
        for(Map.Entry<String, String> entry1:xmlMap.entrySet()){
            String m1value = entry1.getValue() == null?"":entry1.getValue();
            String m2value = excelMap.get(entry1.getKey())==null?"":excelMap.get(entry1.getKey());
            if (!m1value.equals(m2value)) {//若两个map中相同key对应的value不相等
                //其他操作...
                comFlag=false;
                break;
            }
        }


        //  xml value 对应excel key
        for(Map.Entry<String, String> entry1:xmlMap.entrySet()){
            String xmlvalue = entry1.getValue() == null?"":entry1.getValue();
            if(xmlvalue==null){
                RcomFlag=false;
                break;
            }
           if(!excelMap.containsKey(xmlvalue)){
               RcomFlag=false;
               break;
           }

          //  String m2value = excelMap.get(entry1.getKey())==null?"":excelMap.get(entry1.getKey());

        }


        //  xml value 对应excel key
        for(Map.Entry<String, String> entry1:excelMap.entrySet()){
            String xmlvalue = entry1.getValue() == null?"":entry1.getValue();
            if(xmlvalue==null){
                RcomFlag=false;
                break;
            }
            if(!xmlMap.containsKey(xmlvalue)){
                RcomFlag=false;
                break;
            }

            String m2value = excelMap.get(entry1.getKey())==null?"":excelMap.get(entry1.getKey());

        }

        return resultCompare=comFlag|RcomFlag;



    }
}
