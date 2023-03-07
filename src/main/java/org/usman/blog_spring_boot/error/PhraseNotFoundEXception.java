package org.usman.blog_spring_boot.error;

public class PhraseNotFoundEXception extends RuntimeException{

//when a search returns empty
    public PhraseNotFoundEXception(String string){
        super(string);
    }
}
