package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.acme.dto.QuickSetupDTO;
import org.acme.service.QuickSetupService;

@Path("/api/quickSetup")
@AllArgsConstructor
public class QuickSetupController {

    @Inject
    private QuickSetupService quickSetupService;

    @POST
    @Transactional
    public Response quickSetup(QuickSetupDTO quickSetupDTO){
        quickSetupService.quickSetup(quickSetupDTO);
        return Response.ok().build();
    }
}
