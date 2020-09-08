package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Author chris = new Author("Chris", "Evans");
    Book ddd = new Book("Design Driven Development", "324556");
    chris.getBooks().add(ddd);
    ddd.getAuthors().add(chris);

    authorRepository.save(chris);
    bookRepository.save(ddd);

    Author at = new Author("Ivo", "Marulic");
    Book sg = new Book("Sodoma i gomora", "4536347");
    chris.getBooks().add(sg);
    ddd.getAuthors().add(at);

    authorRepository.save(at);
    bookRepository.save(sg);

    Publisher pub = new Publisher("Znanje", "Vukovarska 23", "Zagreb", "Croatia", "3325");
    publisherRepository.save(pub);

    ddd.setPublisher(pub);
    sg.setPublisher(pub);

    pub.getBooks().add(ddd);
    pub.getBooks().add(sg);

    publisherRepository.save(pub);

    System.out.println("Started in BootStrap");
    System.out.println("Number of authors: " + authorRepository.count());
    System.out.println("Number of books: " + bookRepository.count());
    System.out.println("Number of publishers: " + publisherRepository.count());
    System.out.println("Publisher number of books: " + pub.getBooks().size());

  }
}
