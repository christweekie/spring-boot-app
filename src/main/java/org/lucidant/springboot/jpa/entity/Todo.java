package org.lucidant.springboot.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Entity
@Table(name = "TODO")
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Builder
@With
public class Todo {

	@NotNull(message = "Product id is required")
	@Column(nullable = false)
	private int userId;
	@Id
	private int id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private boolean completed;
}
