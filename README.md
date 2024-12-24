## 특강 수강신청 애플리케이션
이번 과제는 특강에 대해 수강 신청하는 애플리케이션입니다.

클린 아키텍쳐와 TDD, 동시성 제어를 활용하여 구현합니다.


### 설계 아키텍쳐 (12/24)
```
/src
	/interfaces
		/api
			/lecture
				LectureController.java
	/application
		/lecture
			EnrollFacade.java
	/domain
		/common
			BaseEntity.java
		/lecture
			/enrollment
				EnrollmentService.java
				Enrollment.java
				EnrollmentRepository.java (interface)
			LectureService.java
			Lecture.java (JPA Entity)
			LectureRepository.java (interface)
		/exception
			DuplicatedEnrollmentException.java
			EnrollmentLimitExceededException.java
			ScheduleConflictException.java
	/infrastructure
		/lecture
			LectureRepositoryImpl.java
			LectureJpaRepository.java (interface)
			EnrollmentRepositoryImpl.java
			EnrollmentJpaRepository.java (interface)
	/support
		/http
			/exception
				GlobalExceptionHandler.java
				ErrorCode.java
			/reponse
				ApiResponse.java
```