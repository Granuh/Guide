package com.example.guide;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.guide", appContext.getPackageName());
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    /*
    // Проверка наличия
    @Test
    public void checkSearchView_isDisplayed() {
        onView(withId(R.id.searchView))
                .check(matches(isDisplayed()));
    }

    // Проверка наличия
    @Test
    public void checkTextViewWhereStay_isDisplayed() {
        onView(withId(R.id.tvWhereStay))
                .check(matches(isDisplayed()));
    }

    // Проверка наличия
    @Test
    public void checkTextViewDescription_isDisplayed() {
        onView(withId(R.id.tvDescription))
                .check(matches(isDisplayed()));
    }

    // Проверка отображает нужный текст
    @Test
    public void checkTextViewWhereStay_hasCorrectText() {
        onView(withId(R.id.tvWhereStay))
                .check(matches(withText("Где остановится")));
    }

    // Проверка отображает нужный текст
    @Test
    public void checkTextViewDescription_hasCorrectText() {
        onView(withId(R.id.tvDescription))
                .check(matches(withText("Популярные места, на основании нашей статистики.")));
    }

    // Проверка невидимости
    @Test
    public void checkTextViewWhereStay_isGone() {
        onView(withId(R.id.tvWhereStay))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    // Проверка невидимости
    @Test
    public void checkTextViewDescription_isGone() {
        onView(withId(R.id.tvDescription))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    // Проверка текста кнопки
    @Test
    public void checkButtonBase_hasCorrectText() {
        onView(withId(R.id.buttonBase))
                .check(matches(withText("Базы")));
    }

    // Проверка кликабельна кнопка
    @Test
    public void checkButtonBase_isClickable() {
        onView(withId(R.id.buttonBase))
                .check(matches(isClickable()));
    }

    // Проверка текста кнопки
    @Test
    public void checkButtonSanatori_hasCorrectText() {
        onView(withId(R.id.buttonSanatori))
                .check(matches(withText("Санатории")));
    }

    // Проверка кликабельна кнопка
    @Test
    public void checkButtonSanatori_isClickable() {
        onView(withId(R.id.buttonSanatori))
                .check(matches(isClickable()));
    }

    // Проверка текста кнопки
    @Test
    public void checkButtonHotel_hasCorrectText() {
        onView(withId(R.id.buttonHotel))
                .check(matches(withText("Отели")));
    }

    // Проверка кликабельна кнопка
    @Test
    public void checkButtonHotel_isClickable() {
        onView(withId(R.id.buttonHotel))
                .check(matches(isClickable()));
    } */
}