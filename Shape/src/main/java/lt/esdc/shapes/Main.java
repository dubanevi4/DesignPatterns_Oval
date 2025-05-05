package lt.esdc.shapes;

import lt.esdc.shapes.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(Main.class);
        try {
            FileReader.readAndStoreShapes("D:\\InteliJ_workspace\\Java_lab_master\\Year2_semester2\\Shape\\Shape\\src\\main\\resources\\shapes.txt");
                logger.info("Shapes processing completed successfully");
        } catch (Exception e) {
                logger.error("Application error: {}", e.getMessage(), e);
        }
    }
}