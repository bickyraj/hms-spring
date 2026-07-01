package com.hms.hospital.patientcase.entity;

import com.hms.common.valueobject.PatientId;
import com.hms.hospital.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientCase {

	private Long id;
	private PatientId patientId;
	private Hospital hospital;
}
