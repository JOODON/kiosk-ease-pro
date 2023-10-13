package com.example.kioskeasepro.service;

public interface ValidationService {
    public boolean isUsernameValid(String username);

    public boolean isPasswordValid(String password);

    public boolean isNameValid(String name);

    public boolean isBusinessIdValid(String businessId);
    public boolean isBusinessNameValid(String businessName);

    public boolean isBusinessAddressValid(String businessAddress);

    public boolean isPhoneNumberValid(String phoneNumber);

}
