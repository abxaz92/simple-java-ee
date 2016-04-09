package local.diplom.service.security;

import local.diplom.service.common.ContextService;
import local.diplom.service.controller.SaleProductController;
import local.diplom.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created  by david on 03.04.16
 */
@WebServlet("/rest/secure/login")
public class Login extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(SaleProductController.class);

    @Inject
    private ContextService ctx;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ctx.getCurrentUser();
        LOG.info(ctx.getCurrentUserName());
        LOG.info(user.getUsername());
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        if ("manager".equals(user.getType()))
            resp.setHeader("Location", "/manager/");
        else
            resp.setHeader("Location", "/");
    }
}
