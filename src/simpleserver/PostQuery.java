package simpleserver;

import java.util.ArrayList;

public class PostQuery implements Query {

    String response;
    String delim = "[=&]+";
    ArrayList postList;
    int para;
    Post post;

public void  maxLength(String length){
    int i = Integer.parseInt(length);
    String s = post.getPostData();
    s = s.substring(0, Math.min(s.length(), i));
}

public PostQuery(String url){

    Database instance = Database.getInstance();
    String [] arguments = url.split(delim);
    para = arguments.length;
    if(para > 2 ){
        post = instance.getPost(arguments[1]);
        postList.add(post);
        ResponseBuilder a = new ResponseBuilder(postList);
        this.response = (a.constructResponse(postList));
    } else {

            switch (arguments[0]){
                case "postid" :
                    post = instance.getPost(arguments[1]);
                    postList.add(post);
                    maxLength(arguments[3]);
                    ResponseBuilder a = new ResponseBuilder(postList);
                    this.response = (a.constructResponse(postList));

                    break;
                case "maxlength":
                    post = instance.getPost(arguments[3]);
                    maxLength(arguments[1]);
                    postList.add(post);
                    ResponseBuilder b = new ResponseBuilder(postList);
                    this.response = (b.constructResponse(postList));


            }
        }
    }

    @Override
    public String getResponseString() {
        return this.response;
    }
}

