package com.qait.tests;

public class ClassB extends ClassA{
	

	public void abcSuper() {
		super.abc();
	}

	@Override
	public void abc() {
		System.out.println("XYZ");
	}
	
	
	
}
