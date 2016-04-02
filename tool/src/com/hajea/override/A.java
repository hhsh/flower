package com.hajea.override;

public class A {
	protected int method1 (int a, int b) {	return 0; }
	//public int method 1 (int a, int b) { return 0; } �����ķ���Ȩ�޲�������������
	//private int method1 (int a, int b) { return 0; } �����ķ���Ȩ�޲�������������
	
	//protected  String method1 (int a, int b) { return "hello"; }�����ķ���ֵ��������������
	//protected  void method1 (int a, int b) { }�����ķ���ֵ��������������
	protected  int  method1 (int a, long b) { return 0; } //���������Ϳ�����������
	protected  int  method1 (long b,int a) { return 0; } //�������͵�˳�������������
	protected  int  method1 (int a) { return 0; } //��������������������
	  

}
