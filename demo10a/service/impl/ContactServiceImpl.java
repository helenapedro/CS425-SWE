package edu.miu.demo10a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.demo10a.domain.Contact;
import edu.miu.demo10a.repository.ContactRepository;
import edu.miu.demo10a.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
  @Autowired
  private ContactRepository contactRepository;
  public void testFunc(){
    System.out.println("Test");
  }
  public void add(Contact contact){
    try {
      contactRepository.addContact(contact);
      this.testFunc();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } 
  }
  public List<Contact> getAllContacts(){
    return contactRepository.getAllContacts();
  }
}
