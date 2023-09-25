package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    @Test
    void shouldAdd() {
        SecurityClearance sc = new SecurityClearance(5, "TEST");
        SecurityClearance mockOut = new SecurityClearance(5, "TEST");

        when(repository.add(sc)).thenReturn(mockOut);

        Result<SecurityClearance> actual = service.add(sc);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {

        SecurityClearance sc = new SecurityClearance(-35, "TEST");

        Result<SecurityClearance> actual = service.add(sc);
        assertEquals(ResultType.INVALID, actual.getType());

        sc.setSecurityClearanceId(0);
        sc.setName(null);
        actual = service.add(sc);
        assertEquals(ResultType.INVALID, actual.getType());

        sc.setName(" ");
        actual = service.add(sc);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance sc = new SecurityClearance(5, "TEST");

        when(repository.update(sc)).thenReturn(true);
        Result<SecurityClearance> actual = service.update(sc);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        SecurityClearance sc = new SecurityClearance(35, "TEST");

        when(repository.update(sc)).thenReturn(false);
        Result<SecurityClearance> actual = service.update(sc);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        SecurityClearance sc = new SecurityClearance(35, null);

        Result<SecurityClearance> actual = service.update(sc);
        assertEquals(ResultType.INVALID, actual.getType());

        sc.setName("TEST");
        actual = service.update(sc);
        assertEquals(ResultType.NOT_FOUND, actual.getType());

        sc.setSecurityClearanceId(0);
        actual = service.update(sc);
        assertEquals(ResultType.INVALID, actual.getType());
    }

}