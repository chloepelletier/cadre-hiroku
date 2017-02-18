package projet.cadre.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import projet.cadre.managers.CadreLibrary;
import projet.cadre.model.Contact;


@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(new Java8TimeDialect());	
		WebContext context = new WebContext(req, resp, req.getServletContext());

		templateEngine.process("page-contact", context, resp.getWriter());

}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nomContact = req.getParameter("nomContact");
		String mailContact = req.getParameter("mailContact"); 
		String societeContact = req.getParameter("societeContact"); 
		String messageContact = req.getParameter("messageContact"); 
		Contact contact= new Contact(nomContact, mailContact, societeContact, messageContact);
		CadreLibrary.getInstance().saveContact(contact);
		resp.sendRedirect("contact");

}
	
}
