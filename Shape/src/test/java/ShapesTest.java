import com.shapes.entity.Point;
import com.shapes.entity.Shape;
import com.shapes.factory.ShapeFactory;
import com.shapes.repository.ShapeRepository;
import com.shapes.validator.InputStringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ShapesTest {
    private ShapeRepository repository;

    @BeforeMethod
    public void setUp() {
        Configurator.setRootLevel(Level.OFF); // Отключаем логи для тестов
        repository = ShapeRepository.getInstance();
    }

    @AfterMethod
    public void tearDown() {
        repository.clear();
    }

    @DataProvider
    public Object[][] validOvalStrings() {
        return new Object[][] {
                {"Oval: «1.0; 1.0; 5.0; 5.0»;"},
                {"Oval: «0.0; 0.0; 10.0; 10.0»;"},
                {"Oval: «-1.0; -1.0; 1.0; 1.0»;"}
        };
    }

    @DataProvider
    public Object[][] invalidOvalStrings() {
        return new Object[][] {
                {"Oval: «1.0; 2.0; 3.0»;"}, // Не хватает значения
                {"Oval: «a; b; c; d»;"},     // Нечисловые значения
                {"Invalid format"}            // Неверный формат
        };
    }

    @Test(dataProvider = "validOvalStrings")
    public void testValidOvalCreation(String input) {
        double[] coords = InputStringValidator.parseOvalCoordinates(input);
        Shape oval = ShapeFactory.createShape(
                ShapeFactory.ShapeType.OVAL,
                new Point(coords[0], coords[1]),
                new Point(coords[2], coords[3])
        );

        long id = repository.save(oval);
        Shape retrieved = repository.getById(id);

        assertNotNull(retrieved);
        assertEquals(retrieved, oval);
    }

    @Test(dataProvider = "invalidOvalStrings", expectedExceptions = IllegalArgumentException.class)
    public void testInvalidOvalCreation(String input) {
        InputStringValidator.parseOvalCoordinates(input);
    }

    @Test
    public void testRepositorySingleton() {
        ShapeRepository instance1 = ShapeRepository.getInstance();
        ShapeRepository instance2 = ShapeRepository.getInstance();
        assertSame(instance1, instance2);
    }
}
