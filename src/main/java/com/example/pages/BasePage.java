package com.example.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;


public class BasePage {
    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void takeScreenshot(String name) {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }
}
