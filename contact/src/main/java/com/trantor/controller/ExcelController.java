package com.trantor.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.trantor.entity.Contact;
import com.trantor.repository.ContactRepository;
import com.trantor.service.ExcelExporter;

@Controller
public class ExcelController {

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping("/exportexcel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
//		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=contact.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Contact> contacts = contactRepository.findAll();

		ExcelExporter excelExporter = new ExcelExporter(contacts);

		excelExporter.export(response);
	}
}