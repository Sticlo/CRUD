import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Modelo.Categoria;

public class CategoriaTest {
    @Test
    public void testGettersAndSetters() {
        Categoria cat = new Categoria();
        cat.setId_categoria(1);
        cat.setNombre("Bebidas");
        cat.setDescripcion("Categoria de bebidas");

        assertEquals(1, cat.getId_categoria());
        assertEquals("Bebidas", cat.getNombre());
        assertEquals("Categoria de bebidas", cat.getDescripcion());
    }
}
