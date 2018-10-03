package simpleserver;
import com.google.gson.*;
import java.util.ArrayList;

public class ResponseBuilder {

  int status;
  int entries;
  String response;

  public ResponseBuilder(ArrayList a){
    status = 1;
    entries = a.size();
  }

  public String constructResponse(ArrayList a) {
    Response in = new Response(status,entries,a);
    response = new Gson().toJson(in);
    return response;
  }

}


