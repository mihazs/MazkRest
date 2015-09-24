/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.entity.exception;

/**
 *
 * @author Mihael Zamin
 */
public class UserNotFoundException extends Exception {

    public enum Cause {
      LOGIN_NOT_FOUND,
      PASSWORD_NOT_FOUND
    };
    public UserNotFoundException() {
        super("User not found exception, Unknown cause");
        
    }
    public UserNotFoundException(Cause c) {
        super();
        switch(c)
        {
            case LOGIN_NOT_FOUND:
               
                System.out.println("UserNotFound: Invalid Login");
                break;
            case PASSWORD_NOT_FOUND:
                System.out.println("UserNotFound: Invalid Password");
                break;
        }
        
        
    }
    
    
    
}
