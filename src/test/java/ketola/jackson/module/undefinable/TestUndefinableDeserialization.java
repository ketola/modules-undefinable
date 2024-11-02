package ketola.jackson.module.undefinable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestUndefinableDeserialization {
    @Test
    void testUndefinableIsDefined() throws JsonProcessingException {
        String json = """
            {
                "name": "John",
                "author": "Seppo"
            }
            """;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UndefinableModule());

        Book book = objectMapper.readValue(json, Book.class);

        assertThat(book)
            .satisfies(
                b -> assertThat(b.name()).isEqualTo("John"),
                b -> assertThat(b.author()).isInstanceOf(Undefinable.class),
                b -> assertThat(b.author().getValue()).satisfies(
                    authorValue -> assertThat(authorValue).isInstanceOf(String.class),
                    authorValue -> assertThat(authorValue).isEqualTo("Seppo")
                )
            );
    }

    @Test
    void testUndefinableIsNull() throws JsonProcessingException {
        String json = """
            {
                "name": "John",
                "author": null
            }
            """;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UndefinableModule());

        Book book = objectMapper.readValue(json, Book.class);

        assertThat(book)
            .satisfies(
                b -> assertThat(b.name()).isEqualTo("John"),
                b -> assertThat(b.author()).isInstanceOf(Undefinable.class),
                b -> assertThat(b.author().getValue()).isNull()
            );
    }

    @Test
    void testUndefinableIsUndefined() throws JsonProcessingException {
        String json = """
            {
                "name": "John"
            }
            """;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UndefinableModule());

        Book book = objectMapper.readValue(json, Book.class);

        assertThat(book)
            .satisfies(
                b -> assertThat(b.name()).isEqualTo("John"),
                b -> assertThat(b.author()).isInstanceOf(Undefinable.class),
                b -> assertThat(b.author()).isInstanceOf(Undefinable.Undefined.class)
            );
    }

    public record Book(String name, Undefinable<String> author) {
    }

}
