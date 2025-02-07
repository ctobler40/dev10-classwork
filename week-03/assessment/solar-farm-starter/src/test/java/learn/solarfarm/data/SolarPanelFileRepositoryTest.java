package learn.solarfarm.data;

import learn.solarfarm.models.Material;
import learn.solarfarm.models.SolarPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SolarPanelFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/solarfarm-seed.txt";
    static final String TEST_FILE_PATH = "./data/solarfarm-test.txt";

    SolarPanelFileRepository repository = new SolarPanelFileRepository(TEST_FILE_PATH);

    /**
     * Runs before each test.
     * This is an (A)rrange step.
     * Takes an existing seed data file and copies its contents
     * into a test file. The repository then operates on the
     * test file.
     *
     * @throws IOException
     */
    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findBySection() throws DataAccessException {
        // Arrange happens in setupTest().

        // Act: test that the "The Ridge" section returns two solar panels
        List<SolarPanel> actual = repository.findBySection("The Ridge");
        // Assert
        assertEquals(2, actual.size());

        // Act: test that capitalization doesn't matter
        actual = repository.findBySection("THE RIDGE");
        // Assert
        assertEquals(2, actual.size());

        // Act: test that an empty list returned if a section doesn't have any solar panels
        actual = repository.findBySection("East Hill");
        // Assert
        assertEquals(0, actual.size());
    }

    @Test
    void findByKey() throws DataAccessException {
        // Arrange also happens in setupTest().
        // Arrange
        SolarPanel expected = new SolarPanel(1, "The Ridge", 1, 1, 2020, Material.POLY_SI, true);

        // Act: test that an existing solar panel can be retrieved
        SolarPanel actual = repository.findByKey("The Ridge", 1, 1);
        // Assert
        assertEquals(expected, actual);

        // Act: test that capitalization doesn't matter
        actual = repository.findByKey("THE RIDGE", 1, 1);
        // Assert
        assertEquals(expected, actual);

        // Act: test that attempting to retrieve a non-existent solar panel returns null
        actual = repository.findByKey("East Hill", 5, 6);
        // Assert
        assertNull(actual);
    }

    @Test
    void create() throws DataAccessException {
        // Arrange
        SolarPanel solarPanel = new SolarPanel();
        solarPanel.setSection("The Ridge");
        solarPanel.setRow(1);
        solarPanel.setColumn(10);
        solarPanel.setYearInstalled(2020);
        solarPanel.setMaterial(Material.A_SI);
        solarPanel.setTracking(true);

        SolarPanel expected = new SolarPanel(6, "The Ridge", 1, 10, 2020, Material.A_SI, true);

        // Act
        SolarPanel actual = repository.create(solarPanel);
        // Assert
        assertEquals(6, actual.getId());

        // Act
        List<SolarPanel> all = repository.findBySection("The Ridge");
        // Assert
        assertEquals(3, all.size());

        // the newly-added solar panel
        actual = all.get(2);
        // Assert
        assertEquals(expected, actual);
    }

    // TODO: Add test for update
    @Test
    void shouldUpdateSolarPanel() throws DataAccessException
    {
        SolarPanel solarPanel = new SolarPanel(1, "BigBoy", 1, 2, 2021, Material.POLY_SI, false);
        assertTrue(repository.update(solarPanel));
        assertEquals(solarPanel, repository.findByKey("BigBoy", 1, 2));

        SolarPanel solarPanel2 = new SolarPanel(1, "BigBoy", 1, 2, 2011, Material.CD_TE, true);
        assertTrue(repository.update(solarPanel2));
        assertEquals(solarPanel2, repository.findByKey("BigBoy", 1, 2));

        SolarPanel solarPanel3 = new SolarPanel(1, "BigBoy", 2, 2, 2002, Material.A_SI, false);
        assertTrue(repository.update(solarPanel3));
        assertEquals(solarPanel3, repository.findByKey("BigBoy", 2, 2));
    }

    // TODO: Add test for delete
    @Test
    void shouldDeleteSolarPanelByKey() throws DataAccessException
    {
        SolarPanel solarPanel = new SolarPanel(10, "Buick", 1, 1, 2021, Material.CIGS, false);
        repository.create(solarPanel);
        assertTrue(repository.deleteByKey(solarPanel));
        assertFalse(repository.deleteByKey(solarPanel));

        repository.create(solarPanel);
        SolarPanel solarPanel2 = new SolarPanel(7, "Trevor", 3, 1, 2018, Material.CD_TE, true);
        assertFalse(repository.deleteByKey(solarPanel2));
        assertTrue(repository.deleteByKey(solarPanel));
    }
}
