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
				"사랑","로맨스","매력","즐거움","스릴",
				"소름","긴장","공포","유머","웃음","개그",
				"행복","전율","경이","우울","절망","신비",
				"여운","희망","긴박","감동","감성","휴머니즘",
				"자극","재미","액션","반전","비극","미스테리",
				"판타지","꿈","설레임","흥미","풍경","일상",
				"순수","힐링","눈물","그리움","호러","충격","잔혹",
				"드라마","판타지","공포","멜로","애정",
				"모험","느와르","다큐멘터리","코미디","범죄","SF","애니메이션"};
		Pattern[] p = new Pattern[feel.length]; //패턴담을 그릇
		for(int i=0;i<p.length;i++){
			p[i]=Pattern.compile(feel[i]); //각 단어로 패턴 만들기
		}
		int[] count = new int[feel.length]; //갯수 샐 인트 배열 만들고
		try {
			FileReader fr = new FileReader("c://review_data/"+type+".txt"); //검색 결과 파일 가져오고
			int i=0;
			String data ="";
			while((i=fr.read())!=-1){
				data+=String.valueOf((char)i); // 그 결과를 다 String에 담고
			}
			fr.close();
			String[] feelArray = data.split("\n"); //한줄씩 분리해서 배열에 담고
			Matcher[] m = new Matcher[feel.length]; //매치를 저장할 배열 만들고
			for(String s:feelArray){
				for(int j=0; j<m.length;j++){
					m[j]=p[j].matcher(s); //패턴이랑 문자열이 매치하면 매치에 하나씩 담고
					if(m[j].find()){ //매치가 존재하면 갯수를 올려
						count[j]++;
					}
				}
			}
			String feel_data="";
			for(int z=0;z<feel.length;z++){
				if(count[z]>0){ //하나라도 있으면 출력
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
