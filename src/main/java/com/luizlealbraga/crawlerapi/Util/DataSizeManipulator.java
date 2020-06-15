package com.luizlealbraga.crawlerapi.Util;

public class DataSizeManipulator {

	public Double calculateSize(String size, String type, String rerturnType) {
		// Here we can see the return type that we need to convert and if the return
		// type is different of any possibilities or blank it will return Bytes as
		// default.
		switch (rerturnType.toLowerCase()) {
		case "bytes":
			return this.calculateSizeAsBytes(size, type.toLowerCase());
		case "kb":
			return this.calculateSizeAsKb(size, type.toLowerCase());
		case "mb":
			return this.calculateSizeAsMb(size, type.toLowerCase());
		case "gb":
			return this.calculateSizeAsGb(size, type.toLowerCase());
		case "tb":
			return this.calculateSizeAsTb(size, type.toLowerCase());
		default:
			return this.calculateSizeAsBytes(size, type.toLowerCase());
		}

	}

	private Double calculateSizeAsBytes(String size, String type) {

		switch (type) {
		case "bytes":
			return Double.parseDouble(size);
		case "kb":
			return Double.parseDouble(size) * 1024;
		case "mb":
			return (Double.parseDouble(size) * 1024) * 1024;
		case "gb":
			return ((Double.parseDouble(size) * 1024) * 1024) * 1024;
		case "tb":
			return (((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024;
		case "pb":
			return ((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024;
		case "eb":
			return (((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024;
		case "zb":
			return ((((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024;
		case "yb":
			return (((((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024;
		default:
			return (Double.parseDouble(size) / 8);
		}

	}

	private Double calculateSizeAsKb(String size, String type) {

		switch (type) {
		case "bytes":
			return Double.parseDouble(size) / 1024;
		case "kb":
			return Double.parseDouble(size);
		case "mb":
			return Double.parseDouble(size) * 1024;
		case "gb":
			return (Double.parseDouble(size) * 1024) * 1024;
		case "tb":
			return ((Double.parseDouble(size) * 1024) * 1024) * 1024;
		case "pb":
			return (((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024;
		case "eb":
			return ((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024;
		case "zb":
			return (((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024;
		case "yb":
			return ((((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024;
		default:
			return (Double.parseDouble(size) / 8) / 1024;
		}

	}

	private Double calculateSizeAsMb(String size, String type) {

		switch (type) {
		case "bytes":
			return (Double.parseDouble(size) / 1024) / 1024;
		case "kb":
			return Double.parseDouble(size) / 1024;
		case "mb":
			return Double.parseDouble(size);
		case "gb":
			return Double.parseDouble(size) * 1024;
		case "tb":
			return (Double.parseDouble(size) * 1024) * 1024;
		case "pb":
			return ((Double.parseDouble(size) * 1024) * 1024) * 1024;
		case "eb":
			return (((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024;
		case "zb":
			return ((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024;
		case "yb":
			return (((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024) * 1024;
		default:
			return ((Double.parseDouble(size) / 8) / 1024) / 1024;
		}

	}

	private Double calculateSizeAsGb(String size, String type) {

		switch (type) {
		case "bytes":
			return ((Double.parseDouble(size) / 1024) / 1024) / 1024;
		case "kb":
			return (Double.parseDouble(size) / 1024) / 1024;
		case "mb":
			return Double.parseDouble(size) / 1024;
		case "gb":
			return Double.parseDouble(size);
		case "tb":
			return Double.parseDouble(size) * 1024;
		case "pb":
			return (Double.parseDouble(size) * 1024) * 1024;
		case "eb":
			return ((Double.parseDouble(size) * 1024) * 1024) * 1024;
		case "zb":
			return (((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024;
		case "yb":
			return ((((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024) * 1024;
		default:
			return (((Double.parseDouble(size) / 8) / 1024) / 1024) / 1024;
		}

	}

	private Double calculateSizeAsTb(String size, String type) {

		switch (type) {
		case "bytes":
			return (((Double.parseDouble(size) / 1024) / 1024) / 1024) / 1024;
		case "kb":
			return ((Double.parseDouble(size) / 1024) / 1024) / 1024;
		case "mb":
			return (Double.parseDouble(size) / 1024) / 1024;
		case "gb":
			return Double.parseDouble(size) / 1024;
		case "tb":
			return Double.parseDouble(size);
		case "pb":
			return Double.parseDouble(size) * 1024;
		case "eb":
			return (Double.parseDouble(size) * 1024) * 1024;
		case "zb":
			return ((Double.parseDouble(size) * 1024) * 1024) * 1024;
		case "yb":
			return (((Double.parseDouble(size) * 1024) * 1024) * 1024) * 1024;
		default:
			return ((((Double.parseDouble(size) / 8) / 1024) / 1024) / 1024) / 1024;
		}

	}

	public String formatType(String type) {
		switch (type.toLowerCase()) {
		case "bytes":
			return type.toUpperCase();
		case "kb":
			return type.toUpperCase();
		case "mb":
			return type.toUpperCase();
		case "gb":
			return type.toUpperCase();
		case "tb":
			return type.toUpperCase();
		case "pb":
			return type.toUpperCase();
		case "eb":
			return type.toUpperCase();
		case "zb":
			return type.toUpperCase();
		case "yb":
			return type.toUpperCase();
		default:
			return "Bytes";
		}
	}
}
