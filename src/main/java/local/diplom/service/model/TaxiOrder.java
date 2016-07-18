package local.diplom.service.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by [david] on 18.07.16.
 */
@Entity
@Table(name = "taxi_orders")
public class TaxiOrder {
    @Id
    private String id;
    private Date date; // дата создания заказа
    private String status; // статус заказа
    private String phone; // номер телефона клиента
    private String description;
    private String driver; // id водителя принявшего заказ
    private String driverName; // фио водителя принявшего заказ
    private String driverPhone; // тел. водителя принявшего заказ
    private String firm; // фирма исполнившая заказ

    private String fromAddr; // адрес подачи машины
    private String area;
    private String tarif; // тариф заказа
    private double cost;// стоимость при создании заказа
    private double costF;// стоимость фактическая
    private double distanceF;// дистанция
    private double costT;// стоимость ожидания
    private double distance;// дистанция
    private long waitTimeout; // суммареное время ожидания за всю поездку
    private Date dateArrrive; // дата подачи машины
    private Date dateComplete;// дата завершения поездки
    private int payType;// способ опалты 0 - нал, 1 - безнал, 2 - акция
    private String firmIncom;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCostF() {
        return costF;
    }

    public void setCostF(double costF) {
        this.costF = costF;
    }

    public double getCostT() {
        return costT;
    }

    public void setCostT(double costT) {
        this.costT = costT;
    }

    public double getDistanceF() {
        return distanceF;
    }

    public void setDistanceF(double distanceF) {
        this.distanceF = distanceF;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getWaitTimeout() {
        return waitTimeout;
    }

    public void setWaitTimeout(long waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    public Date getDateArrrive() {
        return dateArrrive;
    }

    public void setDateArrrive(Date dateArrrive) {
        this.dateArrrive = dateArrrive;
    }

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getFirmIncom() {
        return firmIncom;
    }

    public void setFirmIncom(String firmIncom) {
        this.firmIncom = firmIncom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
