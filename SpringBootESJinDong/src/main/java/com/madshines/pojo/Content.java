package com.madshines.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author :madshines
 * @Date: 2020-06-09
 * @Description: com.madshines.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "content")
public class Content {
    @Id
    private String title;
    @Column(name = "img")
    private String img;
    @Column(name = "price")
    private String price;
}
