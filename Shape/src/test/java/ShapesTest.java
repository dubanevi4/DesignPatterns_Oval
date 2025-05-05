import lt.esdc.shapes.entity.Oval;
import lt.esdc.shapes.entity.Point;
import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;
import lt.esdc.shapes.factory.ShapeFactory;
import lt.esdc.shapes.repository.ShapeRepository;
import lt.esdc.shapes.validator.Validator;
import lt.esdc.shapes.validator.impl.OvalValidator;
import lt.esdc.shapes.validator.impl.InputStringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ShapesTest {
    private ShapeRepository repository;
    private Validator<Oval> ovalValidator;

    @BeforeMethod
    public void setUp() {
        Configurator.setRootLevel(Level.OFF); // Отключаем логи для тестов
        repository = ShapeRepository.getInstance();
        ovalValidator = new OvalValidator(); // Инициализируем валидатор
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
                {"Oval: «a; b; c; d»;"},    // Нечисловые значения
                {"Invalid format"}          // Неверный формат
        };
    }

    @Test(dataProvider = "validOvalStrings")
    public void testValidOvalCreation(String input) throws OvalProjectException {
        double[] coords = InputStringValidator.parseOvalCoordinates(input);
        Oval oval = (Oval) ShapeFactory.createShape(
                ShapeFactory.ShapeType.OVAL,
                new Point(coords[0], coords[1]),
                new Point(coords[2], coords[3])
        );

        assertTrue(ovalValidator.validate(oval).isValid()); // Проверяем валидность овала

        long id = repository.save(oval);
        Shape retrieved = repository.getById(id);

        assertNotNull(retrieved);
        assertEquals(retrieved, oval);
    }

    @Test(dataProvider = "invalidOvalStrings", expectedExceptions = OvalProjectException.class)
    public void testInvalidOvalCreation(String input) throws OvalProjectException {
        InputStringValidator.parseOvalCoordinates(input);
    }

    @Test
    public void testInvalidOvalValidation() {
        Oval invalidOval = new Oval(new Point(3.0, 3.0), new Point(3.0, 3.0)); // Точки совпадают

        assertFalse(ovalValidator.validate(invalidOval).isValid());
    }

    @Test
    public void testRepositorySingleton() {
        ShapeRepository instance1 = ShapeRepository.getInstance();
        ShapeRepository instance2 = ShapeRepository.getInstance();
        assertSame(instance1, instance2);
    }
}