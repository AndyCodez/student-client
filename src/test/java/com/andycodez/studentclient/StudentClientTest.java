package com.andycodez.studentclient;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureWireMock
public class StudentClientTest {

    @Autowired
    private StudentClient studentClient;

    @Test
    void getStudent_forGivenStudent_isReturned() {
        Long id = 1L;
        stubFor(WireMock.get("/students/" + id).willReturn(okJson("""
                    {
                    "id": 1,
                    "studentName": "Mark",
                    "grade": 10
                    }
                """)));

        Student student = studentClient.getStudent(id);

        then(student.getId()).isNotNull();
        then(student.getStudentName()).isEqualTo("Mark");
        then(student.getGrade()).isEqualTo(10);
    }
}
