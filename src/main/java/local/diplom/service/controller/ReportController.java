package local.diplom.service.controller;

import local.diplom.service.service.ReportService;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by david on 04.06.16.
 */
@Path("/secure/report") // Путь на сайте
@Produces(MediaType.APPLICATION_JSON) // тип возврщаемых данных JSON
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReportController {
    @Inject
    private ReportService reportService;

    @GET
    @Path("/count/sales")
    public Object countSales(@QueryParam("start") Long start, @QueryParam("end") Long end) {
        return reportService.countSales(start, end);
    }
}
