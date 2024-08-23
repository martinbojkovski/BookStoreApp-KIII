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
import wp.finki.ukim.mk.labb.service.AuthorService;
import wp.finki.ukim.mk.labb.service.BookService;

import java.io.IOException;

@WebServlet(name="author",urlPatterns = "/author")
public class АuthorServlet extends HttpServlet {
    private final AuthorService authorService;

    private final BookService bookService;

    private final SpringTemplateEngine springTemplateEngine;

    public АuthorServlet(AuthorService authorService, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("authors",authorService.listAuthors());
        context.setVariable("isbn",req.getSession().getAttribute("isbn"));
        springTemplateEngine.process("authorList.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("authorId");
        bookService.addAuthorToBook(id,(String) req.getSession().getAttribute("isbn"));


        resp.sendRedirect("/bookDetails");

    }
}
