package com.example;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
@Path("/api/v1/business")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BusinessResource {

    @Inject
    BusinessRepository businessRepository;

    @GET
    @Path("/get")
    public Response getAll() {
        List<Business> business = businessRepository.listAll();
        return Response.ok(business).build();
    }

    @GET
    @Path("/get/id/{id}")
    public Response getById(@PathParam("id") Long id) {
        return businessRepository
                .findByIdOptional(id)
                .map(business -> Response.ok(business).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("/get/name/{name}")
    public Response getByName(@PathParam("name") String name) {
        return businessRepository.find("name",name)
                .singleResultOptional()
                .map(business -> Response.ok(business).build())
                .orElse(Response.status(NOT_FOUND).build());
    }


    @GET
    @Path("/get/lname/{lname}")
    public Response getByLName(@PathParam("lname") String lname) {
        return businessRepository.find("lname",lname)
                .singleResultOptional()
                .map(business -> Response.ok(business).build())
                .orElse(Response.status(NOT_FOUND).build());
    }


    @GET
    @Path("/get/acn/{acn_Number}")
    public Response getByacn(@PathParam("acn_Number") int acn_Number) {
        return businessRepository.find("acn_Number",acn_Number)
                .singleResultOptional()
                .map(business -> Response.ok(business).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("/get/acn/{abn_Number}")
    public Response getByabn(@PathParam("abn_Number") int abn_Number) {
        return businessRepository.find("abn_Number",abn_Number)
                .singleResultOptional()
                .map(business -> Response.ok(business).build())
                .orElse(Response.status(NOT_FOUND).build());
    }


    @POST
    @Path("/post")
    @Transactional
    public Response create(Business business) {
        businessRepository.persist(business);
        if (businessRepository.isPersistent(business)) {
            return Response.created(URI.create("/api/v1/business/" + business.getId())).build();
        }
        return Response.status(NOT_FOUND).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = businessRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(BAD_REQUEST).build();
    }

}
