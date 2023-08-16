import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Exercise03Test
{
    @Test
    void surroundWithTags()
    {
        assertEquals(true, Exercise03.hasAllVowels("a"));
        assertEquals(true, Exercise03.hasAllVowels("aeiou"));
        assertEquals(false, Exercise03.hasAllVowels("splendid"));
        assertEquals(false, Exercise03.hasAllVowels("aaeeiioot"));
        assertEquals(true, Exercise03.hasAllVowels("aeioaueoiaueoiaeuoaeiuaoeiu"));
        assertEquals(false, Exercise03.hasAllVowels("tear"));
    }
}