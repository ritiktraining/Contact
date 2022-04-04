package com.trantor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trantor.entity.Contact;
import com.trantor.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepository;

	@Override
	public Contact saveContact(Contact contact) {

		return contactRepository.save(contact);
	}

	@Override
	public Contact fetchbyId(Long contactId) {
		Optional<Contact> contact = contactRepository.findById(contactId);

		if (contact.isPresent()) {
			return contact.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Contact> fetchContactList() {
		return contactRepository.findAll();
	}

	@Override
	public Contact fetchbyfirstName(String firstName) {
		return contactRepository.findByFirstName(firstName);
	}

	@Override
	public Contact deleteContact(Long contactId) {

		Contact contact = contactRepository.findById(contactId).orElseThrow();

		contact.setIsActive("N");

		return contactRepository.save(contact);

	}

}
