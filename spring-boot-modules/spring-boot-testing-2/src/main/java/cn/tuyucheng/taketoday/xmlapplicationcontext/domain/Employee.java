package cn.tuyucheng.taketoday.xmlapplicationcontext.domain;

import cn.tuyucheng.taketoday.jacoco.exclude.annotations.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class Employee {

	private String name;
	private String role;

	public Employee() {

	}

	public Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return this.name;
	}

	public String getRole() {
		return this.role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}
}