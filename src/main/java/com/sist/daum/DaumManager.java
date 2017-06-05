package com.sist.daum;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

/*
 *  ��ȭ������ ��α� �˻�(XML, JSON �Ľ�)
 *  JAXB�� ��� 
 *  XML�� class��(�𸶼�)
 *  class�� xml��(����)
 *  
 *  
 *  ����(Daum)�� ���
 *  <channel>
 *  	<item> //�̰� ��α� �ϳ�
 *  		<description>��ȭ���</description>
 *  	</item>
 *  	<item>
 *  		<description>��ȭ���</description>
 *  	</item>
 *  	<item>
 *  		<description>��ȭ���</description>
 *  	</item>
 *  </channel>
 *  
 *  �� �����Ǿ Channel.java Item.java ������
 *  
 *  
 *  String[] feel = {"���","�θǽ�","�ŷ�","��ſ�","����",
				"�Ҹ�","����","����","����","����","����",
				"�ູ","����","����","���","����","�ź�",
				"����","���","���","����","����","�޸Ӵ���",
				"�ڱ�","���","�׼�","����","���","�̽��׸�",
				"��Ÿ��","��","������","���","ǳ��","�ϻ�",
				"����","����","����","�׸���","ȣ��","���","��Ȥ"};
		String[] genre = {
				"���","��Ÿ��","����","���","����",
				"�θǽ�","����","������","���͸�","��ť���͸�",
				"�ڹ̵�","�̽��͸�","����","SF","�׼�","�ִϸ��̼�"};
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
			//xml->class, class->xml �� ����
			Unmarshaller un = jc.createUnmarshaller();
			
			//Unmarshaller ��ü�� xml�� class�� ��ȯ���ش�!
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
