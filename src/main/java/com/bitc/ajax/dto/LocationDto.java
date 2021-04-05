package com.bitc.ajax.dto;

import java.util.List;

import lombok.Data;

@Data
public class LocationDto {

	private String location;
	private List<AreaDto> area;
}
