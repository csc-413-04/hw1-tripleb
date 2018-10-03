package simpleserver;

import java.util.ArrayList;

public class UserQuery extends Query {

  ArrayList userList;
  String response;




public UserQuery(String url){
  
    Database database = Database();
  if(url.contains("[0-9]+")){
    url.replaceAll("[^0-9]", "");
    User user = database.getUser(url);
    userList.add(user);
    ResponseBuilder a = new ResponseBuilder(userList);
    this.response = (a.constructResponse(userList));
  } else {

     userList =  database.getAllUsers();
      ResponseBuilder a = new ResponseBuilder(userList);
      this.response = (a.constructResponse(userList));
  }

}

  @Override
  public String getResponseString() {
    return this.response;
  }
    }
