package pro.sky.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.sky.todolist.dto.TaskDtoIn;
import pro.sky.todolist.dto.TaskDtoOut;
import pro.sky.todolist.mapper.LabelMapper;
import pro.sky.todolist.mapper.UserMapper;
import pro.sky.todolist.repository.LabelRepository;
import pro.sky.todolist.repository.TaskRepository;
import pro.sky.todolist.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
public class TaskControllerTest extends Generator {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LabelMapper labelMapper;

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/generate/users").param("count", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/generate/labels").param("count", "20"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
        labelRepository.deleteAll();
        taskRepository.deleteAll();
    }

    @Test
    public void createTest() throws Exception {
        TaskDtoIn taskDtoIn = generateTaskDtoIn();

        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDtoIn)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(result -> {
                    TaskDtoOut taskDtoOut = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            TaskDtoOut.class
                    );
                    assertThat(taskDtoOut.getName()).isEqualTo(taskDtoIn.getName());
                    assertThat(taskDtoOut.getDescription()).isEqualTo(taskDtoIn.getDescription());
                    assertThat(taskDtoOut.getUserId()).isEqualTo(taskDtoIn.getUserId());
                    assertThat(taskDtoOut.getLabelId()).isEqualTo(taskDtoIn.getLabelId());


                });
    }

}
