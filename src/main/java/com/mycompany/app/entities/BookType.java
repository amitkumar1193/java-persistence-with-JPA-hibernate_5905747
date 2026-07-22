package com.mycompany.app.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(BookTypeKey.class)
@Table(name = "book_type")
public class BookType {

  @Id
  @Column(name = "type_code")
  private String code;

  @Id
  @Column(name = "type_subcode")
  private String subCode;

  @Column(name = "type_name")
  private String typeName;

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getSubCode() {
	return subCode;
}

public void setSubCode(String subCode) {
	this.subCode = subCode;
}

public String getTypeName() {
	return typeName;
}

public void setTypeName(String typeName) {
	this.typeName = typeName;
}

@Override
public String toString() {
	return "BookType [code=" + code + ", subCode=" + subCode + ", typeName=" + typeName + "]";
}

@Override
public int hashCode() {
	return Objects.hash(code, subCode, typeName);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	BookType other = (BookType) obj;
	return code == other.code && Objects.equals(subCode, other.subCode) && Objects.equals(typeName, other.typeName);
}

}
