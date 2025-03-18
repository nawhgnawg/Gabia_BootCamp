package dev.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class DevVO {
  private String name="";
  private double year = 1.5;
  private int payTot = 2500000;
  private double pay_tax = 1.5;
  
  
}

