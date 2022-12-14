package cn.tuyucheng.taketoday.microprofile.web;

import cn.tuyucheng.taketoday.microprofile.model.Book;
import cn.tuyucheng.taketoday.microprofile.repo.BookManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("books")
@RequestScoped
public class BookEndpoint {

    @Inject
    private BookManager bookManager;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") String id) {
        Book book = bookManager.get(id);
        return Response.ok(book).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        return Response.ok(bookManager.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Book book) {
        String bookId = bookManager.add(book);
        return Response.created(
                    UriBuilder.fromResource(this.getClass()).path(bookId).build())
              .build();
    }
}