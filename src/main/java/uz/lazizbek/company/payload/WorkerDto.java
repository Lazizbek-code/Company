package uz.lazizbek.company.payload;

import javax.validation.constraints.NotNull;

public class WorkerDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak")
    private String phoneNumber;

    @NotNull(message = "departmentId bo'sh bo'lmasligi kerak")
    private Integer departmentId;

    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'masligi kerak")
    private String homeNumber;

    public WorkerDto() {
    }

    public WorkerDto(String name, String phoneNumber, Integer departmentId, String street, String homeNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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
