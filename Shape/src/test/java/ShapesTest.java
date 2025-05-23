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

        assertTrue(ovalValidator.validate(oval).isValid(), "Овал должен быть валидным");

        repository.save(oval);
        Shape retrieved = repository.getById(oval.getID());

        assertNotNull(retrieved, "Фигура должна существовать в репозитории");
        assertEquals(retrieved, oval, "Фигура в репозитории должна совпадать с исходной");
    }

    @Test(dataProvider = "invalidOvalStrings")
    public void testInvalidOvalCreation(String input) {
        assertThrows(OvalProjectException.class, () -> InputStringValidator.parseOvalCoordinates(input));
    }

    @Test
    public void testInvalidOvalValidation() {
        Oval invalidOval = new Oval(new Point(3.0, 3.0), new Point(3.0, 3.0)); // Точки совпадают

        assertFalse(ovalValidator.validate(invalidOval).isValid(), "Овал с одинаковыми точками должен быть невалидным");
    }

    @Test
    public void testRepositorySingleton() {
        assertSame(ShapeRepository.getInstance(), ShapeRepository.getInstance(), "Репозиторий должен быть Singleton");
    }
}