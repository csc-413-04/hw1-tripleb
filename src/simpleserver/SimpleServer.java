package simpleserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

class SimpleServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ding;
        Socket dong = null;
        String resource = null;
        String queryResponse = "";
        URL requestUrl;

        //Initializes database on server start.
        Database database = Database.getInstance();
        QueryFactory queryFactory = new QueryFactory();
        
        try {
            ding = new ServerSocket(1299);
            System.out.println("Opened socket " + 1299);
            while (true) {
                // keeps listening for new clients, one at a time
                try {
                    dong = ding.accept(); // waits for client here
                } catch (IOException e) {
                    System.out.println("Error opening socket");
                    System.exit(1);
                }

                InputStream stream = dong.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                try {
                    // read the first line to get the request method, URI and HTTP version
                    String line = in.readLine();
                    System.out.println("----------REQUEST START---------");
                    System.out.println(line);
                    // read only headers
                    line = in.readLine();
                    while (line != null && line.trim().length() > 0) {
                        int index = line.indexOf(": ");
                        if (index > 0) {
                            if (line.contains("Referer")) {
                                requestUrl = new URL(line.substring(9));
                                String path = requestUrl.getPath().substring(1);
                                String query = requestUrl.getQuery();
                                queryResponse = queryFactory.getQuery(path, query).toString();
                            }
                        } else {
                            break;
                        }
                        line = in.readLine();
                    }
                    System.out.println("----------REQUEST END---------\n\n");
                } catch (IOException e) {
                    System.out.println("Error reading");
                    System.exit(1);
                }

                BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
                PrintWriter writer = new PrintWriter(out, true);  // char output to the client

                // every response will always have the status-line, date, and server name
                writer.println("HTTP/1.1 200 OK");
                writer.println("Server: TEST");
                writer.println("Connection: close");
                writer.println("Content-type: text/html");
                writer.println("");

                // Body of our response
                writer.println("<h1>" + queryResponse + "</h1>");

                dong.close();
            }
        } catch (IOException e) {
            System.out.println("Error opening socket");
            System.exit(1);
        }
    }
}