package guru.springframework.spring7webapp.bootstrap;


import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;
import guru.springframework.spring7webapp.domain.Publisher;
import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author();
        eric.setPrimerNombre("Eric");
        eric.setPrimerApellido("Evans");

        Book ddd = new Book();
        ddd.setName("Domain Driver Design");
        ddd.setIsbn("123456");

        Author rod = new Author();
        rod.setPrimerNombre("Rod");
        rod.setPrimerApellido("Johnson");

        Book noEJB = new Book();
        noEJB.setName("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author ericGuardado = authorRepository.save(eric);
        Book dddGuardado = bookRepository.save(ddd);

        //asociacion
        Author rodGuardado = authorRepository.save(rod);
        Book noEJBGuardado = bookRepository.save(noEJB);

        // persistencia de autor a libro
        ericGuardado.getBooks().add(dddGuardado);
        rodGuardado.getBooks().add(noEJBGuardado);

        // persistencia de libro a autor
        dddGuardado.getAuthors().add(ericGuardado);
        noEJBGuardado.getAuthors().add(rodGuardado);

        Publisher ccc = new Publisher();
        ccc.setPublisherName("Gabriel");
        ccc.setAddress("Calle 21 22 22");
        Publisher savedPublisher = publisherRepository.save(ccc);

        //asignar publicador a libro
        dddGuardado.setPublisher(savedPublisher);
        noEJBGuardado.setPublisher(savedPublisher);

        // persistencia autor y de libro
        authorRepository.save(ericGuardado);
        authorRepository.save(rodGuardado);
        bookRepository.save(dddGuardado);
        bookRepository.save(noEJBGuardado);

        System.out.println("In bootstrap");
        System.out.println("Cantidad de autor: " + authorRepository.count());
        System.out.println("Cantidad libro: " + bookRepository.count());

        System.out.println("Cantidad publisher: " + publisherRepository.count());
    }
}
