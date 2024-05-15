package org.acme.controller;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.acme.dto.ContaDTO;
import org.acme.dto.DepositoLimiteDTO;
import org.acme.service.ContaService;
import org.acme.service.exceptions.ObjectNotFoundException;

@Path("/api/conta")
@AllArgsConstructor
@Consumes("application/json")
@Produces(MediaType.APPLICATION_JSON)
public class ContaController {

    @Inject
    private ContaService contaService;

    @POST
    @Transactional
    public Response cadastrarConta(ContaDTO contaDTO){
           contaService.cadastrarConta(contaDTO);
            return Response.ok().build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object buscarContaId(@PathParam("id") Long id){
        try {
            return contaService.buscarContaId(id);
        }catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void editarConta(@PathParam("id") Long id, ContaDTO contaDTO){
        try{
            contaService.editarConta(contaDTO,id);
        }catch (ObjectNotFoundException e) {
            Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/alterarSaldo/{id}")
    @Transactional
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public void alterarSaldo(DepositoLimiteDTO dto, @PathParam("id") Long id){
        try{
            contaService.alterarSaldo(dto.getValor(),id);
        }catch (ObjectNotFoundException e) {
            Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
