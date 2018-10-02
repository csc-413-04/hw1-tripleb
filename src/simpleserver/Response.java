package simpleserver;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;


public class Response{
private int status, entries;
private ArrayList data;

public Response(int _status, int _entries, ArrayList _a){
  status = _status;
  entries = _entries;
  data = _a;
  }

  public ArrayList getData() {
    return data;
  }

  public int getStatus() {
    return status;
  }

  public int getEntries() {
    return entries;
  }
}