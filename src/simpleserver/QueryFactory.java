package simpleserver;

public class QueryFactory {

    public static Processor makeProcessor(String request, String[] args) {
      switch(request) {
        case "/users":
          return new UserProcessor(args);
        break;
        case "/posts":
          return new PostProcessor(args);
        break;
      }
      return null;
    }
  }

