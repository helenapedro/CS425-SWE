package edu.miu.demo10a.service;

import java.util.List;

import edu.miu.demo10a.domain.Contact;

public interface ContactService {
  abstract void add(Contact contact);
  abstract List<Contact> getAllContacts();
}
