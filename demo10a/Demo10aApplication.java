package edu.miu.demo10a;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.miu.demo10a.domain.Contact;
import edu.miu.demo10a.service.ContactService;

@SpringBootApplication
public class Demo10aApplication implements CommandLineRunner{
	@Autowired
	private ContactService contactService;
	public static void main(String[] args) {
		SpringApplication.run(Demo10aApplication.class, args);
	}

	@Override
	public void run(String ...args){
		contactService.add(new Contact("Thao", "123"));
		contactService.add(new Contact("Peter", "321"));
		List<Contact> contacts = contactService.getAllContacts();
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
	}
}
