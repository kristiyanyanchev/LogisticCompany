package org.logistic.company.logisticcompany.persistance.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PackageDTO {
    private Long id;

    //Both are office properties
    private String sentFrom = "";
    private String sentTo = "";

    private String sender = "";
    private String recipient = "";

    private String senderName = "";
    private String senderPhone = "";
    private String senderEmail = "";
    private String senderNote = "";
    private String senderAddress = "";

    private String recipientName = "";
    private String recipientPhone = "";
    private String recipientEmail = "";
    private String recipientAddress = "";

    private String employee = "";

    private String sentAt = LocalDateTime.now().toString();
    private String receivedAt = "";
    private BigDecimal price = BigDecimal.ZERO;
    private String status = "";

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderNote() {
        return senderNote;
    }

    public void setSenderNote(String senderNote) {
        this.senderNote = senderNote;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sendAt) {
        this.sentAt = sendAt;
    }

    public String getSource() {
        return sentFrom;
    }

    public void setSource(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    public String getDestination() {
        return sentTo;
    }

    public void setDestination(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {

        this.receivedAt = receivedAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
