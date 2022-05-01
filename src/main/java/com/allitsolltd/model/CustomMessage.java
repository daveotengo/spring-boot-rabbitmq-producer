package com.allitsolltd.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8397562829181578108L;
	private String messageId;
	private String message;
	private Date messageDate;

}
