package com.luizlealbraga.crawlerapi.Model;

import java.util.HashMap;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.luizlealbraga.crawlerapi.Util.DataSizeManipulator;

public class CapturedDataDto {
	private Double totalSize;
	private Integer totalLines;
	private Integer totalOccurrence;
	@Expose
	@SerializedName("size")
	private String returnTotalSize;
	@Expose
	@SerializedName("lines")
	private String returnTotalLines;
	@Expose
	@SerializedName("occurrences")
	private String returnTotalOccurrence;

	public CapturedDataDto() {
		this.totalLines = 0;
		this.totalSize = 0.0;
		this.totalOccurrence = 0;
	}

	public CapturedDataDto(Double totalSize, Integer totalLines) {
		super();
		this.totalSize = totalSize;
		this.totalLines = totalLines;
	}

	public Double getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Double totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(Integer totalLines) {
		this.totalLines = totalLines;
	}
	
	public Integer getTotalOccurrence() {
		return totalOccurrence;
	}

	public void setTotalOccurrence(Integer totalOccurrence) {
		this.totalOccurrence = totalOccurrence;
	}

	public String getrTotalSize() {
		return returnTotalSize;
	}

	public void setrTotalSize(String rTotalSize) {
		this.returnTotalSize = rTotalSize;
	}

	public String getrTotalLines() {
		return returnTotalLines;
	}

	public void setreturnTotalLines(String rTotalLines) {
		this.returnTotalLines = rTotalLines;
	}

	public String getreturnTotalOccurrence() {
		return returnTotalOccurrence;
	}

	public void setreturnTotalOccurrence(String returnTotalOccurrence) {
		this.returnTotalOccurrence = returnTotalOccurrence;
	}

	public HashMap<String, CapturedDataDto> generateFormatedResult(HashMap<String, CapturedDataDto> map,
			String responseSizeFormat) {
		Set<String> mapkeys = map.keySet();
		for (String key : mapkeys) {
			if (key != null) {
				CapturedDataDto capturedDataDto = map.get(key);
				capturedDataDto.setrTotalSize(capturedDataDto.getTotalSize() + " " + new DataSizeManipulator().formatType(responseSizeFormat));
				capturedDataDto.setreturnTotalLines(capturedDataDto.getTotalLines() + " Lines");
				capturedDataDto.setreturnTotalOccurrence(capturedDataDto.getTotalOccurrence() + " Occurrences");
			}
		}
		return map;
	}

	public void sumTotalLines(Integer totalLines) {
		this.totalLines += totalLines;
	}

	public void sumTotalSize(Double totalSize) {
		this.totalSize += totalSize;
	}
	
	public void sumTotalOccurrence(Integer totalOccurrence) {
		this.totalOccurrence += totalOccurrence;
	}
}
