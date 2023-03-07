package org.usman.blog_spring_boot.error;

public class IdNotFoundException  extends  RuntimeException {

//    when there is no Id
    public IdNotFoundException(String string){
        super(string);
    }
}
