package learn.field_agent.data;

import learn.field_agent.data.KnownGoodState;
import learn.field_agent.data.SecurityClearanceJdbcTemplateRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 9;

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> scs = repository.findAll();
        assertNotNull(scs);

        // can't predict order
        // if delete is first, we're down to 7
        // if add is first, we may go as high as 10
        assertTrue(scs.size() >= 7 && scs.size() <= 10);
    }

    @Test
    void shouldAdd() {
        // all fields
        SecurityClearance sc = makeSecurityClearance();
        SecurityClearance actual = repository.add(sc);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getSecurityClearanceId());

        // null dob
        sc = makeSecurityClearance();
        sc.setName(null);
        actual = repository.add(sc);
        assertNotNull(actual);
        assertEquals(NEXT_ID + 1, actual.getSecurityClearanceId());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance sc = makeSecurityClearance();
        sc.setSecurityClearanceId(3);
        assertTrue(repository.update(sc));
        sc.setSecurityClearanceId(13);
        assertFalse(repository.update(sc));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(NEXT_ID));
        assertFalse(repository.deleteById(NEXT_ID));
    }

    private SecurityClearance makeSecurityClearance() {
        SecurityClearance sc = new SecurityClearance();
        sc.setName("Test");
        return sc;
    }
}