package com.example.guide;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.guide.bases.Base;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkGetId() {
        Base base = new Base();
        base.setId(1);
        int id = base.getId();
        assertEquals(id, 1);
    }

    @Test
    public void checkBaseModel() {
        Base base = new Base("База отдыха «Медная сова»", "База отдыха «Медная сова» находится в Курьинском районе на въезде в село Колывань, приблизительно в 1 км от трассы Курья – Бугрушиха. Она расположена в березовой роще, граничащей с бором.", "https://visitaltai.info/upload/webp/530/resize_cache/iblock/60b/530_320_2/hinat5bvu9odx9jcutusz85ix5efvy3k.webp");
        assertEquals("База отдыха «Медная сова»", base.getTitle());
        assertEquals("База отдыха «Медная сова» находится в Курьинском районе на въезде в село Колывань, приблизительно в 1 км от трассы Курья – Бугрушиха. Она расположена в березовой роще, граничащей с бором.", base.getDescription());
        assertEquals("https://visitaltai.info/upload/webp/530/resize_cache/iblock/60b/530_320_2/hinat5bvu9odx9jcutusz85ix5efvy3k.webp", base.getImageUrl());
    }
}