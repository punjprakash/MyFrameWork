package com.qait.tests;

import java.util.Hashtable;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import com.qait.automation.utils.Xls_Reader;
import com.qait.automation.utils.YamlReader;


public class ASR_TACancleFactory {
	String appName= "ASR";
	String excelFilePath=Xls_Reader.setdataFilePath(appName);
	Xls_Reader dataXLS; 
	String testCaseName = "ASR_TACancle";
	
	
    @Factory(dataProvider = "exceldataprovider")
    
    //Removed the constructor parameter to debug
    // public Object[] factoryMethod(Hashtable<String, String> usereInfoMap) {
        //return new Object[]{new ASR_TACancle(usereInfoMap)};
    //}

  /*  @DataProvider(name = "yamldataprovider", parallel = true)
    public Object[][] dataprovider() {
    YamlReader.setYamlFilePath();
        int userMapSize = YamlReader.getYamlValues("MercuryPage").size();
        Object[][] returnObj = new Object[userMapSize][1];
        for (int i = 1; i <= userMapSize; i++) {
            Map<String, Object> userInfo = YamlReader.getYamlValues("MercuryPage.testID" + i);
            returnObj[i - 1][0] = userInfo;
        }
        return returnObj;
    }*/
    
    @DataProvider(name = "exceldataprovider", parallel = true)
	public Object[][] getLoginData(){
    	dataXLS = new Xls_Reader(excelFilePath);
    	return Xls_Reader.getData(testCaseName, dataXLS);
	}
    
    
}
