package org.usman.blog_spring_boot.Error;

public class IdNotFoundException  extends  RuntimeException {

    public IdNotFoundException(String string){
        super(string);
    }
}
