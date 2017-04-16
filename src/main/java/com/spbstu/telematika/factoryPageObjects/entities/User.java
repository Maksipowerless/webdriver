package com.spbstu.telematika.factoryPageObjects.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by maxfromperek on 16.04.17.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String login;
    String password;
}
