package com.devtucs.identityservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node("user_profile")
public class Profile {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;

    @Property("userId")
    String userId;
    String fullName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dob;
    String email;
    int gender;
    String address;

}
