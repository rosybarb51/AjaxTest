package com.bitc.ajax.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// restful 방식 사용할 것임

@Controller
public class DataController {

//	기본적으로 접속할 사이트 -> GET을 해준다.
	@RequestMapping(value="/data/pharmacyPage", method=RequestMethod.GET)
	public String pharmacyPage() throws Exception {
		return "/data/pharmacyPage";
	}
	
	@ResponseBody
	@RequestMapping(value="/data/pharmacyFullData", method=RequestMethod.POST)
//	Map 타입으로 안 받을거면 Dto 파일 생성해서 사용하기
//	키는 String, 값은 Integer로 받음, 변수명은 param
	public Object getPharmacyFullData(@RequestParam Map<String, Integer> param) throws Exception {
		
//		자바의 문자열 처리 클래스 중 하나
//		자바의 문자열 클래스인 String 클래스는 기본 자료형처럼 사용되지만 메모리 낭비가 많은 자료형임
//		String 타입의 변수 2개(a = hello, b = world)가 있을 경우 c = a + b를 하게 되면 사용자의 눈에는 문자열 변수 c에 hello와 world가 합쳐져서 하나의 문자열이 되는 것처럼 보이나, 실제로는 새로운 메모리 영역을 선언하고 거기에 helloworld 라는 문자열을 생성하여 저장하는 형태임
//		StringBuffer 클래스는 String 클래스의 메모리 낭비 부분을 해결한 클래스임 -> 여러 개의 메모리를 사용하는 것이 아니라, 하나의 메모리에 추가해서 사용하는 방식 => 그래서 아래의 try 문 내 while 문 안의 변수명.append(); 해서 기존 문자열의 뒤에 추가로 문자열을 입력할 수 있다.
		StringBuffer result = new StringBuffer();
		
//		rest api의 주소
//		endPoint에 주소 끝에 / 하나 넣는 거 빼먹지 말기!!
		String endPoint ="http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/";
		
//		요청할 명령어
//		물음표 잊지말기!
		String serviceFunc = "getParmacyFullDown?";
		
//		키 명령어
		String keyFunc = "serviceKey=";
		
//		요청할 페이지
		String pageNo = "&pageNo";
		
//		한페이지당 결과 수
		String rows = "&numOfRows=";
		
//		실제 사용자 인증키
		String serviceKey = "MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D";
		
		try {
//			위의 rest api 주소 및 명령, 옵션들을 하나의 주소로 합함, 단순한 주소형식의 문자열을 만들었다 아래의 URL 주소에서 만들어야 실제 접속할 수 있는 주소가 생성되는 것임 -> 그래야 웹브라우저를 통해서 명령어를 보낼 수 있으니까.
			String urlStr = endPoint + serviceFunc + keyFunc + serviceKey + pageNo + param.get("page") + rows + param.get("row");
			
//			URL : 실제 웹주소 입력 클래스 / 생성자의 매개변수로 받은 문자열을 실제 접속할 수 있는 URL로 변환
//			URL import 시 java.net 으로 import 하기
			URL url = new URL(urlStr);
			
//			자바에서 http 프로토콜을 이용하여 지정한 웹사이트에 접속하는 클래스
//			Http : 우리가 일반적으로 접속하는 웹 형태로 붙이겠다는 뜻
//			URL : 네트워크상의 주소
//			Connection : 접속한다는 뜻
//			.openConnection(); 을 통해서 실제 접속이 이루어짐. 그것을 urlConn에 넣어줌.
//			HttpURLConnection import 시 java.net 으로
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			
//			requestmethod 어떤 방식으로 보낼지 정하는 명령어, 보통 api는 GET, POST 정도로만 보낸다.
			urlConn.setRequestMethod("GET");
			
//			데이터를 읽기 위한 메모리 버퍼 선언
//			접속한 사이트의 정보를 읽어오기
//			Buffer는 메모리라고 생각하면 됨, 그래서 BufferedReader 는 메모리의 것을 읽어온다고 생각하면됨.
//			Input : 입력
//			Stream : 입력되는 모든 것들의 메모리라고 생각하면 됨
//			->InputStreamReader : 입력 메모리를 읽어오는 것
//			그래서 결국 위에서 마든 urlConn에 접속한 사이트의 정보를 읽어온다는 것임
//			InputStreamReader import 시 java.io 로 import 하기
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			
			String dataLine;
			
//			dataLine에 읽어온 정보를 null이 아닐 때까지 다 읽어와서 저장한다.
//			readLine()은 한 줄씩 ~!
//			result는 위에서 StringBuffer로 선언했음.
//			\n은 엔터 효과
			while ((dataLine = br.readLine()) != null) {
				result.append(dataLine + "\n"); // 기존 문자열의 뒤에 추가로 문자열을 입력함
			}
			
//			접속 종료
			urlConn.disconnect();
		}
		catch (Exception e) {
//			오류나면 오류 표시해주기
			e.printStackTrace();
		}
		
//		ResponseBody로 받아서, 지금까지 받은 데이터를 html의 ajax로 던져줘서, success의 function(data)에 들어간다. 그래서 거기서 JSON으로 파싱해서 화면에 뿌려주는 방식. -> 그런데, 컨트롤러에서 파싱해줘도 된다.
		return result;
	}
}



























