package com.gdg.Build_with_AI.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_user_uuid", columnList = "userUuid")
    }
)
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String userUuid;

  @Column(unique = true)
  private String providerId;

  @Builder
  public User(String userUuid, String providerId) {
    this.userUuid = userUuid;
    this.providerId = providerId;
  }

  public static User of(String userUuid, String providerId) {
    return User.builder()
        .userUuid(userUuid)
        .providerId(providerId)
        .build();
  }

}
