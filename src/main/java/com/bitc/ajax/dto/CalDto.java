package com.bitc.ajax.dto;

import lombok.Data;

// 기존방식의 사이트 접근 방식 - 동기방식=========================
// 3. dto 파일을 만들어서 선언을 해줘야 한다. 
@Data
public class CalDto {
	private int num1;
	private int num2;
	private int result;
}
