package com.aymanshehri.bakingapp.activities;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.aymanshehri.bakingapp.R;
import com.aymanshehri.bakingapp.SimpleIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    IdlingResource idlingResource;

    @Before
    public void setUp() throws Exception {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void testUserClickOnRecipe(){
        onView(withId(R.id.rv_recipe)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}