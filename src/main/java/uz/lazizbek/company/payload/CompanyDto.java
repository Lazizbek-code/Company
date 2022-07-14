package uz.lazizbek.company.payload;

import javax.validation.constraints.NotNull;

public class CompanyDto {

    @NotNull(message = "companyName bo'sh bo'lmasligi kerak")
    private String companyName;

    @NotNull(message = "directorName bo'sh bo'lmasligi kerak")
    private String directorName;

    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'masligi kerak")
    private String homeNumber;

    public CompanyDto() {
    }

    public CompanyDto(String companyName, String directorName, String street, String homeNumber) {
        this.companyName = companyName;
        this.directorName = directorName;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }
}
