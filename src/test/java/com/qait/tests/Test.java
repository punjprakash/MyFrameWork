package com.qait.tests;


public class Test {

@org.testng.annotations.Test
public void method(){
	String detailValue="$vhvvv";
	String newVar=detailValue.replace("$", "");
	System.out.println(newVar);
}
}
