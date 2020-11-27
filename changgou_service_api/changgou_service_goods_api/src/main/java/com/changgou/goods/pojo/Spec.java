package com.changgou.goods.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_spec")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spec {
    /**
     * 规格表
     */
    @Id
    private Integer id;
    private String name;    //规格名称
    private String options; //规格选项
    private Integer seq;    //排序
    @Column(name = "template_id")   //排序
    private Integer templateId; //模板ID
}
