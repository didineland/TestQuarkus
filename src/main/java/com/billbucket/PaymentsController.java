package com.billbucket;

import io.quarkus.security.Authenticated;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

@Path("/payment")
@RequestScoped
public class PaymentsController {

    private static final Logger LOG = Logger.getLogger(PaymentsController.class);


    @GET
    @Path("permit-all")
    @Authenticated
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello permit alls";
    }


    @GET
    @Path("permit-scope")
    @RolesAllowed("lo")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloScope() {
        return "Hello permit scope";
    }



    @POST
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public PaymentDto postPayment(PaymentDto paymentDto) {

        var newPayment = new Payment();
        newPayment.amount = paymentDto.amount;
        newPayment.persist();

        return paymentDto;
    }

    @GET
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Payment> postPayment() {

        LOG.info("blabla");

        return Payment.find("amount < ?1", new BigDecimal(100)).list();

        //return Payment.list("amount", new BigDecimal(100));
        //return Payment.listAll();
    }
}