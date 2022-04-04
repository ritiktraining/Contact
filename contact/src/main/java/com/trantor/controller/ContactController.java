package com.trantor.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trantor.dto.ContactDto;
import com.trantor.entity.Contact;
import com.trantor.service.ContactServiceImpl;

@RestController
public class ContactController {

	@Autowired
	private ContactServiceImpl contactServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/contact")
	public ResponseEntity<ContactDto> saveContact(@RequestBody ContactDto contactDto) {

		Contact contact = modelMapper.map(contactDto, Contact.class);
		Contact saveContact = contactServiceImpl.saveContact(contact);
		ContactDto map = modelMapper.map(saveContact, ContactDto.class);

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@GetMapping("/contact/{id}")
	public ContactDto fetchbyId(@PathVariable("id") Long contactId) {
		Contact contact = contactServiceImpl.fetchbyId(contactId);

		return modelMapper.map(contact, ContactDto.class);
	}

	@GetMapping("/contact")
	public List<ContactDto> fetchContctList() {
		return contactServiceImpl.fetchContactList().stream().map(contact -> modelMapper.map(contact, ContactDto.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/contact/name/{name}")
	public ContactDto fetchbyfirstName(@PathVariable("name") String firstName) {
		Contact fetchbyfirstName = contactServiceImpl.fetchbyfirstName(firstName);

		return modelMapper.map(fetchbyfirstName, ContactDto.class);
	}

	@DeleteMapping("/contact/{id}")
	public ContactDto deleteById(@PathVariable("id") Long contactId) {

		Contact deleteContact = contactServiceImpl.deleteContact(contactId);

		return modelMapper.map(deleteContact, ContactDto.class);

	}

}
