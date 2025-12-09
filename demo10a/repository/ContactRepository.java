package edu.miu.demo10a.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.miu.demo10a.domain.Contact;

@Repository
public class ContactRepository {
  private Map<String, Contact> contacts = new HashMap<>();
  
  public void addContact(Contact contact) throws Exception{
    if(contacts.containsKey(contact.getName())){
      throw new Exception("Contact existed");
    }
    contacts.put(contact.getName(), contact);
  }
  
  public List<Contact> getAllContacts(){
    return new ArrayList<>(contacts.values());
  }
  
  public Contact getContactByName(String name){
    Contact contact = contacts.get(name);
    return contact;
  }

}
