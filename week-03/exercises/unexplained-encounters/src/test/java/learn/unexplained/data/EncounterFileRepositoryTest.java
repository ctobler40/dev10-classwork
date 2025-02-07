package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    static final String TEST_PATH = "./data/encounters-test.csv";
    final Encounter[] testEncounters = new Encounter[]{
            new Encounter(1, EncounterType.UFO, "2020-01-01", "short test #1", 1),
            new Encounter(2, EncounterType.CREATURE, "2020-02-01", "short test #2", 1),
            new Encounter(3, EncounterType.SOUND, "2020-03-01", "short test #3", 1)
    };

    EncounterRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws DataAccessException {
        for (Encounter e : repository.findAll()) {
            repository.deleteById(e.getEncounterId());
        }

        for (Encounter e : testEncounters) {
            repository.add(e);
        }
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> encounters = repository.findAll();
        Encounter[] actual = encounters.toArray(new Encounter[encounters.size()]);
        assertArrayEquals(testEncounters, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void shouldFindByType() throws DataAccessException
    {
        // This would work since we are accessing the first index
        Encounter test = new Encounter(1,EncounterType.UFO,"2020-01-01","short test #1",1);
        List<Encounter> actual = repository.findByType(EncounterType.UFO);
        assertEquals(test, actual.get(0));

        // This would not work since there are < 1 instances of this test
        // assertEquals(test, actual.get(1));
    }

    @Test
    void shouldUpdate() throws DataAccessException
    {
        // Needs work
        Encounter test = new Encounter(1,EncounterType.SOUND,"2020-01-01","short test #1",1);
        assertTrue(repository.update(test));
        assertEquals(test, repository.findByType(EncounterType.SOUND).get(0));
    }

    @Test
    void shouldDeleteById() throws DataAccessException
    {
        assertTrue(repository.deleteById(2));
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }
}