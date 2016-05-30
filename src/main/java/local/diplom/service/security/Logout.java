package local.diplom.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created  by david on 03.04.16
 */
@WebServlet("/rest/secure/logout")
public class Logout extends HttpServlet {
    public static final Logger LOG = LoggerFactory.getLogger(Logout.class);
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            request.getSession().invalidate();
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            try {
                request.logout();
            } catch (ServletException e) {
                LOG.error(e.toString());
            }
        } catch (Exception e) {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "/login");
        }
    }

}
