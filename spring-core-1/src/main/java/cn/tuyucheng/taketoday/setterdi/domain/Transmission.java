package cn.tuyucheng.taketoday.setterdi.domain;

import cn.tuyucheng.taketoday.jacoco.exclude.annotations.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class Transmission {
	private String type;

	public Transmission() {
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("%s", type);
	}
}