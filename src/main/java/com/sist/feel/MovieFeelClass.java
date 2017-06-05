package com.sist.feel;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MovieFeelClass {
	/*public static void main(String[] args) {
		MovieFeelClass mf = new MovieFeelClass();
		mf.moiveFeelData();
	}*/
	public void moiveFeelData(String type){
		String[] feel = {
				"���","�θǽ�","�ŷ�","��ſ�","����",
				"�Ҹ�","����","����","����","����","����",
				"�ູ","����","����","���","����","�ź�",
				"����","���","���","����","����","�޸Ӵ���",
				"�ڱ�","���","�׼�","����","���","�̽��׸�",
				"��Ÿ��","��","������","���","ǳ��","�ϻ�",
				"����","����","����","�׸���","ȣ��","���","��Ȥ",
				"���","��Ÿ��","����","���","����",
				"����","���͸�","��ť���͸�","�ڹ̵�","����","SF","�ִϸ��̼�"};
		Pattern[] p = new Pattern[feel.length]; //���ϴ��� �׸�
		for(int i=0;i<p.length;i++){
			p[i]=Pattern.compile(feel[i]); //�� �ܾ�� ���� �����
		}
		int[] count = new int[feel.length]; //���� �� ��Ʈ �迭 �����
		try {
			FileReader fr = new FileReader("c://review_data/"+type+".txt"); //�˻� ��� ���� ��������
			int i=0;
			String data ="";
			while((i=fr.read())!=-1){
				data+=String.valueOf((char)i); // �� ����� �� String�� ���
			}
			fr.close();
			String[] feelArray = data.split("\n"); //���پ� �и��ؼ� �迭�� ���
			Matcher[] m = new Matcher[feel.length]; //��ġ�� ������ �迭 �����
			for(String s:feelArray){
				for(int j=0; j<m.length;j++){
					m[j]=p[j].matcher(s); //�����̶� ���ڿ��� ��ġ�ϸ� ��ġ�� �ϳ��� ���
					if(m[j].find()){ //��ġ�� �����ϸ� ������ �÷�
						count[j]++;
					}
				}
			}
			String feel_data="";
			for(int z=0;z<feel.length;z++){
				if(count[z]>0){ //�ϳ��� ������ ���
					feel_data+=feel[z]+" "+count[z]+"\n";
				}
			}
			FileWriter fw=new FileWriter("c://review_data/"+type+"_feel.txt");
			fw.write(feel_data);
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
