package app.code.service.enums;

public enum CategoryType {
    A(10.0),
    B(20.0),
    C(0.0);

    private Double extraCharge;

    private CategoryType(Double extraCharge) {
        this.extraCharge = extraCharge;
    }

    public Double getExtraCharge() {
        return extraCharge;
    }

}
