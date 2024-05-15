package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.acme.dto.CompraDTO;
import org.acme.dto.QuickSetupDTO;
import org.acme.service.CompraService;

@Path("/api/comprarCreditoDebito")
@AllArgsConstructor
@Consumes("application/json")
public class CompraController {

    @Inject
    private CompraService compraService;


    @POST
    @Transactional
    public Response comprarCreditoDebito(CompraDTO compraDTO){
        compraService.comprarCreditoDebito(compraDTO);
        return Response.ok().build();
    }
}
