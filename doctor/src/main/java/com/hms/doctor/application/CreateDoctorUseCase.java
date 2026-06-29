package com.hms.doctor.application;

import com.hms.common.UseCase;
import com.hms.doctor.entity.Doctor;
import com.hms.doctor.services.DoctorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateDoctorUseCase extends UseCase<CreateDoctorUseCase.Request, CreateDoctorUseCase.Response> {

	@Data
	@AllArgsConstructor(staticName = "of")
	public static class Request {
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final String email;
		private final String username;
	}

	@Data
	@AllArgsConstructor(staticName = "of")
	public static class Response {
        private final String message;
	}

    private final DoctorService doctorService;

	@Override
	public Response execute(Request request) {
        Doctor doctor = Doctor.builder()
				.username(request.username)
                .email(request.email)
                .firstName(request.firstName)
                .middleName(request.middleName)
                .lastName(request.lastName)
                .build();
        doctorService.createDoctor(doctor);
        return Response.of("doctor created");
	}
}
