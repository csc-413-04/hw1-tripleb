package simpleserver;

public class QueryFactory {

    public Query getQuery(String request, String args) {
      switch(request) {
        case "/users":
          return new UserQuery(args);

        case "/posts":
          return new PostQuery(args);
      }
      return null;
    }
  }

