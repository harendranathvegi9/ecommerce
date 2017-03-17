package com.aripd.ecommerce.view;

import com.aripd.ecommerce.entity.AddressEntity;
import com.aripd.ecommerce.entity.UserGroup;
import com.aripd.ecommerce.entity.UserStatus;
import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @see Builder Pattern
 */
public class SignupModel {

    private final UserGroup group;
    private final UserStatus status;
    private final String email;
    private final String password;
    private final String firstname;
    private final String lastname;

    private final AddressEntity address;

    public SignupModel(UserGroup group, UserStatus status, String email, String password, String firstname, String lastname, AddressEntity address) {
        this.group = group;
        this.status = status;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public UserGroup getGroup() {
        return group;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public static class Builder {

        private UserGroup group;
        private UserStatus status;
        private String email;
        private String password = RandomStringUtils.random(6, 0, 0, true, true, null, new SecureRandom());

        private String firstname = "";
        private String lastname = "";

        private AddressEntity address = null;

        public Builder setGroup(UserGroup group) {
            this.group = group;
            return this;
        }

        public Builder setStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setAddress(AddressEntity address) {
            this.address = address;
            return this;
        }

        public SignupModel build() {
            return new SignupModel(group, status, email, password, firstname, lastname, address);
        }
    }

}
