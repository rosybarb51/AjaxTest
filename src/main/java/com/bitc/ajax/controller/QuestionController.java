package com.bitc.ajax.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {
	
	@RequestMapping(value="/quiz/question2", method=RequestMethod.GET)
	public String quiz2() throws Exception {
		return "/quiz/question2";
	}

	// ResponseBody 는 rest api 라고, 위에 @Controller 말고 @RestController를 사용하게되면 아래의 @ResponseBody가 기본적으로 표현돼서, RestController를 사용해서 아래의 @Responsebody를 사용하지 않고 똑같은 효과를 볼 수 있따.
	@ResponseBody
	@RequestMapping(value="/quiz/emailList", method=RequestMethod.POST)
	public Object getEmailList() throws Exception {
		// 원래 서비스에서 아래의 내용 해줘야한다
		String[] emailList = {"@nate.com", "@naver.com", "@daum.net", "@gmail.com"};
		return emailList;
	}
	
	@ResponseBody
	@RequestMapping(value="/quiz/phoneList", method=RequestMethod.POST)
	public Object getPhoneList() throws Exception {
		String[] phoneList = {"010", "011", "017"};
		return phoneList;
	}
	
	@ResponseBody
	@RequestMapping(value="/quiz/telList", method=RequestMethod.POST)
	public Object getTelList() throws Exception {
		String[] telList = {"02", "031", "032", "033", "041", "042", "043", "044", "051", "052", "053", "061", "062", "063", "064"};
		return telList;
	}
	
	@ResponseBody
	@RequestMapping(value="/quiz/areaList1", method=RequestMethod.POST)
	public Object getArea1() throws Exception {
		String[] areaList = {"서울", "부산", "제주"};
		return areaList;
	}
	
	@ResponseBody
	@RequestMapping(value="/quiz/areaList2", method=RequestMethod.POST)
	// 어제는 dto 파일 생성해서 매개변수지정해서 썼음
	// 갯수가 적으면 이렇게 map 타입 사용하고, 5개 이상이면 dto 파일 만드는 게 편하다
	// 여기서는 @RequestParam을 써서 HashMap 타입(js의 object와 비슷, 키와 값이 1:1로 매칭된 자료형)을 사용함
	// Map 타입<키도 String타입, 데이터도 String타입> 뒤의 변수명을 param이라고 함.
	public Object getArea2(@RequestParam Map<String, String> param) throws Exception {
		// 해쉬맵 타입을 사용할 때 넣을 땐 add, 뺄 땐 get 
		// 기본적으로 get 꺼내보기, set 수정, add 추가, remove 삭제
		// get 뒤에 키이름을 적어줌, queste2.html의 자바스크립트의 키값을 적은 것이다. areaName (ajax 내의 data에서 )
		String areaName = param.get("areaName");
		String [] areaList = null;
		switch (areaName) {
		case "서울":
		// String 뒤에 갯수
			areaList = new String[3];
			areaList[0] = "강북구";
			areaList[1] = "강남구";
			areaList[2] = "강서구";
			break;
			
		case "부산":
			areaList = new String[3];
			areaList[0] = "동래구";
			areaList[1] = "해운대구";
			areaList[2] = "부산진구";
			break;
			
		case "제주":
			areaList = new String[2];
			areaList[0] = "제주시";
			areaList[1] = "서귀포시";
			break;
		}

		return areaList;
	}
	
	@ResponseBody
	@RequestMapping(value="/quiz/areaList3", method=RequestMethod.POST)
	public Object getArea3(@RequestParam Map<String, String> param) throws Exception {
		String [][] area1 = {
				{"개포동", "논현동", "대치동"},
				{"미아동", "번동", "수유동"},
				{"가양동", "개화동", "공항동"}
		};
		String [][] area2 = {
			{"낙민동", "명륜동", "명장동"},
			{"반송동", "반여동", "석대동"},
			{"가야동", "개금동", "당감동"}
		};
		String [][] area3 = {
				{"건입동", "구좌동", "내도동"},
				{"강정동", "남원동", "대정동"}
		};
		
		String [] result = null;
		
		switch (param.get("areaName")) {
		case "강북구":
			result = area1[0];
		break;
		
		case "강서구":
			result = area1[1];
  		break;
		      
		case "강남구":
			result = area1[2];
  		break;
		      
		case "동래구":
			result = area2[0];
  		break;
		      
		case "해운대구":
			result = area2[1];
  		break;
		      
		case "부산진구":
			result = area2[2];
  		break;
		      
		case "제주시":
			result = area3[0];
  		break;
		      
		case "서귀포시":
			result = area3[1];
  		break;
		}
		
		return result;
	}
}
