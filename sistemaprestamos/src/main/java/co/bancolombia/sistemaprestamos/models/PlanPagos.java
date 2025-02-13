package co.bancolombia.sistemaprestamos.models;

import java.math.BigDecimal;

public class PlanPagos {

    private Integer numerocuota;
    private BigDecimal capitalcuota;
    private BigDecimal interescuota;
    private BigDecimal valorcuota;
    private BigDecimal saldo;

    public PlanPagos() {
    }

    
    public PlanPagos(Integer numerocuota, BigDecimal capitalcuota, BigDecimal interescuota, BigDecimal valorcuota,
            BigDecimal saldo) {
        this.numerocuota = numerocuota;
        this.capitalcuota = capitalcuota;
        this.interescuota = interescuota;
        this.valorcuota = valorcuota;
        this.saldo = saldo;
    }


    public Integer getNumerocuota() {
        return numerocuota;
    }
    public void setNumerocuota(Integer numerocuota) {
        this.numerocuota = numerocuota;
    }
    public BigDecimal getCapitalcuota() {
        return capitalcuota;
    }
    public void setCapitalcuota(BigDecimal capitalcuota) {
        this.capitalcuota = capitalcuota;
    }
    public BigDecimal getInterescuota() {
        return interescuota;
    }
    public void setInterescuota(BigDecimal interescuota) {
        this.interescuota = interescuota;
    }
    public BigDecimal getValorcuota() {
        return valorcuota;
    }
    public void setValorcuota(BigDecimal valorcuota) {
        this.valorcuota = valorcuota;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    

}
