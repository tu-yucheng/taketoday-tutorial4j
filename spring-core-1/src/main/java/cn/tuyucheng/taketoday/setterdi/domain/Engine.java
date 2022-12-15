package cn.tuyucheng.taketoday.setterdi.domain;

import cn.tuyucheng.taketoday.jacoco.exclude.annotations.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class Engine {
	private String type;
	private int volume;

	public Engine() {
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return String.format("%s %d", type, volume);
	}
}