package com.data.datawaytest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Baijl
 * @date 2020-04-22
 * @time 15:23
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {
    private Integer pageIndex=0;
    private Integer pageSize=3;
}
