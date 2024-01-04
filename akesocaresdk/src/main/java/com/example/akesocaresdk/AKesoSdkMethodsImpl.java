package com.example.akesocaresdk;

public class AKesoSdkMethodsImpl implements AkesoSdkMethods {
    private boolean isInit = false;

    @Override
    public void init() {
        isInit = true;
    }

    @Override
    public Boolean authenticateApiKey(String apiKey) {
        // call some function or Auth apis
        return isInit;
    }
}