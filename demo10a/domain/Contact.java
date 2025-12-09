package edu.miu.demo10a.domain;

public class Contact {
  private String name;
  private String phone;
  public Contact(String name, String phone){
    this.name = name;
    this.phone = phone;
  }

  public String getName(){
    return this.name;
  }
  public String getPHone(){
    return this.phone;
  }

  @Override
  public String toString(){
    return "Name: " + this.name + ", Phone: " + this.phone;
  }
}
