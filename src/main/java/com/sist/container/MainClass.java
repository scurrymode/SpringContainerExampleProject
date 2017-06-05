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
		System.out.println("영화명:");
		Scanner scan = new Scanner(System.in);
		String title = scan.next();
		mc.dm.daumReviewSave(title); //영화명 입력받은걸로 검색결과 저장하고
		System.out.println("Save End....");
		mc.mfc.moiveFeelData("daum"); //결과 분석해서 저장하고
		System.out.println("Feel Save End...");
		mc.rm.rGraph(); //이미지로 저장한다
		System.out.println("Image Save..");
		
	}

}
