package com.luizlealbraga.crawlerapi.Model;

import java.util.HashMap;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.luizlealbraga.crawlerapi.Util.DataSizeManipulator;

public class CapturedDataDto {
	@Expose
	@SerializedName("size")
	private Double totalSize;
	@Expose
	@SerializedName("lines")
	private Integer totalLines;
	@SerializedName("occurrences")
	private Integer totalOccurrence;

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
