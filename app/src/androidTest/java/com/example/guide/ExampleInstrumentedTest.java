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

    // Проверка наличия
    @Test
    public void checkTextViewGid_isDisplayed() {
        onView(withId(R.id.textViewGid))
                .check(matches(isDisplayed()));
    }

    // Проверка отображает нужный текст
    @Test
    public void checkTextViewGid_hasCorrectText() {
        onView(withId(R.id.textViewGid))
                .check(matches(withText("Мобильный гид по Алтайскому краю")));
    }

    // Проверка невидимости
    @Test
    public void checkTextViewGid_isGone() {
        onView(withId(R.id.textViewGid))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    // Проверка наличия
    @Test
    public void checkTextViewResearch_isDisplayed() {
        onView(withId(R.id.textViewResearch))
                .check(matches(isDisplayed()));
    }

    // Проверка отображает нужный текст
    @Test
    public void checkTextViewResearch_hasCorrectText() {
        onView(withId(R.id.textViewResearch))
                .check(matches(withText("Исследуйте достопримечательности Алтайского края")));
    }

    // Проверка невидимости
    @Test
    public void checkTextViewResearch_isGone() {
        onView(withId(R.id.textViewResearch))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    // Проверка наличия
    @Test
    public void checkTextViewGoogle_isDisplayed() {
        onView(withId(R.id.textViewGoogle))
                .check(matches(isDisplayed()));
    }

    // Проверка отображает нужный текст
    @Test
    public void checkTextViewGoogle_hasCorrectText() {
        onView(withId(R.id.textViewGoogle))
                .check(matches(withText("Войдите через Google")));
    }

    // Проверка невидимости
    @Test
    public void checkTextViewGoogle_isGone() {
        onView(withId(R.id.textViewGoogle))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    // Проверка текста кнопки
    @Test
    public void checkSignInButton_hasCorrectText() {
        onView(withId(R.id.signInButton))
                .check(matches(withText("Войти через Google")));
    }

    // Проверка кликабельна кнопка
    @Test
    public void checkSignInButton_isClickable() {
        onView(withId(R.id.signInButton))
                .check(matches(isClickable()));
    }
}