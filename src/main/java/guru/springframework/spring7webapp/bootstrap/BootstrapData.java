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

        Publisher ccc = new Publisher();
        ccc.setPublisherName("Gabriel");
        ccc.setAddress("Calle 21 22 22");
        ccc.setCity("Bogota");
        ccc.setState("Cundinamarca");
        ccc.setZip("110111");

        Author ericGuardado = authorRepository.save(eric);
        Book dddGuardado = bookRepository.save(ddd);
        Publisher cccGuardado = publisherRepository.save(ccc);

        Author rod = new Author();
        eric.setPrimerNombre("Rod");
        eric.setPrimerApellido("Johnson");

        Book noEJB = new Book();
        ddd.setName("J2EE Development without EJB");
        ddd.setIsbn("54757585");

        //asociacion
        Author rodGuardado = authorRepository.save(rod);
        Book noEJBGuardado = bookRepository.save(noEJB);


        ericGuardado.getBooks().add(dddGuardado);
        rodGuardado.getBooks().add(noEJBGuardado);

        // persistencia h2
        authorRepository.save(ericGuardado);
        authorRepository.save(rodGuardado);
        publisherRepository.save(cccGuardado);


        System.out.println("In bootstrap");
        System.out.println("Cantidad de autor: " + authorRepository.count());
        System.out.println("Cantidad libro: " + bookRepository.count());
        System.out.println("Cantidad publisher: " + publisherRepository.count());
    }
}
