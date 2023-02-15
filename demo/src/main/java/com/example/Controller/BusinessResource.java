package com.example.Controller;


import com.example.ApiPath;
import com.example.DTO.BusinessDTO;
import com.example.DTO.BusinessSaveDTO;
import com.example.DTO.BusinessUpdateDTO;
import com.example.Entity.Business;
import com.example.Mapper.BusinessMapper;
import com.example.Mapper.BusinessSaveMapper;
import com.example.Mapper.BusinessUpdateMapper;
import com.example.Repository.BusinessRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.PageRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.ApiPath.*;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
@Path(BUSINESS)

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BusinessResource {

    @Inject
    BusinessRepository businessRepository;
    @Inject
    BusinessMapper mapper;

    @Inject
    BusinessSaveMapper mapper1;

    @Inject
    BusinessUpdateMapper mapper2;

    @GET
    @Path(BUSINESSES)
    public Response getAll() {
        List<BusinessDTO> businesss = businessRepository.listAll().stream()
                .map(business ->mapper.toDTO(business) )
                .collect(Collectors.toList());
        return Response.ok(businesss).build();
    }

    @GET
    @Path(BUSINESSID + "{id}")
    public Response getById(@PathParam("id") Long id) {
        return businessRepository
                .findByIdOptional(id)
                .map(business -> Response.ok(mapper.toDTO(business)).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path(BUSINESSNAME + "{name}")
    public Response getByName(@PathParam("name") String name) {
        return businessRepository.find("name",name)
                .singleResultOptional()
                .map(business -> Response.ok(mapper.toDTO(business)).build())
                .orElse(Response.status(NOT_FOUND).build());
    }


    @GET
    @Path(BUSINESSLNAME + "{lname}")
    public Response getByLName(@PathParam("lname") String lname) {
        return businessRepository.find("lname",lname)
                .singleResultOptional()
                .map(business -> Response.ok(mapper.toDTO(business)).build())
                .orElse(Response.status(NOT_FOUND).build());
    }


    @GET
    @Path(BUSINESSABN  + "{acn_Number}")
    public Response getByacn(@PathParam("acn_Number") int acn_Number) {
        return businessRepository.find("acn_Number",acn_Number)
                .singleResultOptional()
                .map(business -> Response.ok(mapper.toDTO(business)).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path(BUSINESSIDACN  + "{abn_Number}")
    public Response getByabn(@PathParam("abn_Number") int abn_Number) {
        return businessRepository.find("abn_Number",abn_Number)
                .singleResultOptional()
                .map(business -> Response.ok(mapper.toDTO(business)).build())
                .orElse(Response.status(NOT_FOUND).build());
    }


    @POST
    @Path(ADDRECORD)
    @Transactional
    public Response create(BusinessSaveDTO businessSaveDTO) {
        Business business=mapper1.toDAO(businessSaveDTO);
        businessRepository.persist(business);
        if (businessRepository.isPersistent(business)) {
            return Response.created(URI.create("/api/v1/business/" + business.getId())).build();
        }
        return Response.status(NOT_FOUND).build();
    }

    @DELETE
    @Path(DELETE_RECORD +"{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = businessRepository.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(BAD_REQUEST).build();
    }



    @PUT
    @Path(UPDATE_RECORD+"{id}")
    @Transactional
    public Response updateBusiness(BusinessUpdateDTO businessUpdateDTO, @PathParam("id") Long id) {
        return businessRepository
                .findByIdOptional(id)
                .map(
                        businessToUpdate -> {
                            Business businessUpdated = mapper2.toDAO(businessUpdateDTO);
                            mapper2.merge(businessToUpdate, businessUpdated);
                            return Response.ok(mapper2.toDTO(businessToUpdate)).build();

                        })
                .orElse(Response.status(NOT_FOUND).build());
    }



    @POST
    @Path(SEARCH + "{id}")
    public Response searchBusinesses(@PathParam("id") Long id,
                                     @QueryParam("pageNo") int pageNo,
                                     @QueryParam("pageSize") int pageSize,
                                     @QueryParam("sortBy") String sortBy,
                                     @QueryParam("sortDir") String sortDir) {

        // Create Sort object based on sorting direction and field name
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create Pageable object based on pagination parameters and Sort object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Find businesses with the given id and pagination parameters
        Page<Business> businesses = BusinessRepository.find("id", id, pageable);

        // Map the entities to DTOs
        List<Business> listOfPosts = businesses.getContent();
        List<BusinessDTO> content = listOfPosts
                .stream()
                .map(business -> BusinessMapper.INSTANCE.toDTO(business))
                .collect(Collectors.toList());

        // Create and return response object
        Response response = new Response();
        response.setContent(content);
        response.setPageNo(businesses.getNumber());
        response.setPageSize(businesses.getSize());
        response.setTotalElements(businesses.getTotalElements());
        response.setTotalPages(businesses.getTotalPages());
        response.setLast(businesses.isLast());

        return Response.ok(response).build();
    }













}
