package com.sist.daum;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

/*
 *  영화명으로 블로그 검색(XML, JSON 파싱)
 *  JAXB를 사용 
 *  XML을 class로(언마셜)
 *  class를 xml로(마셜)
 *  
 *  
 *  다음(Daum)의 경우
 *  <channel>
 *  	<item> //이게 블로그 하나
 *  		<description>영화댓글</description>
 *  	</item>
 *  	<item>
 *  		<description>영화댓글</description>
 *  	</item>
 *  	<item>
 *  		<description>영화댓글</description>
 *  	</item>
 *  </channel>
 *  
 *  로 구성되어서 Channel.java Item.java 만들어둠
 *  
 *  
 *  String[] feel = {"사랑","로맨스","매력","즐거움","스릴",
				"소름","긴장","공포","유머","웃음","개그",
				"행복","전율","경이","우울","절망","신비",
				"여운","희망","긴박","감동","감성","휴머니즘",
				"자극","재미","액션","반전","비극","미스테리",
				"판타지","꿈","설레임","흥미","풍경","일상",
				"순수","힐링","눈물","그리움","호러","충격","잔혹"};
		String[] genre = {
				"드라마","판타지","공포","멜로","애정",
				"로맨스","모험","스릴러","느와르","다큐멘터리",
				"코미디","미스터리","범죄","SF","액션","애니메이션"};
 * */
import java.io.*;
import java.net.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
public class DaumManager {
	private String key;
	
	public void setKey(String key) {
		this.key = key;
	}

	public void daumReviewSave(String title){
		String total="";
		for(int i=1;i<=3;i++){
			String review=daumReviewData(i, title);
			total+=review;
		}
		try {
			FileWriter fw = new FileWriter("c://review_data/daum.txt");
			fw.write(total);
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String daumReviewData(int page, String title){
		String review= "";
		
		try {
			URL url = new URL("https://apis.daum.net/search/blog?apikey="
			+key+"&result=20&output=xml&pageno="+page+"&q="
					+URLEncoder.encode(title, "UTF-8"));
			
			JAXBContext jc = JAXBContext.newInstance(Channel.class);
			//xml->class, class->xml 중 선택
			Unmarshaller un = jc.createUnmarshaller();
			
			//Unmarshaller 객체가 xml을 class로 변환해준다!
			Channel channel = (Channel)un.unmarshal(url);
			List<Item> temp = channel.getItem();
			
			for(Item i: temp){
				review+=i.getDescription()+"\n";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return review;		
	}

}
