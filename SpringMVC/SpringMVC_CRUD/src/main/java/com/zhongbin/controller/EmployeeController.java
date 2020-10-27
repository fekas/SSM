package com.zhongbin.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhongbin.bean.Department;
import com.zhongbin.bean.Employee;
import com.zhongbin.dao.DepartmentDao;
import com.zhongbin.dao.EmployeeDao;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	DepartmentDao departmentDao;
	
	@RequestMapping("/emps")
	public String getEmps(Model model){
		Collection<Employee> employees = employeeDao.getAll();
		model.addAttribute("emps",employees);
		return "list";
	}
	
	@RequestMapping("/toaddpage")
	public String toAddPage(Model model){
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts",departments);
		model.addAttribute("employee",new Employee());
		return "add";
	}

	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String AddEmployee(Employee employee){
		employeeDao.save(employee);
		//重定向到查询所有员工的请求
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public String GetEmp(@PathVariable("id")Integer id,Model model){
		Employee employee = employeeDao.get(id);
		model.addAttribute("employee",employee);
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		return "edit";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
	public String updateEmp(@ModelAttribute("employee")Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String deleteEmp(@PathVariable("id")Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	@ModelAttribute
	public void myModelAttribute(@RequestParam(value="id",required=false)Integer id,Model model){
		if(id != null ){
			Employee employee = employeeDao.get(id);
			model.addAttribute("employee", employee);
		}
	}
	
}
