package models;

public class Task {
    private String company;
    private String vatNumber;
    private String phone;
    private String website;
    private String group;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String currency;
    private String language;
    private String country;

    public Task(String company, String vatNumber, String phone, String website,
                   String group, String address, String city, String state,
                   String zipCode, String currency, String language, String country) {
        this.company = company;
        this.vatNumber = vatNumber;
        this.phone = phone;
        this.website = website;
        this.group = group;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.currency = currency;
        this.language = language;
        this.country = country;
    }

    public static class Builder {
        private String company;
        private String vatNumber;
        private String phone;
        private String website;
        private String group;
        private String address;
        private String city;
        private String state;
        private String zipCode;
        private String currency;
        private String language;
        private String country;

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder vatNumber(String vatNumber) {
            this.vatNumber = vatNumber;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder group(String group) {
            this.group = group;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Customer build() {
            return new Customer(company, vatNumber, phone, website, group,
                              address, city, state, zipCode, currency, language, country);
        }
    }

    public String getCompany() { return company; }
    public String getVatNumber() { return vatNumber; }
    public String getPhone() { return phone; }
    public String getWebsite() { return website; }
    public String getGroup() { return group; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getCurrency() { return currency; }
    public String getLanguage() { return language; }
    public String getCountry() { return country; }

    @Override
    public String toString() {
        return "Customer{" +
                "company='" + company + '\'' +
                ", group='" + group + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
