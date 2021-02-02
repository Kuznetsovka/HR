package org.example.lesson1;

public class Person {
    public final String firstName;
    public final String lastName;
    public final String middleName;
    public final String country;
    public final String address;
    public final String phone;
    public final int age;
    public final String gender;

    private Person(Person builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        middleName = builder.middleName;
        country = builder.country;
        address = builder.address;
        phone = builder.phone;
        age = builder.age;
        gender = builder.gender;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public Builder(String firstName, String lastName, String middleName, String country, String address, String phone, int age, String gender) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.country = country;
            this.address = address;
            this.phone = phone;
            this.age = age;
            this.gender = gender;
        }

        //Методы с возвращающим типом Builder для необязательного параметра с, d, e, f,
        public Builder firstName(String txt) {
            firstName = txt;
            return this;
        }

        public Builder lastName(String txt) {
            lastName = txt;
            return this;
        }

        public Builder middleName(String txt) {
            middleName = txt;
            return this;
        }

        public Builder country(String txt) {
            country = txt;
            return this;
        }

        public Builder address(String txt) {
            address = txt;
            return this;
        }

        public Builder phone(String txt) {
            phone = txt;
            return this;
        }

        public Builder age(int txt) {
            age = txt;
            return this;
        }

        public Builder gender(String txt) {
            gender = txt;
            return this;
        }

    }

        public Person build() {
            return new Person (this); }
}
