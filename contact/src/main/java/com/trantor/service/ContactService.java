package com.trantor.service;

import java.util.List;

import com.trantor.entity.Contact;

public interface ContactService {

	public Contact saveContact(Contact contact);

	public Contact fetchbyId(Long contactId);

	public List<Contact> fetchContactList();

	public Contact fetchbyfirstName(String firstName);

	public Contact deleteContact(Long contactId);

}
