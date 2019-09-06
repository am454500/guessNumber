package com.example.guessnumber

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class MetarialActivityTest{
    @Rule
    @JvmField
    var ActivityTestRule=ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong(){
        val resources=ActivityTestRule.activity.resources
        var msg_nAnB=ActivityTestRule.activity.secretNumber.msg_nAnB
        val number="1234"
//        Espresso.onView(ViewMatchers.withId(R.id.id_number)).perform(ViewActions.typeText("1234 "))
//        Espresso的 ONVIEW方法 WITH ID 太長了 以下為簡短寫法 IMPORT相關類別
        if(msg_nAnB != "4A0B") {
            onView(withId(R.id.id_number)).perform(clearText())
            onView(withId(R.id.id_number)).perform(typeText("1234 "))
            onView(withId(R.id.btn_OK)).perform(click())
//            檢查如果不是4A 是否會顯示XAXB的提示
            onView(withText(msg_nAnB)).check(ViewAssertions.matches(isDisplayed()))
        }
    }
    @Test
    fun restart(){
        onView(withId(R.id.fab)).perform(click())
//        onView(withId(android.R.id.button1)).perform(click())
//        onView(withId(R.id.id_count)).check(ViewAssertions.matches(withText("1")))
    }
}