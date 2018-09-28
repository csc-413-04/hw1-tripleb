package simpleserver;

public class QueryFactory {


    public static queryFactory (String request, String[] args) {
      switch(request) {
        case "/users":
          return new (args);
        break;
        case "/posts":
          return new PostProcessor(args);
        break;
      }
      return null;
    }
  }

