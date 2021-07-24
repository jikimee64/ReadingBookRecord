package com.kwc.ch2reactive.ch2reactive;

import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Item {
    private @Id String id;
    private String name;
    private double price;
    //추가
    private String distributorRegion;
    private Date releaseDate;
    private int availableUnits;
    //private Point location;
    private boolean active;

    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
