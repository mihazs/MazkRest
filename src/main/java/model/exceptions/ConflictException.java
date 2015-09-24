/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.exceptions;


import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;
 

/**
 * Thrown to return a 409 Conflict response with optional Location header and entity.
 * @author Mihael Zamin 
 */
public class ConflictException extends WebApplicationException
{
    private static final long serialVersionUID = 1L;
 
    public ConflictException()
    {
        this(null, null);
    }
 
    public ConflictException(URI location)
    {
        this(location, null);
    }
 
    public ConflictException(URI location, Object entity)
    {
        super(Response.status(Status.CONFLICT).location(location).entity(entity).build());
    }
}
