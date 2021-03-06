package com.trantor.service;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.trantor.controller.ContactController;
import com.trantor.dto.ContactDto;
import com.trantor.entity.Contact;

@Service
public class ExternalClientService {

	@Value("${uri}")
	private String uri;

	@Autowired
	private ContactController contactController;

	@Autowired
	private ContactServiceImpl contactServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<String> fetchAll(Integer flag) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		try {
			if (flag == 1) {
				return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			} else if (flag == 0) {
				return new ResponseEntity(contactServiceImpl.fetchContactList(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return restTemplate.exchange("http://10.50.2.206:9292/findAll", HttpMethod.GET, entity, String.class);
		}

		return new ResponseEntity<>("Please enter either 0 or 1", HttpStatus.OK);

	}

	public ResponseEntity<String> postData(@RequestParam("id") int flag, @RequestBody ContactDto contactDto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Contact contact = modelMapper.map(contactDto, Contact.class);

		HttpEntity<Contact> entity = new HttpEntity<>(contact, headers);

		if (flag == 1) {
			return restTemplate.exchange("http://10.50.2.211:8080/add", HttpMethod.POST, entity, String.class);
		} else if (flag == 0) {
			return new ResponseEntity(contactController.saveContact(contactDto).getBody(), HttpStatus.OK);
		}

		return new ResponseEntity<>("Please enter either 0 or 1", HttpStatus.OK);
	}

}
