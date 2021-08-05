package org.alalgo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class SpringProject {
	private String name;
	private Date beginDate;
	private String desc;
}
