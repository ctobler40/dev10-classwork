package learn.fund.domain;

import learn.fund.data.BackerRepository;
import learn.fund.models.Backer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static learn.fund.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

// In our service tests, we don't need a web environment
// so we add the annotation @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE).
// This provides IoC container dependencies but doesn't start a full mock web environment.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BackerServiceTest
{
    // The @MockBean annotation declaratively creates a mock object from the BackerRepository interface.
    // It's equivalent to this Mockito code.
    @MockBean
    BackerRepository repository;

    // A mocking framework like Mockito doesn't require concrete source code to implement an interface.
    // When our code runs, Mockito generates a class at runtime.
    // Then we configure specific behaviors in specific tests.
    // We don't have to consider every possible scenario in a test double.
    // We define the behavior when and where we need it.

    @Autowired
    BackerService service;

    /*
    Mockito has a rich API. For our purposes, it's enough to stub a mock method.
    A stub is a simplified implementation of a method where the actual implementation would be much more complex.

    Mockito's when method stubs the mock's method.
    thenReturn optionally adds a return value to the stubbed implementation.

    // Mock a List<String>.
    List<String> mockList = mock(List.class);

    // Stub: mockList.get(0) returns "red".
    when(mockList.get(0)).thenReturn("red");
    // Stub: mockList.get(1) returns "blue".
    when(mockList.get(1)).thenReturn("blue");
    // Stub: mockList.get(2) returns "yellow".
    when(mockList.get(2)).thenReturn("yellow");

    // Call our mock.
    System.out.println(mockList.get(0));   // red
    System.out.println(mockList.get(2));   // yellow

    // mockList.get(100) isn't stubbed so it returns null.
    System.out.println(mockList.get(100)); // null
     */

    // add
    @Test
    void shouldAdd() {
        // In shouldAdd, we stub the repository's add call and make it return a legit Backer.
        Backer arg = makeBacker(5);
        arg.setBackerId(0);

        // Arrange our mock repository.
        // `when` stubs the repository's `add` call.
        // `any` indicates that any argument is valid for `add`.
        // `thenReturn` will return the specified argument when
        // the mock is called.
        when(repository.add(any())).thenReturn(makeBacker(1));

        Result<Backer> expected = makePayloadResult(makeBacker(1));
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // When a negative test doesn't interact with the repository, the mock repository should never be called.
    @Test
    void shouldNotAddWithNullBacker() {
        Result<Backer> expected = makeInvalidResult("backer may not be null");
        Result<Backer> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullName() {
        Backer arg = makeBacker(0);
        arg.setName(null);
        Result<Backer> expected = makeInvalidResult("name is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyName() {
        Backer arg = makeBacker(0);
        arg.setName(" ");
        Result<Backer> expected = makeInvalidResult("name is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress(null);
        Result<Backer> expected = makeInvalidResult("email is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("  ");
        Result<Backer> expected = makeInvalidResult("email is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("notvalid");
        Result<Backer> expected = makeInvalidResult("not a valid email address");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateEmail() {
        // To trigger the duplicate email validation,
        // we stub our findByEmailAddress with when and thenReturn a backer with any email address.
        Backer arg = makeBacker(0);
        arg.setEmailAddress("backer1@example.com");

        when(repository.findByEmailAddress(any())).thenReturn(makeBacker(1));

        Result<Backer> expected = makeInvalidResult("duplicate email address");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // update
    @Test
    void shouldUpdate() {
        Backer arg = makeBacker(1);

        when(repository.update(any())).thenReturn(true);

        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateWithNullBacker() {
        Result<?> expected = makeInvalidResult("backer may not be null");
        Result<?> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullName() {
        Backer arg = makeBacker(1);
        arg.setName(null);
        Result<?> expected = makeInvalidResult("name is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyName() {
        Backer arg = makeBacker(1);
        arg.setName(" ");
        Result<?> expected = makeInvalidResult("name is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress(null);
        Result<?> expected = makeInvalidResult("email is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress("  ");
        Result<?> expected = makeInvalidResult("email is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress("notvalid");
        Result<?> expected = makeInvalidResult("not a valid email address");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateDuplicateEmail() {
        Backer arg = makeBacker(2);
        arg.setEmailAddress("backer1@example.com");

        when(repository.findByEmailAddress(any())).thenReturn(makeBacker(1));

        Result<?> expected = makeInvalidResult("duplicate email address");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Backer arg = makeBacker(2);
        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // delete
    @Test
    void shouldDelete() {
        // The mock repository's deleteById will always return false if not stubbed.
        // We stub it with an anyInt() argument and thenReturn(true).
        // If we want to target a specific ID, this would also work:
        // when(repository.deleteById(1)).thenReturn(true);

        when(repository.deleteById(anyInt())).thenReturn(true);
        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete() {
        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.deleteById(2);
        assertEquals(expected, actual);
    }
}