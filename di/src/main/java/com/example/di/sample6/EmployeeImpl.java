package com.example.di.sample6;

public class EmployeeImpl implements Employee {
	private Integer id;
	private String name;
	private Department dept;
	
	public EmployeeImpl(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public void info() {
		System.out.println(String.format("사원번호:%d, 사원명:%s, 부서명:%s, 부서위치:%s", 
							id, name, dept.getName(), dept.getLocation()));
	}

}
