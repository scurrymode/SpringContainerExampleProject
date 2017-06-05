package com.sist.daum;
//item 최대 20개 이걸 list로 묶어주자
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Channel {
	private List<Item> item = new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}
	
	@XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}

}
