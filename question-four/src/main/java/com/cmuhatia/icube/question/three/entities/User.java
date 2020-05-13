/*
 * Copyright 2019 Cornelius M.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cmuhatia.icube.question.three.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User database entity
 *
 * @author Cornelius M.
 * @version 1.0.0, 12/05/2020
 */
@Entity
@Table(name="USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(max = 200, min = 1)
    @NotNull
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "borrower")
    List<Iou> owes = new ArrayList<>();
    @OneToMany(mappedBy = "lender")
    List<Iou> owedBy = new ArrayList<>();

    /**
     * Default constructor used by JPA
     */
    public User(){

    }

    public User(String name){
        this.setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.nonNull(name) ? name.trim(): null;
    }

    public List<Iou> getOwes() {
        return owes;
    }

    public void setOwes(List<Iou> owes) {
        this.owes = owes;
    }

    public List<Iou> getOwedBy() {
        return owedBy;
    }

    public void setOwedBy(List<Iou> owedBy) {
        this.owedBy = owedBy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
