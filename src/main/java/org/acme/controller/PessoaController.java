package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.acme.dto.PessoaDTO;
import org.acme.service.PessoaService;
import org.acme.service.exceptions.ObjectNotFoundException;

@Path("/api/pessoa")
@AllArgsConstructor
@Consumes("application/json")
public class PessoaController {

    @Inject
    private PessoaService pessoaService;

    @POST
    @Transactional
    public Response salvarPessoa(PessoaDTO pessoaDTO){

        try {
            pessoaService.CadastrarPessoa(pessoaDTO);
            return Response.ok().build();
        }catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object buscarPessoaCpf(@PathParam("cpf") String cpf){
        try {
            return pessoaService.buscarPessoaCpf(cpf);
        }catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
        }

    @PUT
    @Path("/{id}")
    @Transactional
    public void EditarPessoa(@PathParam("id") Long id, PessoaDTO pessoaDTO){
        try{
            pessoaService.EditarPessoa(pessoaDTO,id);
        }catch (ObjectNotFoundException e) {
        Response.status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
    }
    }
}
