package br.com.mesttra.person.service;

import br.com.mesttra.person.controller.BookController;
import br.com.mesttra.person.data.vo.v1.BookVO;
import br.com.mesttra.person.entity.Book;
import br.com.mesttra.person.exceptions.ResourceNotFoundException;
import br.com.mesttra.person.mapper.DozerMapper;
import br.com.mesttra.person.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository bookRepository;

    public List<BookVO> findAll() {

        logger.info("Encontrando todas os livros!");


        var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;

    }

    public BookVO findById(Long id) {

        logger.info("Encontrando um livro!");


       var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + id));

        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }


    public BookVO createBook(BookVO book) {

        logger.info("Criando um livro!");

        var entity = DozerMapper.parseObject(book, Book.class);

        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);

        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;


    }

    public BookVO updateBook(BookVO book) {

        logger.info("Atualizando um livro!");

        var entity = bookRepository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + book.getKey()));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);

        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        logger.info("Removendo um livro!");

        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sem registro para o id: " + id));

        bookRepository.delete(entity);

    }

}
