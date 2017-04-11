package com.yzq.demo1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class Controller {

	@Value("${app.params.string}")
	private String string;

	@ApiOperation(value = "Hello首页", notes = "根据输入返回提示信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "用户名", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(String name) {
		if (StringUtils.isEmpty(name)) {
			System.out.println("xxxx");
			return "Hello World";
		} else {
			System.out.println("name:" + name);
			return "Hello " + name;
		}
	}

	@ApiOperation(value = "欢迎首页", notes = "根据输入返回提示信息")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return string;
	}
}
