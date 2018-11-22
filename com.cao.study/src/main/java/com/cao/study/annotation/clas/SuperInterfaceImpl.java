package com.cao.study.annotation.clas;

import java.lang.annotation.Annotation;

import com.cao.study.annotation.superinter.SuperInterface;

public class SuperInterfaceImpl implements SuperInterface {

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String addString() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
