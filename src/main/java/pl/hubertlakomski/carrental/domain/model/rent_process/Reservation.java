package pl.hubertlakomski.carrental.domain.model.rent_process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.aspectj.lang.annotation.After;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;
import pl.hubertlakomski.carrental.domain.model.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="reservations") @Getter @Setter
@ToString(exclude = {
        "client", "sippCode", "rentDepartment", "plannedReturnDepartment", "rentData", "returnData"}, callSuper = true)
public class Reservation extends ParentEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @OneToOne @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @JoinColumn(name="sipp_code_id")
    private SippCode sippCode;
    //when booking, the employee provides the SIPP code not concrete car

    @Column(nullable = false)
    private LocalDateTime plannedRentDate;

    @Column(nullable = false)
    private LocalDateTime plannedReturnDate;

    @OneToOne @JoinColumn(name="rent_department_id")
    private Department rentDepartment;

    @OneToOne @JoinColumn(name="planned_return_department_id")
    private Department plannedReturnDepartment;

    private Long deposit;

    private double plannedRentalFee;
    //rental fee: perDay(number of rental days from the beginning and end of the rental)*amount(from sipp code)

    @OneToOne @JoinColumn(name="rent_data_id")
    private ReservationRent rentData;

    @OneToOne @JoinColumn(name="return_data_id")
    private ReservationReturn returnData;

    private String comment;

    @PrePersist
    public void setDepositAndPlannedRentalFee(){

        deposit = sippCode.getDeposit();

        double periodOfRentalDays  = getPeriodOfRentalDays(plannedRentDate, plannedReturnDate);
        Long amountForDay = sippCode.getPerDayCharge();
        plannedRentalFee = periodOfRentalDays*amountForDay;
    }

    private double getPeriodOfRentalDays(LocalDateTime start, LocalDateTime end){

        long periodOfHours = ChronoUnit.HOURS.between(start, end);

        double period = periodOfHours/24.0;

        if(period < 1){
            return 1;
        }
        else if(period%1==0){
            return period;
        }
        else{
            return Math.ceil(period);
        }
    }
}
