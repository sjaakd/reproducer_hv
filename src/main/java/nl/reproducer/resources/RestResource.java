package nl.reproducer.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.reproducer.model.BadRequest;
import nl.reproducer.model.Request;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path( "" )
public interface RestResource {

    @POST
    @Path( "/reproduce" )
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN } )
    @Operation(
        operationId = "example",
        summary = "Summary",
        description = "The Description"
    )
    @RequestBody(
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            examples =
            @ExampleObject(
                name = "Reproducer",
                description = "A small JSON Class",
                value = "{\n"
                    + "  \"field1\": \"test1\",\n"
                    + "  \"field2\": \"test2\"\n"
                    + "}"
            )
        )
    )
    @APIResponse(
        responseCode = "200",
        content = @Content( mediaType = MediaType.TEXT_PLAIN ),
        description = "The result."
    )
    @APIResponse(
        responseCode = "400",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema( implementation = BadRequest.class )
        ),
        description = "Bad request result."
    )
    Response register(Request request);

}
