package vn.edu.iuh.fit.week3_rest.api;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.week3_rest.business.BaseProcess;
import vn.edu.iuh.fit.week3_rest.modals.Student;

import java.util.ArrayList;
import java.util.List;

@Path("/students")
public class StudentResource {
    @Inject
    private BaseProcess process;
    @GET
    public Response getStudents() {
//        List<Student> students = new ArrayList<Student>();
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(process.getStudents());
        return builder.build();
    }
    @GET
    @Path("/id")
    public Response getStudentById(@QueryParam("id") long id) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(process.getStudentById(id));
        return builder.build();
    }


}
