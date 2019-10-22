package com.antheminc.oss.nimbus.samples.repws;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/nimbus-samples-repws/#/h/homeview/vpMain");

	}

	@GetMapping(value = "/apperror")
	public String error(@RequestParam Map<String, String> allParams, HttpServletRequest request,
			HttpServletResponse response) {
		return "customerror";
	}

}
