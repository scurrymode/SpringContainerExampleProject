package com.sist.container;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.daum.DaumManager;
import com.sist.feel.MovieFeelClass;
import com.sist.r.RManager;

public class MainClass {
	private static DaumManager dm;
	private MovieFeelClass mfc;
	private RManager rm;
	

	public void setRm(RManager rm) {
		this.rm = rm;
	}

	public void setMfc(MovieFeelClass mfc) {
		this.mfc = mfc;
	}

	public void setDm(DaumManager dm) {
		this.dm = dm;
	}

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MainClass mc = app.getBean("mc", MainClass.class);
		System.out.println("��ȭ��:");
		Scanner scan = new Scanner(System.in);
		String title = scan.next();
		mc.dm.daumReviewSave(title); //��ȭ�� �Է¹����ɷ� �˻���� �����ϰ�
		System.out.println("Save End....");
		mc.mfc.moiveFeelData("daum"); //��� �м��ؼ� �����ϰ�
		System.out.println("Feel Save End...");
		mc.rm.rGraph(); //�̹����� �����Ѵ�
		System.out.println("Image Save..");
		
	}

}
