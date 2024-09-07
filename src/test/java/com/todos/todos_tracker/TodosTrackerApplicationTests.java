package com.todos.todos_tracker;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.todos.todos_tracker.entity.Schedule;
import com.todos.todos_tracker.model.ScheduleDto;
import com.todos.todos_tracker.repository.ScheduleRepository;
import com.todos.todos_tracker.service.TodosService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class TodosTrackerApplicationTests {

	@Autowired
	private TodosService todosService;
	
	@MockBean
	private ScheduleRepository scheduleRepository;
	
	private List<Schedule> listOfSchedule;
	private DateTimeFormatter dateTimeFormatter;
	
	public TodosTrackerApplicationTests() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        listOfSchedule = Arrays.asList(
                new Schedule(1l, "Morning Meeting", LocalDateTime.parse("2024-09-05 09:00:00", dateTimeFormatter), LocalDateTime.parse("2024-09-05 10:00:00", dateTimeFormatter), 1l, "In Progress"),
                new Schedule(2l, "Lunch Break", LocalDateTime.parse("2024-09-05 12:00:00", dateTimeFormatter), LocalDateTime.parse("2024-09-05 13:00:00", dateTimeFormatter), 2l, "In Progress"),
                new Schedule(3l, "Project Discussion", LocalDateTime.parse("2024-09-05 14:00:00", dateTimeFormatter), LocalDateTime.parse("2024-09-05 15:30:00", dateTimeFormatter), 2l, "In Progress"),
                new Schedule(4l, "Client Call", LocalDateTime.parse("2024-09-05 16:00:00", dateTimeFormatter), LocalDateTime.parse("2024-09-05 17:00:00", dateTimeFormatter), 2l, "In Progress"),
                new Schedule(5l, "Team Wrap-up", LocalDateTime.parse("2024-09-05 17:30:00", dateTimeFormatter), LocalDateTime.parse("2024-09-05 18:00:00", dateTimeFormatter), 1l, "In Progress")
        );
	}
	
	@Test
	void testGetSchedules() {
		long userId = 1l;
		given(scheduleRepository.findByUserId(userId)).willReturn(listOfSchedule.stream().filter(e -> e.getUserId() == userId).toList());
		List<ScheduleDto> result = todosService.getSchedules(userId);
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).id()).isEqualTo(1l);
		assertThat(result.get(0).name()).isEqualTo("Morning Meeting");
		assertThat(result.get(0).startDate()).isEqualToIgnoringNanos(LocalDateTime.parse("2024-09-05 09:00:00", dateTimeFormatter));
		assertThat(result.get(0).endDate()).isEqualToIgnoringNanos(LocalDateTime.parse("2024-09-05 10:00:00", dateTimeFormatter));
		assertThat(result.get(0).userId()).isEqualTo(1l);
		assertThat(result.get(0).completed()).isEqualTo("In Progress");
		
		assertThat(result.get(1).id()).isEqualTo(5l);
		assertThat(result.get(1).name()).isEqualTo("Team Wrap-up");
		assertThat(result.get(1).startDate()).isEqualToIgnoringNanos(LocalDateTime.parse("2024-09-05 17:30:00", dateTimeFormatter));
		assertThat(result.get(1).endDate()).isEqualToIgnoringNanos(LocalDateTime.parse("2024-09-05 18:00:00", dateTimeFormatter));
		assertThat(result.get(1).userId()).isEqualTo(1l);
		assertThat(result.get(1).completed()).isEqualTo("In Progress");
		
	}

}
