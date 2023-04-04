package com.trysol.controller;

import java.util.Properties;
import java.io.IOException;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trysol.model.TrysolWebApp;
import com.trysol.service.TrysolService;

@Controller
public class TrysolController {

	@Autowired
	private TrysolService trysolService;

	@GetMapping("/contact")
	public String showform(Model m) {
		m.addAttribute("webApp", new TrysolWebApp());
		return "contactus";
	}

	@PostMapping("/contactus-submit")
	public String storeEntity(@Validated @ModelAttribute("webApp") TrysolWebApp webApp) throws AddressException, MessagingException, IOException{
		trysolService.saveResponse(webApp);
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("info.trysol@gmail.com", "kpzzafptpittkgcz");
			}
		});
		jakarta.mail.Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(webApp.getEmail(), true));
		msg.setRecipients(jakarta.mail.Message.RecipientType.TO, InternetAddress.parse("info@trysol.com"));
		msg.setSubject(webApp.getSubject());
		
		msg.setContent("Email:"+webApp.getEmail()+"\r\n"+"Name: "+webApp.getName()+"\r\n"+"     Message: "+webApp.getMessage(), "text/html");
		msg.setSentDate(new java.util.Date());

		jakarta.mail.internet.MimeBodyPart messageBodyPart = new jakarta.mail.internet.MimeBodyPart();
		messageBodyPart.setContent("Trysol Email", "text/html");

		
		Transport.send(msg);
		return "saved-success";
	}

	@PostMapping("/view")
	public String removeResponse(@PathVariable Integer id) {
		trysolService.removeResponse(id);
		return "removed successfully";

	}

	@GetMapping("/")
	public String getHomePage() {
		return "index";
	}

	@GetMapping("/aboutus")
	public String getAboutUsPage() {
		return "aboutus";
	}

	@GetMapping("/services")
	public String servicesPage() {
		return "services";
	}

	@GetMapping("/clients")
	public String clientsPage() {
		return "clients";
	}

}
