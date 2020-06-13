package com.luizlealbraga.crawlerapi.Model;

public class CapturedData {
	private Double totalSize;
	private String extensionName;
	private Integer totalLines;
	
	public CapturedData(Double totalSize, String extensionName, Integer totalLines) {
		super();
		this.totalSize = totalSize;
		this.extensionName = extensionName;
		this.totalLines = totalLines;
	}

	public Double getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Double totalSize) {
		this.totalSize = totalSize;
	}

	public String getExtensionName() {
		return extensionName;
	}

	public void setExtensionName(String extensionName) {
		this.extensionName = extensionName;
	}

	public Integer getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(Integer totalLines) {
		this.totalLines = totalLines;
	}
	
	@Override
    public boolean equals(Object object)
    {
        boolean equals = false;

		if (object != null && object instanceof CapturedData) {
			equals = this.extensionName.equals(((CapturedData) object).extensionName);
		}

        return equals;
    }
	
}
