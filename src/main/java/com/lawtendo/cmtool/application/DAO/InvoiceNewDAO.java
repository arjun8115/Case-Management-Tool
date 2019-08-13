package com.lawtendo.cmtool.application.DAO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name="invoicenew_table")
@Component
public class InvoiceNewDAO {

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(updatable = false)
	private String inewuid;
	
	@Column(updatable = false)
	private String cuid;
	@Column(updatable = false)
	private String uuid;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String type;
	@Column
	private Integer amount;
	@Column
	private Timestamp createdAt;
	@Column
	private Timestamp updatedAt;
	@Column
	private String date;
	@Column
	private String status;
	@Column
	private boolean isDeleted;
	@Column
	private Integer quantity;
	
	private String payment_terms_title;
	
	private String item_header;
	
	private String quantity_header;
	
	private String purchase_order_title;
	
	private String invoice_number_title;
	
	private String currency;
	
	private String logo;
	
	private String notes;
	
	public String getInewuid() {
		return inewuid;
	}
	public void setInewuid(String inewuid) {
		this.inewuid = inewuid;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	public String getPayment_terms_title() {
		return payment_terms_title;
	}
	public void setPayment_terms_title(String payment_terms_title) {
		this.payment_terms_title = payment_terms_title;
	}
	public String getItem_header() {
		return item_header;
	}
	public void setItem_header(String item_header) {
		this.item_header = item_header;
	}
	public String getQuantity_header() {
		return quantity_header;
	}
	public void setQuantity_header(String quantity_header) {
		this.quantity_header = quantity_header;
	}
	public String getPurchase_order_title() {
		return purchase_order_title;
	}
	public void setPurchase_order_title(String purchase_order_title) {
		this.purchase_order_title = purchase_order_title;
	}
	public String getInvoice_number_title() {
		return invoice_number_title;
	}
	public void setInvoice_number_title(String invoice_number_title) {
		this.invoice_number_title = invoice_number_title;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "InvoiceNewDAO [inewuid=" + inewuid + ", cuid=" + cuid + ", uuid=" + uuid + ", name=" + name
				+ ", description=" + description + ", type=" + type + ", amount=" + amount + ", date=" + date
				+ ", status=" + status + ", isDeleted=" + isDeleted + ", quantity=" + quantity + "]";
	}
	
	
}
