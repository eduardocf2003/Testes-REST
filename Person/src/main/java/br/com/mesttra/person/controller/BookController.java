package br.com.mesttra.person.controller;

import br.com.mesttra.person.data.vo.v1.BookVO;
import br.com.mesttra.person.data.vo.v1.PersonVO;
import br.com.mesttra.person.service.PersonService;
import br.com.mesttra.person.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "BookEndpoint", description = "REST API for Book")
public class BookController {

    @Autowired
    private br.com.mesttra.person.service.BookServices bookService;


    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find book by id", description = "Find book by id", tags = {"BookEndpoint"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content)
    })
    public BookVO findById(
            @PathVariable(value = "id") Long id) {


        return bookService.findById(id);

    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all", description = "This method find all books", tags = {"BookEndpoint"}
            , responses = {@ApiResponse(description = "Success", responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))

                    )}), @ApiResponse(description = "Bad Request", responseCode = "400"),
            @ApiResponse(description = "Unauthorized", responseCode = "401"),
            @ApiResponse(description = "Not Found", responseCode = "404"),
            @ApiResponse(description = "Internal Error", responseCode = "500")})
    public List<BookVO> findAll() {


        return bookService.findAll();

    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new book", description = "Adds a new book", tags = {"BookEndpoint"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            })
    public BookVO create(@RequestBody BookVO book) {


        return bookService.createBook(book);

    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Update a book", description = "Update a book", tags = {"BookEndpoint"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content)
            })
    public BookVO update(@RequestBody BookVO book) {


        return bookService.updateBook(book);

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a book", description = "Delete a book", tags = {"BookEndpoint"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        bookService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
