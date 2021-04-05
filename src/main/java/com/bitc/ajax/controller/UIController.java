package com.bitc.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.ajax.dto.AreaDto;

// 에이젝스를 활용한 옵션창

@Controller
public class UIController {

//	private static final Object ListArea = null;

	@RequestMapping(value="/ui/selectBox", method=RequestMethod.GET)
	public String selectBox() throws Exception {
		
		return "/ui/selectBox";
	}
	
	@RequestMapping(value="/ui/selectBox1", method=RequestMethod.POST)
	@ResponseBody // @ResponseBody가 있어야 데이터 자체를 주고 받을 수 있다( 그래서 아래의 return 에 적은 글자 자체를 데이터로 만들어서 날릴 수 있다. )(위처럼 return에 주소를 적지 않아도 된다.)
	public Object box1Selected(AreaDto area) throws Exception {
//		원래 서비스 파일에서 할 작업
		List<AreaDto> listArea = new ArrayList<AreaDto>();
		
		if (area.getAreaName().equals("서울")) {
			AreaDto se1 = new AreaDto();
			AreaDto se2 = new AreaDto();
			AreaDto se3 = new AreaDto();
			AreaDto se4 = new AreaDto();
			se1.setAreaName("강동구");
			se2.setAreaName("강서구");
			se3.setAreaName("강남구");
			se4.setAreaName("강북구");
			
			listArea.add(se1);
			listArea.add(se2);
			listArea.add(se3);
			listArea.add(se4);
		}
		else if (area.getAreaName().equals("대전")) {
			AreaDto ad1 = new AreaDto();
			AreaDto ad2 = new AreaDto();
			AreaDto ad3 = new AreaDto();
			AreaDto ad4 = new AreaDto();
			ad1.setAreaName("동구");
			ad2.setAreaName("서구");
			ad3.setAreaName("중구");
			ad4.setAreaName("유성구");
			
			listArea.add(ad1);
			listArea.add(ad2);
			listArea.add(ad3);
			listArea.add(ad4);
			
		}
		else if (area.getAreaName().equals("대구")) {
			AreaDto ad1 = new AreaDto();
			AreaDto ad2 = new AreaDto();
			AreaDto ad3 = new AreaDto();
			AreaDto ad4 = new AreaDto();
			ad1.setAreaName("달서구");
			ad2.setAreaName("북구");
			ad3.setAreaName("동구");
			ad4.setAreaName("서구");
			
			listArea.add(ad1);
			listArea.add(ad2);
			listArea.add(ad3);
			listArea.add(ad4);
		}
		else { 
			AreaDto se1 = new AreaDto();
			AreaDto se2 = new AreaDto();
			AreaDto se3 = new AreaDto();
			AreaDto se4 = new AreaDto();
			se1.setAreaName("진구");
			se2.setAreaName("해운대구");
			se3.setAreaName("수영구");
			se4.setAreaName("동래구");
			
			listArea.add(se1);
			listArea.add(se2);
			listArea.add(se3);
			listArea.add(se4);
		}
		return listArea;
	}
	
}