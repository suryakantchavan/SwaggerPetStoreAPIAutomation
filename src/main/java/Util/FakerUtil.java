package Util;

import com.github.javafaker.Faker;

public class FakerUtil {

  Faker faker;

  public FakerUtil() {
    faker = new Faker();
  }

  public String getFirstName() {
    return faker.name().firstName();
  }

  public String getLastName() {
    return faker.name().lastName();
  }

  public String getEmailId() {
    return faker.internet().emailAddress();
  }

  public Integer getId() {
    return faker.number().numberBetween(1,1000);
  }

  public String userName() {
    return faker.name().lastName();
  }

  public String getTagName()
  {
    return faker.name().name();
  }

  public String getPetName()
  {
    return faker.animal().name();
  }
  public String getCategoryName()
  {
    return faker.name().nameWithMiddle();
  }

  public Integer getTagId()
  {
    return faker.number().randomDigit();
  }

  public Integer getCategoryId()
  {
    return faker.number().randomDigit();
  }

  public Integer getPetId()
  {
    return faker.number().randomDigit();
  }

  public  String getUrl()
  {
    return faker.internet().url();
  }

}
