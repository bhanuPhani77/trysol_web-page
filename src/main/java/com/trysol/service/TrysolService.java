package com.trysol.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trysol.excel.ExcelGenerator;
import com.trysol.model.TrysolWebApp;
import com.trysol.repo.TrysolRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class TrysolService {

	@Autowired
	private TrysolRepository trysolRepo;
	
	public void saveResponse(TrysolWebApp webApp) {
		
		trysolRepo.save(webApp);
	}
	
	public void removeResponse(Integer id) {
		trysolRepo.deleteById(id);
	}
	
	public void generateExcelSheet(HttpServletResponse response) throws IOException{
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=employee" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<TrysolWebApp> listOfRes = trysolRepo.findAll();
		ExcelGenerator generator = new ExcelGenerator(listOfRes);
		generator.generate(response);
	}
	
}
