package uz.lazizbek.company.payload;

import javax.validation.constraints.NotNull;

public class DepartmentDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "companyId bo'sh bo'lmasligi kerak")
    private Integer companyId;

    public DepartmentDto() {
    }

    public DepartmentDto(String name, Integer companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
