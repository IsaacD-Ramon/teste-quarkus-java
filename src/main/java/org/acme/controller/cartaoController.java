package org.acme.controller;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.acme.dto.CartaoDTO;
import org.acme.dto.DepositoLimiteDTO;
import org.acme.service.CartaoService;
import org.acme.service.exceptions.ObjectNotFoundException;

@Path("/api/cartao")
@AllArgsConstructor
@Consumes("application/json")
public class cartaoController {

    @Inject
    private CartaoService cartaoService;

    @POST
    @Transactional
    public Response cadastrarCartao(CartaoDTO cartaoDTO){
        cartaoService.cadastrarCartao(cartaoDTO);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object buscarCartaoId(@PathParam("id") Long id){
        try {
            return cartaoService.buscarCartaoId(id);
        }catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void editarCartao(@PathParam("id") Long id, CartaoDTO cartaoDTO){
        try{
            cartaoService.editarCartao(cartaoDTO,id);
        }catch (ObjectNotFoundException e) {
            Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/alterarLimite/{id}")
    @Transactional
    public void alterarLimite(@PathParam("id") Long id, DepositoLimiteDTO dto){
        try{
            cartaoService.alterarLimite(dto.getValor(),id);
        }catch (ObjectNotFoundException e) {
            Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
