package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    // at runtime when spring gets a request to /books will execute the getBooks method, provide that method a model object
    // for that model we're going add the attribute books, which will execute bookRepository (in db) and give a list of books
    // model will get returned back to the view layer, which can then be used in the view to the client

    private final BookRepository bookRepository;

    // will DI an instance of book repository into the controller
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository; // interface that uses constructor injection with a DB to get book entities
    }

    @RequestMapping("/books") // maps HTTP requests to the getBooks method
    public String getBooks(Model model) { // model is an interface used in Spring MVC to pass data from the controller to the view

        // model.addAttribute adds attribute named books to the model, which contains all books retrieved from the repo
        // books is the key used to access this data in the view such as ${books}
        model.addAttribute("books", bookRepository.findAll()); // calls repo to retrieve all book entities from DB
        return "books/list"; // the view name you want rendered, such as list.html found in the template dir
    }
}
