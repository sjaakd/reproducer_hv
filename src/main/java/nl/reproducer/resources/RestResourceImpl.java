package nl.reproducer.resources;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.reproducer.model.BadRequest;
import nl.reproducer.model.Request;
import org.jboss.logging.Logger;

public class RestResourceImpl implements RestResource {

    private static final Logger LOG = Logger.getLogger( RestResourceImpl.class );

    @Inject
    Validator validator;

    public Response register(Request req) {

        LOG.debug( String.format( "register: %s", req ) );

        Set<ConstraintViolation<Request>> result = validator.validate( req );
        if ( !result.isEmpty() ) {
            List<String> errors  = result.stream().map( ConstraintViolation::getMessage ).collect( Collectors.toList() );
            return Response.status( Response.Status.BAD_REQUEST ).entity( new BadRequest( errors ) ).type( MediaType.APPLICATION_JSON ).build();
        }

        return Response.ok().entity( "ok" ).type( MediaType.TEXT_PLAIN_TYPE ).build();
    }
}
