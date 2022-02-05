package com.yhx.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonMessage<T>
{
    private String code;
    private String message ;
    private T date;

    public CommonMessage(String code,String message){
        this.code =code;
        this.message = message;
    }
}
