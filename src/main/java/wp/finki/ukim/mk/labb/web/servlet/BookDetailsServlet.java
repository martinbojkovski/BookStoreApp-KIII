package wp.finki.ukim.mk.labb.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import wp.finki.ukim.mk.labb.service.BookService;

import java.io.IOException;

@WebServlet(name="details",urlPatterns = "/bookDetails")
public class BookDetailsServlet extends HttpServlet {
    private final BookService bookService;
//
    private final SpringTemplateEngine springTemplateEngine;

    public BookDetailsServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("book",bookService.findBookByIsbn((String) req.getSession().getAttribute("isbn")));
        springTemplateEngine.process("bookDetails.html",context,resp.getWriter());
    }
}
