package com.bitc.ajax.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.ajax.dto.CalDto;

// ajax 통신
// 웹에서 굉장히 많이 사용함
// 예전의 통신 방법 : 동기방식, 클라이언트가 서버로 데이터를 보내면 서버가 신호를 받아서 응답을 주는 형태
// 클라이언트가 서버로 신호를 보낸 후 응답을 받을 때까지 어떠한 작업도 하지 않고 대기 중으로 기다리고 있음
// 이러한 예전 방식은 구현하기 쉬움, 그러나 서버가 제대로 동작을 하지 않으면 클라이언트는 다음 작업을 못 하고 계속 기다려야하는 문제가 생긴다. 그래서 나온 것이 비동기 통신 방식이다.

// 비동기 통신 : 클라이언트가 서버로 데이터를 보낸 후 자신은 다른 필요한 작업을 계속 진행하고, 서버에서 응답이 왔을 경우 이벤트가 동작하여 응답에 대한 처리하는 방식
// -> 이벤트 리스너 또는 콜백 함수 

// AJAX 통신 사용 시 화면의 깜빡임(화면 리플레시, 화면 재로딩)이 없이 서버의 데이터를 불러올 수 있음
// 예전에는 웹으로 채팅을 하려면 액티브x 프로그램이 필요했다, 액티브엑스가 서버랑 통신을 했었음, 그러나 에이젝스를 사용하면서부터 추가로 설치하는 것 없이 바로 웹상에서 채팅이 가능함
@Controller
public class AjaxController {

	@RequestMapping(value="/sync", method=RequestMethod.GET)
	public String sync() throws Exception {
		
		return "/ajax/sync";
	}
	
	// 기존방식의 사이트 접근 방식 - 동기방식=========================================
	// 2. 기본적으로 서버로 데이터 받을 때 아래와 같이 주소를 새로 입력
	@RequestMapping(value="/sync/cal", method=RequestMethod.GET)
	public ModelAndView cal(CalDto cal) throws Exception {
		ModelAndView mv = new ModelAndView("/ajax/cal");
		
		cal.setResult(cal.getNum1() + cal.getNum2());
		
		mv.addObject("cal", cal);
		
		return mv;
	}
	
//	에이젝스사용 방식=================================================================
//	/ajax/ajaxTest.html 페이지와 연결
	@RequestMapping(value="/async/ajaxTest", method=RequestMethod.GET)
	public String ajaxTestPage() throws Exception {
		return "/ajax/ajaxTest";
	}
	
//	restful 방식 사용
//	@ResponseBody 어노테이션을 사용하면 View를 반환하지 않고, 데이터 자체를 반환함
	@RequestMapping(value="/async/ajaxTest", method=RequestMethod.POST)
	@ResponseBody
	public Object ajaxTest(CalDto cal) throws Exception {
//		HashMap은 키와 값이 1:1로 매칭되어 있는 데이터 타입
//		하나의 변수명으로 여러 개의 데이터를 저장할 수 있는 데이터 타입
//		javascript의 object 타입과 유사함
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num1", cal.getNum1());
		map.put("num2", cal.getNum2());
		map.put("result", cal.getNum1() + cal.getNum2());
		
		return map;
	}
}
































